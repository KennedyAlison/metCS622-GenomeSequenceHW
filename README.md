# Genome Sequences: Single vs Multi Threaded  - CS 622 Homework 4

**Overview**: This project is intended to test the difference between running a single thread and running multiple threads by looking at the difference in execution times. Single versus multi-threading is tested by printing 100 random genome sequences of 10 characters each with 'A', 'G' 'C' or 'T'. The main class, *Genome.java* print out 100 random genome sequences with a single thread, then utilizes two different methods of threading to create 5 threads, each of which print out 20 random genome sequences, printing 100 total. Each of the 5 threads created utilizes another class, *GenomeThread.java*, to print out 20 random genome sequences of 10 characters each. The main *Genome.java* file performs the following actions:

* **Print 100 Random Genome Sequences with a Single Thread:** The main method begins by utilizing the random class to pick a random character, either 'A', 'G' 'C' or 'T', and creates a genome sequences of 10 characters, 100 times. Total execution time is tracked in nano seconds. 
* **Print 100 Random Genome Sequences with 5 Threads:** The main method next creates 5 GenomeThread threads, each of which utilizes the random class to pick a random character, either 'A', 'G' 'C' or 'T', and creates genome sequences of 10 characters, 20 times total. The 5 threads are then started and the *.join()* method is called on each thread to ensure the thread execution is terminated before the next instruction (i.e. recording end execution time) is called. Total execution time is tracked in nano seconds. 
* **Print 100 Random Genome Sequences with 5 Threads using Executor Service:** Next the main method next creates a Thread Pool using Executor Service and calls the *.execute()* method to create 5 GenomeThread threads, each of which utilizes the random class to pick a random character, either 'A', 'G' 'C' or 'T', and create genome sequences of 10 characters, 20 times total. The Executor Service is then shutdown, using *awaitTermination()* to ensure completion of thread execution. Total execution time is tracked in nano seconds. The main method throws an InterruptedException in case thread execution is interrupted.

<br />

**Concepts Used**
<br />
<br />
**Threading:**
* I extended the thread class in both the *Genome.java* and *GenomeThread.java* classes, as all actions performed in this project are related to threading
* As the Thread class implements Runnable, I have overriden the *run()* method within the *GenomeThread.java* class to produce 20 random genome sequences 
* After creating the 5 threads, I call the *.start()* method to begin the execution of each of the 5 threads
* The *.join()* method is also called on each of the 5 threads to ensure execution of all 5 threads is terminated before getting the ending run time

**Threading with Executor Service:**
* I utilized the Exeuctor Service interface that allows the maintenance of a pool of threads for tasks to be executed on  
* The *.execue()* method is used to launch a specified task, the creation of 20 random genome sequences, by a Runnable object - a thread
* I utilized the *.shutdown()* method to shutdown the Executor Service after thread execution, allowing the execution of submitted tasks to finish, but no longer accepting newly submitted tasks
* I call the *awaitTermination()* method after the shutdown method to allow the threads to finish executing. I set a timeout of 1000 ms and then call *shutdownNow() to shutdown the execution of all running tasks after the 1000 ms timeout 
* Lastly I use the *isTerminated()* method to confirm that the execution has halted before recording the end time and printing out the total execution time

## Instructions 

1. Run the program from the main *Genome.java* file and observe the console output and difference in execution times
2. If a modification wants to be made to the number of random genome sequences printed to the console, change the first for loop on line 29 in the *Genome.java* class to print *n* number of random genome sequences and then modify the first for loop on line 23 in the *GenomeThread.java* class to print *n // 5* random genome sequences by each of the 5 threads

### Prerequisites

Ensure you are running Java Standard Edition Development Kit v.8 (JDK 8). If you are running from command line, you can check you version of java by running the following:

```
java -version 
```

## Deployment

N/A

## Contributing

N/A

## Versioning

N/A

## Authors

* **Alison Kennedy** 


## License


N/A

