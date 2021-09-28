import java.util.Random;

public class GenomeThread extends Thread {
	
	// Identifier for which thread is running
	private final int thread;
	
	// constructor 
	public GenomeThread(int thread) {
		this.thread = thread;
	}
	
	// Create an array of characters to be used to create genome sequences 
	static char[] charStrings = new char[]{'A','T','G','C'};

	// Override the run method to create the random genome sequence 
	@Override
	public void run() { 
		// Use random object to generate random genome sequence
		Random rand = new Random();
		
		// Generate 20 random genome sequences 
	    for(int i=0; i<20; i++) {
			String sequence = "";
		      for (int j=0; j<10; j++) {
		    	  // Get a random number to pick a letter from the charStrings array
		    	  int randNum = rand.nextInt(4);
		    	  
		    	  // Get the letter of the random Number from the charStrings array and add to the sequence 
			      sequence += charStrings[randNum];
		      }
		      System.out.println("Thread " + thread + " ===> " + i + ": " + sequence);
		}
	}
}