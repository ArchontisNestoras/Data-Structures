//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;

public class SimpleUnionFind {

    private int[] Pi;     // parent array
    private int N;        // number of items
	private int num;

    SimpleUnionFind(int N) {
        this.N = N;
		this.num=this.N;
        Pi = new int[N + 1];
        for (int k = 0; k <= N; k++) {
            Pi[k] = k;
        }
    }

    int find(int v) {
        /* enter your code */
		int k=v;
		while(k!=Pi[k])
		{
			k=Pi[k];
		}
        return k; // change appropriately 
    }

    void unite(int v, int u) {
        /* enter your code */
		int a=this.find(v);
		int b=this.find(u);
		if(a!=b)
		{
			Pi[b]=a;
			this.num=this.num-1;
		}
    }
    
    int setCount() {
        /* enter your code */
        return this.num; // change appropriately 
    }
    
    void print() {
        System.out.println("Simple Set Union Data Structure");
        for (int k=1; k<=N; k++) {
            System.out.println("Pi["+k+"]="+Pi[k]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Test SimpleUnionFind");

        int N = 16; 
        SimpleUnionFind SUF = new SimpleUnionFind(N);
        
        for (int k=1; k<=3; k++){
            SUF.unite(k+1,k);
            SUF.unite(k+5,k+4);
            SUF.unite(k+9,k+8);
            SUF.unite(k+13,k+12);
        }
        SUF.unite(1,5);
        SUF.unite(9,13);
        SUF.print();
        SUF.unite(1,13);
        SUF.find(2);
        SUF.print();
    }
}
