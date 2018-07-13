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
            for(int i=0;i<16; i+=2){
                ShortMessage mS = new ShortMessage();
                mS.setMessage(144, 1, 'c'+i+(gamaMovement*7), 100);
                MidiEvent nuteS = new MidiEvent(mS, tick);
                tick += len;
                ShortMessage mE = new ShortMessage();
                mE.setMessage(128, 1, 'c'+i+(gamaMovement*7), 100);
                MidiEvent nuteE = new MidiEvent(mE, tick);
                track.add(nuteS);
                track.add(nuteE);
            }
            for(int i=0;i<16; i+=2){
                ShortMessage mS = new ShortMessage();
                mS.setMessage(144, 1, 'c'+14-i+(gamaMovement*7), 100);
                MidiEvent nuteS = new MidiEvent(mS, tick);
                tick += len;
                ShortMessage mE = new ShortMessage();
                mE.setMessage(128, 1, 'c'+14-i+(gamaMovement*7), 100);
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

