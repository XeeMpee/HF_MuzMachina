import javax.sound.midi.*;

public class Main implements ControllerEventListener {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main main = new Main();
        main.play();
    }

    public void play(){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] zdarzeniaObslugiwane = {127};
            sequencer.addControllerEventListener(this, zdarzeniaObslugiwane);

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

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("la");        
    }

}

