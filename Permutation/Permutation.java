import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> r = new RandomizedQueue<>();
		int k = Integer.parseInt(args[0]);
		String input = new String();
			while(!StdIn.isEmpty())
			r.enqueue(StdIn.readString());
		
		
		Iterator<String> i = r.iterator();
		while (i.hasNext() && k!= 0) {
			System.out.println(i.next());
			k--;
		}
	}
}	
		
