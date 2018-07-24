import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private Node front;
	private Node back;
	private int size;
	
	public Deque() {
	this.size = 0;
	}
	
	public boolean isEmpty() { return this.size == 0; }
	
	public int size() { return this.size; }
	
	private class LinkedListIterator implements Iterator<Item> {
		Node current = front;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}	
			Item item = current.item;
			current = current.next;
			return item;		
		}	
	}
	
	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		Node node = new Node();
		node.item = item;
		if (size == 0) {
			this.front = node;
			this.back = node;
		}	
		else {
			node.next = this.front;
			this.front.prev = node;
			this.front = node;
		}
		this.size++;
		}
		
		public void addLast(Item item) {
			if (item == null) {
				throw new java.lang.IllegalArgumentException();
			}
			Node node = new Node();
			node.item = item;
			if (size == 0) {
				this.front = node;
				this.back = node;
			}
			else {
				node.prev = this.back;
				this.back.next = node;
				this.back = node;
			}
			this.size++;
		}
		
		public Item removeFirst() {
			if (this.size == 0) {
				throw new java.util.NoSuchElementException();
			}
			else if (this.size == 1) {
				Item item = this.front.item;
				this.front = null;
				this.size--;
				return item;
			}
			else {
				Item item = this.front.item;
				this.front = this.front.next;
				this.front.prev = null;
				this.size--;
				return item;
			}
		}
		
		public Item removeLast() {
			if (this.size == 0) {
				throw new java.util.NoSuchElementException();
			}
			else if (this.size == 1) {
				Item item = this.front.item;
				this.front = null;
				this.size--;
				return item;
			}
			else{
				Item item = this.back.item;
				this.back = this.back.prev;
				this.back.next = null;
				this.size--;
				return item;
			}
		}
			
		
	private class Node {
		Item item;
		Node next;
		Node prev;
	}
	
	public static void main(String[] args) {
		Deque<Integer> d = new Deque<>();
		d.addFirst(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addFirst(4);
		Iterator<Integer> i = d.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		System.out.println(d.removeLast());
		System.out.println(d.removeFirst());
		d.addFirst(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addFirst(4);
		i = d.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		System.out.println(d.removeLast());
		System.out.println(d.isEmpty());
		System.out.println(d.removeFirst());
		System.out.println(d.isEmpty());
	}
}
