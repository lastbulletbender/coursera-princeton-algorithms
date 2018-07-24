import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item arr[];
	private int sizeOfArr;
	private int numberOfElements;
	private int currentPointer;
	
	public RandomizedQueue() {
		arr = (Item[]) new Object[1];
		sizeOfArr = 1;
		numberOfElements = 0;
		currentPointer = 0;
	}
	
	public java.util.Iterator<Item> iterator() {
		return new RandomArrayIterator();
	}
	
	public boolean isEmpty() { return numberOfElements == 0; }
	
	public int size() { return numberOfElements; }
	
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		arr[currentPointer++] = item;
		numberOfElements++;
		if (currentPointer == sizeOfArr) {
			resize(sizeOfArr * 2);
		}
	}
	
	private class RandomArrayIterator implements Iterator<Item> {
		
		private Item randomArray[];
		private int counter = -1;
				
		RandomArrayIterator() {
			randomArray = (Item[]) new Object[numberOfElements];
			for (int i = 0; i < sizeOfArr; i++) {
				if (arr[i] != null) {
					randomArray[++counter] = arr[i];
				}
			}
			StdRandom.shuffle(randomArray);
		}		
		
		public boolean hasNext() { return (counter != -1); }
		
		public Item next() {
			if (counter == -1) {
				throw new java.util.NoSuchElementException();
			}
			return randomArray[counter--];
		}
	}
	
	public Item dequeue() {
	if (numberOfElements == 0) {
		throw new java.util.NoSuchElementException();
	}
	int index;
	do {
		index = StdRandom.uniform(currentPointer);
	} while (arr[index] == null);
	Item item = arr[index];
	arr[index] = null;
	numberOfElements--;
	if (numberOfElements <= (1/4 * sizeOfArr)) {
		resize(sizeOfArr / 2);
	} 
	return item;
	}
	
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		int j = 0;
		for (int i = 0; i < sizeOfArr; i++) {
			if (arr[i] != null) {
				copy[j++] = arr[i];
			}
		}
		currentPointer = j;
		arr = copy;
		sizeOfArr = arr.length;
	}
	
	public Item sample() {
		if (numberOfElements == 0) {
			throw new java.util.NoSuchElementException();
		}
		int index;
		do {
			index = StdRandom.uniform(currentPointer);
		} while (arr[index] == null);
		return arr[index];
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> r = new RandomizedQueue<>();
		r.enqueue(1);
		r.enqueue(2);
		r.enqueue(3);
		r.enqueue(4);
		r.enqueue(5);
		r.enqueue(6);
		r.enqueue(7);
		Iterator<Integer> i = r.iterator();
		Iterator<Integer> j = r.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		while(j.hasNext()) {
			System.out.println(j.next());
		}
		r.sample();
	}
}
	
	
