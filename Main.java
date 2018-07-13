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

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 'c', 100);
            MidiEvent nute1S = new MidiEvent(a, 4);
            track.add(nute1S);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 'c', 100);
            MidiEvent nute1E = new MidiEvent(b, 16);
            track.add(nute1E);

            ShortMessage c = new ShortMessage();
            c.setMessage(144, 1, 'd', 100);
            MidiEvent nute2S = new MidiEvent(c, 17);
            track.add(nute2S);

            ShortMessage d = new ShortMessage();
            d.setMessage(128, 1, 'd', 100);
            MidiEvent nute2E = new MidiEvent(d, 32);
            track.add(nute2E);

            sequencer.setSequence(sequence);
            
            sequencer.start();
            // sequencer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

