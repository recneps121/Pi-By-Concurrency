/**
 * Main runner class for computing Pi to the 1000th digit.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Creates shared data structures.
        TaskQueue taskQueue = new TaskQueue();
        ResultTable resultTable = new ResultTable();

        //Starts timer and fills up task queue with tasks.
        Long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            taskQueue.add(i);
        }

        //Gets the number of cores, which is how many threads will be used.
        int numCores = Runtime.getRuntime().availableProcessors();

        //Creates all the threads and starts them.
        WorkerThread[] threads = new WorkerThread[numCores];
        for (int i = 0; i < numCores; i++) {
           threads[i] =  new WorkerThread(taskQueue, resultTable);
           threads[i].start();
        }

        //Waits for all the threads to terminate.
        for (int i=0; i < numCores; i++) {
            threads[i].join();
        }

        //Extracts the results from the table and stores them in a string.
        StringBuilder sb = new StringBuilder();
        sb.append("3.");
        for (int i=1; i <= 1000; i++) {
            Integer digit = resultTable.get(i);
            sb.append(digit.toString());
			if (i % 10 == 0) {
				System.out.print(".");
			}
            if (i % 100 == 0) {
                sb.append("\n");
            }
        }
		
		System.out.flush();
		System.out.println("");		

        //Prints Pi to the 1000th digit. Ends timer.
        System.out.println(sb.toString());
        Long endTime = System.currentTimeMillis();

        //Calculates time elapsed and prints to the screen.
        Long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("Total time taken:(seconds) " + elapsedTime.toString());
    }
}
