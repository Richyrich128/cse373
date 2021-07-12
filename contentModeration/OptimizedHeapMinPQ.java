import java.util.*;

public class OptimizedHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private List<PriorityNode<T>> items;
    private Map<T, Integer> itemToIndex;
    private int size;

    public OptimizedHeapMinPQ() {
        items = new ArrayList<>();
        itemToIndex = new HashMap<>();
        size = 0;
    }

    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        } else if (item == null) {
            throw new IllegalArgumentException("Item cannot be null!");
        }
        items.add(new PriorityNode<>(item, priority));
        size++;
        itemToIndex.put(item, size - 1 );
        swim(size - 1);
    }

    public boolean contains(T item) {
        return itemToIndex.containsKey(item);
    }

    public T peekMin() {
        if (items.isEmpty()) throw new NoSuchElementException("The priority queue is empty!");
        T item = items.get(0).item();
        return item;
    }

    public T removeMin() {
        if (items.isEmpty()) throw new NoSuchElementException("The priority queue is empty!");
        T min = items.get(0).item();
        exch(0, size - 1);
        size--;
        items.remove(size);
        sink(0);
        itemToIndex.remove(min);
        return min;
    }

    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("This item doesn't exist in the priority queue!");
        }
        int index = itemToIndex.get(item);
        PriorityNode<T> node = items.get(index);
        node.setPriority(priority);
        swim(index);
        sink(index);
    }

    public int size() {
        return size;
    }

    private void swim (int index) {
        int parent = (index - 1)/2;
        while (index > 0 && greater(parent, index)) {
            exch(index, parent);
            index = parent;
            parent = (index - 1)/2;
        }
    }

    private void sink (int index) {
        while (2 * index + 1 < size) {
            int child = 2 * index + 1;
            if (child < size - 1 && greater(child,child + 1)) {
                child++;
            }
            if (!greater(index, child)) {
                break;
            }
            exch(index, child);
            index = child;
        }
    }

    private boolean greater(int i, int j) {
        return (items.get(i).priority()) > (items.get(j).priority()) ;
    }

    private void exch(int i, int j) {
        PriorityNode<T> swap = items.get(i);
        itemToIndex.put(swap.item(), j);
        itemToIndex.put(items.get(j).item(), i);
        items.set(i, items.get(j));
        items.set(j, swap);
    }
}
