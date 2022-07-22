/**
 * Working thread for taking Integer tasks from a shared data structure
 * and calculating the corresponding digit of PI, then storing it in
 * another shared data structure.
 */
public class WorkerThread extends Thread {
    //Shared data structures and Pi computing algorithm.
    private static Bpp bpp = new Bpp();
    private TaskQueue taskQueue;
    private ResultTable resultTable;

    /**
     * WorkingThread constructor. Acquires shared data structures.
     * @param taskQueue
     * @param resultTable
     */
    public WorkerThread(TaskQueue taskQueue, ResultTable resultTable) {
        this.taskQueue = taskQueue;
        this.resultTable = resultTable;
    }

    /**
     * Running method for the thread. Executes upon call of start() method.
     */
    public void run() {
        //Operates while there are still tasks to obtain.
        while (!taskQueue.isEmpty()) {
            try {
                //Acquires permit from shared data structure's semaphore, then retrieves task.
                taskQueue.taskQueueLock.acquire();
                Integer task = taskQueue.pop();
                taskQueue.taskQueueLock.release();
                //If there were no more tasks to do, then it terminates the thread.
                if (task.equals(null)) {
                    return;
                }

                //Calculates the nth digit from pi.
                Integer result = bpp.getDecimal(task);

                //Acquires permit from shared data structure's semaphore, then adds the result.
                resultTable.resultTableLock.acquire();
                resultTable.add(task, result);
                resultTable.resultTableLock.release();

            } catch(InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }
        }
    }
}