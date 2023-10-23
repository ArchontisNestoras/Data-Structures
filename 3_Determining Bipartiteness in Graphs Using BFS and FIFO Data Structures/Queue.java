//Archontis Nestoras 4747
//Spyridon Chalidias 4830

class Queue<Item> {
    private Item[] q;
	private int N;
	private int head;
	private int tail;
	
    Queue(int max) {
        /* enter your code! */
		q=(Item[])new Object[max];
		N=max+1;
		head=N;
		tail=0;
    }
    
    boolean isEmpty() {
        return (head%N==tail); // change appropriately
    }

    // insert new item in the queue
    void put(Item item) {
        /* enter your code! */
		q[tail++]=item;
		tail=tail%N;
    }

    // extract least recent item from the queue
    Item get() {
        /* enter your code */
		head=head%N;
		Item t=q[head++];
        return t; // change appropriately
    }
}