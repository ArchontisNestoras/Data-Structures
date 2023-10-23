//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;
import java.util.Random;

// minimum priority queue implemented with a binary heap
public class MinPQ<Item, Key extends Comparable<Key>> {

    private int N;    // number of items on priority queue
    private Key  pqK[]; // binary heap of keys
    private Item pqI[]; // binary heap of items

    public MinPQ(int maxN) {
        pqK = (Key[]) new Comparable[maxN + 1];
        pqI = (Item[]) new Object[maxN + 1];
        N = 0;
    }
 
    // return the number of elements in the priority queue
    public int size() {
        return N;
    }
	
	private void fixUp(int x){
		while(x>1 && pqK[x].compareTo(pqK[x/2])<=0){
			Key z=pqK[x];
			Item zz=pqI[x];
			pqK[x]=pqK[x/2];
			pqI[x]=pqI[x/2];
			x=x/2;
			pqK[x]=z;
			pqI[x]=zz;
		}
	}
	
	private void fixDown(int x){
		int j;
		Key z;
		Item zz;
		while((2*x)<N){
			j=2*x;
			if(pqK[j].compareTo(pqK[j+1])<=0){
				if(pqK[x].compareTo(pqK[j])>0){
					z=pqK[x];
					zz=pqI[x];
					pqK[x]=pqK[j];
					pqI[x]=pqI[j];
					pqK[j]=z;
					pqI[j]=zz;
				}
			}else{
				j=j+1;
				if(pqK[x].compareTo(pqK[j])>0){
					z=pqK[x];
					zz=pqI[x];
					pqK[x]=pqK[j];
					pqI[x]=pqI[j];
					pqK[j]=z;
					pqI[j]=zz;
				}
			}
			x=j;
		}
	}
	
    // insert item v with key k
    public void insert(Item v, Key k) {
        /* enter your code! */
		pqK[++N]=k;
		pqI[N]=v;
		fixUp(N);
		int n=N;
		while(n>1){
			if(pqK[n].compareTo(pqK[n-1])>=0){
				n=n-1;
			}else{
				Key z=pqK[n];
				Item zz=pqI[n];
				pqK[n]=pqK[n-1];
				pqI[n]=pqI[n-1];
				pqK[n-1]=z;
				pqI[n-1]=zz;
				n=N;
			}
		}
    }

    // return the item with minimum key
    public Item minItem() {
        return pqI[1];
    }
    
    // return the minimum key
    public Key minKey() {
        return pqK[1];
    }
    
    // delete the item with minimum key
    public void delMin() {
        /* enter your code! */
		pqK[1]=pqK[N];
		pqI[1]=pqI[N];
		pqK[N]=null;
		pqI[N--]=null;
		fixDown(1);
		int n=N;
		while(n>1){
			if(pqK[n].compareTo(pqK[n-1])>=0){
				n=n-1;
			}else{
				Key z=pqK[n];
				Item zz=pqI[n];
				pqK[n]=pqK[n-1];
				pqI[n]=pqI[n-1];
				pqK[n-1]=z;
				pqI[n-1]=zz;
				n=N;
			}
		}
    }

    private void printPQ() {
        System.out.println("");
        for (int i = 1; i <= N; i++) {
            System.out.println("" + i + ": item = " + pqI[i] + ", key = " + pqK[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Test Min Priority Queue implemented with a Binary Heap");

        long startTime = System.currentTimeMillis();
        MinPQ<Character,Integer> PQ = new MinPQ<Character,Integer>(26);
        Random rand = new Random(0);

        System.out.println("");
        System.out.println("Inserting keys");
        System.out.println("");
        for (int i = 1; i <= 26; i++) {
            int key = rand.nextInt(100); // assign random keys
            char c = (char) (96 + i);
            System.out.println("insert item " + c + " key " + key);
            PQ.insert(c, key);
        }
        //PQ.printPQ();
        
        System.out.println("");
        System.out.println("Deleting minimum keys");
        System.out.println("");
        while (PQ.size()>0) {
            char c = (char) PQ.minItem();
            int k = PQ.minKey();
            PQ.delMin();
            System.out.println("item " + c + " key " + k);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time = " + totalTime);
    }
}
