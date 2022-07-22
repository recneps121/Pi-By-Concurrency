import java.util.HashMap;
import java.util.concurrent.Semaphore;


/**
 * A Hash table to store the results of the threads.
 */
public class ResultTable {
    private HashMap<Integer, Integer> table;
    public Semaphore resultTableLock;

    /**
     * ResultTable constructor: Initializes members.
     */
    public ResultTable() {
        this.table = new HashMap<Integer, Integer>(500);
        this.resultTableLock = new Semaphore(1);
    }

    /**
     * Adds a value to the Hash Table.
     * @param key The look-up key.
     * @param value The thread result.
     */
    public void add(Integer key, Integer value) {
        this.table.put(key, value);
    }

    /**
     * Returns a value from the Hash Table.
     * @param key The look-up key for the value.
     * @return The value requested. Null if otherwise.
     */
    public Integer get(Integer key) {
        return this.table.get(key);
    }

    /**
     * Clears all mappings from the hash table.
     */
    public void clear() {
        this.table.clear();
    }
}
