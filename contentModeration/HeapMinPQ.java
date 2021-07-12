import java.util.*;

public class HeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private final PriorityQueue<PriorityNode<T>> pq;

    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::priority));
    }

    public void add(T item, double priority) {
        // TODO: Your code here!
        if (contains(item)) {
            throw new IllegalArgumentException();
        } else if (item == null) {
            throw new IllegalArgumentException();
        }
        pq.add(new PriorityNode<>(item, priority));
    }

    public boolean contains(T item) {
        // TODO: Your code here!

        return pq.contains(new PriorityNode<>(item, 0));

    }

    public T peekMin() {
        // TODO: Your code here!
        if(!pq.isEmpty()) {
            PriorityNode<T> checkPriority = pq.peek();
            return checkPriority.item();
        }
        return null;
    }

    public T removeMin() {
        // TODO: Your code here!
        if(!pq.isEmpty()) {
            return pq.remove().item();
        }
        return null;
    }

    public void changePriority(T item, double priority) {
        // TODO: Your code here
        if(contains(item)) {
            pq.remove(new PriorityNode<>(item, 0));
            pq.add(new PriorityNode<>(item, priority));
        }

    }

    public int size() {
        // TODO: Your code here!
        return pq.size();
    }
}
