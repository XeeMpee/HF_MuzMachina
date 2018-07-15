import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class Main  {
    JFrame ramka = new JFrame();
    MojPanelGraf panelGraficzny = new MojPanelGraf();
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main main = new Main();
        main.play();
    }

    public void konfigurujGui(){
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setContentPane(panelGraficzny);
        ramka.setBounds(30, 30, 300, 300);
        ramka.setVisible(true);
    }
    
    public void play(){
        this.konfigurujGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] zdarzeniaObslugiwane = {127};
            sequencer.addControllerEventListener(panelGraficzny, zdarzeniaObslugiwane);

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            for(int i=0;i<=21; i+=4){
                track.add(createEvent(144, 1, i+48, 100, i));
                track.add(createEvent(176, 1, 127, 0, i));
                track.add(createEvent(128, 1, i+48, 100, i+2));
            }
            
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MidiEvent createEvent(int plc, int kanal, int jeden, int dwa, int takt){
        
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(plc, kanal, jeden, dwa);
            event = new MidiEvent(a, takt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }

    // @Override
    // public void controlChange(ShortMessage event) {
    //     System.out.println("la");        
    // }

    class MojPanelGraf extends JPanel implements ControllerEventListener {
        boolean komunikat = false;

        @Override
        public void controlChange(ShortMessage event) {
            komunikat = true;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            if(komunikat == false){
                return;
            }

            g2.setColor(new Color((int)(Math.random()*250),(int)(Math.random()*250),(int)(Math.random()*250)));
        
            int wys = (int)((Math.random()*120)+10);
            int szer = (int)((Math.random()*120)+10);
            int x = (int)((Math.random()*40)+10);
            int y = (int)((Math.random()*40)+10);
            
            g.fillRect(x, y, szer, wys);
            komunikat = false;
        }
    }

}



