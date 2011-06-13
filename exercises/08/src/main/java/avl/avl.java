package avl;

import java.util.LinkedList;

public class avl {

	public static int[] getOrder(int[] L) {
		int[] order = new int[L.length];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		for(int e : L)
			Q.addLast(e);

		System.out.println("Contents of queue in each iteration");

		int i=0;
		while (!Q.isEmpty()) {
			System.out.println("i = "+i+"\tQ = "+Q);

			Integer[] list = Q.toArray(new Integer[0]);

			Q.clear();

			order[i] = list[list.length/2];
			if(list.length>1)
				for(int j=0; j<list.length/2; j++)
					Q.addLast(list[j]);

			if(list.length>2)
				for(int j=list.length/2+1; j<list.length; j++)
					Q.addLast(list[j]);

			i++;
		}
		return order;
	}

	public static void main (String [] args) {
		int[] list = new int[]{3, 7, 13, 15, 21, 33, 40, 47};
		int[] order = getOrder(list);

		System.out.println("");
		System.out.print("Order = [");
		for(int e: order)
			System.out.print(e+", ");
		System.out.println("]");
	}
}
