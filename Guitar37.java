//Implementation of the GuitarString class 
//which simulates a guitar string with the Karplus-Strong algorithm.

import java.util.*;

//Class that implements the Guitar37 object, a simulation of 37 guitar strings
//from 110 Hz to 880 Hz
public class Guitar37 implements Guitar {
	
	public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	public static final double CONCERT_A = 440;
	public static final int NUM_STRINGS = 37;
	public static final int SHIFT = 24;
	private GuitarString[] strings;
	private int times; //The number of times that tic is called
	
	//Returns the frequency of a certain key index. The index has to be an int.
	private double frequency(int index) {
		return CONCERT_A * Math.pow(2.0, (index - SHIFT)/(SHIFT/2.0));
	}
	
	//Constructor of the Guitar37 object. 
	//It makes 37 strings representing every note from 110 to 880 Hz.
	public Guitar37() {
		strings = new GuitarString[NUM_STRINGS];
		for(int i = 0; i < KEYBOARD.length(); i++)
			strings[i] = new GuitarString(frequency(i));
		times = 0; 
	}
	
	//Returns the number of times that 'tic' has been called
	//Represents the number of time units since the Guitar37 was created.
	public int time() {
		return times;
	}
	
	//Returns the sum of the GuitarString sounds.
	//This is the sound that is played by the soundcard.
	public double sample() {
		double s = 0;
		for(GuitarString i : strings) s += i.sample();
		return s;
	}
	
	//Moves forward one time unit.
	public void tic() {
		for(GuitarString i : strings) i.tic();
		times++;
	}
	
	//Creates a GuitarString based on a particular key from 'q' to ' '.
	//Throws an IllegalArgumentException if the key is invalid.
	public void pluck(char key) {
		int index = KEYBOARD.indexOf(key);
		if(index == -1) throw new IllegalArgumentException("Invalid key");
		strings[index].pluck();
	}
	
	//Creates a GuitarString with a certain pitch. 
	//Pitch is represented as the number of 'piano keys' away from concert A.
	//The parameter must be an int.
	//Ignores any keys not in the 37-string range of the Guitar37.
	public void playNote(int pitch) {
		if(pitch + SHIFT >=0 && pitch + SHIFT < NUM_STRINGS) 
			strings[pitch + SHIFT].pluck();
	}
	
	//Returns true if the string of that key exists.
	//The parameter must be a char.
	public boolean hasString(char key) {
		return KEYBOARD.indexOf(key) != -1;
	}
}
