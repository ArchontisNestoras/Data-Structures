//Archontis Nestoras 4747
//Spyridon Chalidias 4830

public class Stack<Item> {
	private int maxN;
	private Item[] A;
	private int N;
	
    Stack(int max) {
        /* enter your code! */
		maxN=max;
		A=(Item[])new Object[max];
		N=0;
    }

    boolean isEmpty() {
        return N==0; // change appropriately
    }

    // insert new item on top of the stack
    void push(Item item) {
        /* enter your code! */
		if(N<=maxN-1)
		{
			A[N++]=item;
		}
    }

    // extract most recent item from the top of the stack
    Item pop() {
        /* enter your code */
		Item t=A[--N];
		A[N]=null;
        return t; // change appropriately
    }
}