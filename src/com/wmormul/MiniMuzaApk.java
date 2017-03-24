package com.wmormul;
import javax.sound.midi.*;

public class MiniMuzaApk {

    public static void main(String[] args) {
	    MiniMuzaApk mini = new MiniMuzaApk();
	    if (args.length < 2) {
	        System.out.println("Nie zapomnij podać argumentów określających instrument i nutę");
        } else {
	        int instrument = Integer.parseInt(args[0]);
	        int nuta = Integer.parseInt(args[1]);
	        mini.graj(instrument, nuta);
        }
    } // end main

    public void graj(int instrument, int nuta) {
        try {
            Sequencer sekwenser = MidiSystem.getSequencer();
            sekwenser.open();

            Sequence sekw = new Sequence(Sequence.PPQ, 4);
            Track sciezka = sekw.createTrack();

            MidiEvent zdarzenie = null;

            ShortMessage pierwszy = new ShortMessage();
            pierwszy.setMessage(192, 1, nuta, 100);
            MidiEvent zmienInstrument = new MidiEvent(pierwszy, 1);
            sciezka.add(zmienInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,nuta,100);
            MidiEvent nutaP = new MidiEvent(a,1);
            sciezka.add(nutaP);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, nuta, 100);
            MidiEvent nutaK = new MidiEvent(b,16);
            sciezka.add(nutaK);
            sekwenser.setSequence(sekw);
            sekwenser.start();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    } //end method graj
}
