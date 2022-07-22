import java.util.LinkedList;
import java.util.concurrent.Semaphore;


/**
 * This is a FIFO queue used for holding tasks resembled as Integers.
 */
public class TaskQueue {

    private LinkedList queue;
    public Semaphore taskQueueLock;

    /**
     * TaskQueue Class constructor. Initializes all members.
     */
    public TaskQueue() {
        this.queue = new LinkedList();
        this.taskQueueLock = new Semaphore(1);
    }

    /**
     * Adds a task to the queue
     * @param task
     */
    public void add(Integer task) {
        this.queue.add(task);
    }

    /**
     * Returns the the task from the queue according to FIFO strategy.
     * @return A task from the queue.
     */
    public Integer pop() {
        if (this.queue.size() > 0) {
            Integer task = (Integer) this.queue.get(0);
            this.queue.remove(0);
            return task;
        }
        return null;
    }

    /**
     * Determines whether the list is empty or not.
     * @return True if empty, false if not.
     */
    public Boolean isEmpty() {
        return this.queue.isEmpty();
    }
}