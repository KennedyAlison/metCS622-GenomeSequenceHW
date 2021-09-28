import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Genome extends Thread {
	
	// Create an array of characters to be used to create genome sequences 
	static char[] charStrings = new char[]{'A','T','G','C'};
	// Track total time for single thread
	static long singleTime = 0;
	// Track total time of multi thread
	static long multiTime = 0;
	// Track total time of multi thread using executor service 
	static long multiTimeEx = 0;
	
	
	public static void main(String[] args) throws InterruptedException {
	
		// Generate 100 random genome sequences using a single thread 
		
		// Track start time in nano seconds to be percise 
		long singleStart = System.nanoTime();
		
		// Use random object to generate random genome sequence
		Random rand = new Random();
		
		// Generate 100 random genome sequences
		for(int i=0; i<100; i++) {
			String sequence = "";
		      for (int j=0; j<10; j++) {
		    	  // Get a random number to pick a letter from the charStrings array
		    	  int randNum = rand.nextInt(4);
		    	  
		    	  // Get the letter of the random Number from the charStrings array and add to the sequence 
			      sequence += charStrings[randNum];
		      }
		      System.out.println(i + ": " + sequence);
		}
		// Record end time and total execution time 
		long singleEnd = System.nanoTime();
		singleTime = singleEnd - singleStart;
		System.out.println("\nTotal execution time for single thread: " + singleTime + " nano seconds\n");
	
		
		// Generate 100 random sequences using 5 threads 
		
		// Create 5 GenomeThread threads 
		GenomeThread t1 = new GenomeThread(1);
		GenomeThread t2 = new GenomeThread(2);
		GenomeThread t3 = new GenomeThread(3);
		GenomeThread t4 = new GenomeThread(4);
		GenomeThread t5 = new GenomeThread(5);

		// Track the start time
		long multiStart = System.nanoTime();
		
		// Start each thread 
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		// Use to join to ensure each thread completes before calculating the end execution time and following print statements
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();

		// Record end time and total execution time 
		long multiEnd = System.nanoTime();
		multiTime = multiEnd - multiStart;
		
		System.out.println("\nTotal execution time for 5 threads: "+ multiTime + " nano seconds\n");
		System.out.println("Total execution time for single thread: " + singleTime + " nano seconds\n");
		
		
		// Generate 100 random sequences using 5 threads with an Executor Service 
		
		// Create an executor service to implement 5 threads 
		ExecutorService ex = Executors.newCachedThreadPool();
		
		// Track start time in nano seconds to be percise 
		long multiStartEx = System.nanoTime();
		
		// Execute 5 threads 
		ex.execute(new GenomeThread(1));
		ex.execute(new GenomeThread(2));
		ex.execute(new GenomeThread(3));
		ex.execute(new GenomeThread(4));
		ex.execute(new GenomeThread(5));
	
		
		// Shutdown the executor service
		ex.shutdown();
		
		// Await 1000 ms for threads to complete otherwise shutdown immediately 
		if(!ex.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
			ex.shutdownNow();
		};
		
		// Print out the end time when execution has terminated 
		if(ex.isTerminated()){
			// Record end time and total execution time 
			long multiEndEx = System.nanoTime();
			multiTimeEx = multiEndEx - multiStartEx;
			System.out.println("\nTotal execution time for 5 threads with executor service: " + multiTimeEx + " nano seconds\n");
			System.out.println("Total execution time for 5 threads: "+ multiTime + " nano seconds\n");
			System.out.println("Total execution time for single thread: " + singleTime + " nano seconds\n");
		}
		
	   }
		
}