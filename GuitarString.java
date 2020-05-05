//Implementation of the GuitarString class which simulates a guitar string
//with the Karplus-Strong algorithm.

import java.util.*;

//Class describes the implementation of a GuitarString object.
//It simulates a pitch with the Karplus-Strong algorithm.
public class GuitarString {
	
	//Decay factor of the sound. This simulates the sound dissipating.	
	public static final double DECAY_FACTOR = .996;
	private Queue<Double> ringBuffer;
	
	//Constructs a GuitarString of specified frequency.
	//Throws IllegalArgumentException if the frequency is < 1 
	//or >= than the sampling rate of the soundcard. 
	public GuitarString(double frequency) {
		ringBuffer = new LinkedList<Double>();
		if(frequency < 1 || frequency >= StdAudio.SAMPLE_RATE)
			throw new IllegalArgumentException("This frequency is out of range.");
		for(int i = 0; i < Math.round(StdAudio.SAMPLE_RATE/frequency); i++)
			ringBuffer.add(0.0);
	}
	
	//Constructs a GuitarString with a specified array of doubles.
	//The array must have at least 2 elements otherwise an IllegalArgumentException is thrown.
	public GuitarString(double[] init) {
		if(init.length < 2) throw new IllegalArgumentException("Invalid Array");
		ringBuffer = new LinkedList<Double>();
		for(double i : init) ringBuffer.add(i);
	}
	
	//Simulates plucking and seeds the GuitarString with random values 
	//which will be used in Karplus-Strong algorithm
	public void pluck() {
		Random r = new Random();
		for(int i = 0; i <= ringBuffer.size(); i++) {
			ringBuffer.remove();
			ringBuffer.add(r.nextDouble() - 0.5);
		}
	}
	
	//Ticks the string forward by one time unit
	public void tic() {
		double a, b;
		a = ringBuffer.remove();
		b = ringBuffer.peek();
		ringBuffer.add((a + b) / 2 * DECAY_FACTOR);
	}
	
	//Returns the current state of the string.
	//This is what is played by the soundcard.
	public double sample() {
    		return ringBuffer.peek();
	}

}
