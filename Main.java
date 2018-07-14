import javax.sound.midi.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        play();
    }

    public static void play(){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int tick = 0;
            int len = 8;
            int gamaMovement = -5;
            int speed = 127;

            for(int i=0;i<16; i+=2){
                ShortMessage mS = new ShortMessage();
                mS.setMessage(144, 1, 'c'+i+(gamaMovement*7), speed);
                MidiEvent nuteS = new MidiEvent(mS, tick);
                tick += len;
                ShortMessage mE = new ShortMessage();
                mE.setMessage(128, 1, 'c'+i+(gamaMovement*7), speed);
                MidiEvent nuteE = new MidiEvent(mE, tick);
                track.add(nuteS);
                track.add(nuteE);
            }

            ShortMessage m = new ShortMessage();
            m.setMessage(192, 1, 102, 0);
            MidiEvent event = new MidiEvent(m, tick);
            track.add(event);

            for(int i=0;i<16; i+=2){
                ShortMessage mS = new ShortMessage();
                mS.setMessage(144, 1, 'c'+14-i+(gamaMovement*7), speed);
                MidiEvent nuteS = new MidiEvent(mS, tick);
                tick += len;
                ShortMessage mE = new ShortMessage();
                mE.setMessage(128, 1, 'c'+14-i+(gamaMovement*7), speed);
                MidiEvent nuteE = new MidiEvent(mE, tick);
                track.add(nuteS);
                track.add(nuteE);
            }

            sequencer.setSequence(sequence);
            
            sequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

