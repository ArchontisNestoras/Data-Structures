//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;

public class UnionFind {

    private int[] Pi;     // parent array
    private int[] size;   // size of each set 
    private int N;        // number of items
	private int num;

    UnionFind(int N) {
        this.N = N;
		this.num=this.N;
        Pi = new int[N + 1];
        size = new int[N + 1];
        for (int k = 0; k <= N; k++) {
            Pi[k] = k;
            size[k] = 1;
        }
    }

    int find(int v) {
        /* enter your code */
		int k=v;
		int a=v;
		while(k!=Pi[k])
		{
			k=Pi[k];
		}
		while(a!=Pi[a])
		{
			int x=a;
			a=Pi[a];
			Pi[x]=k;
		}
        return k; // change appropriately 
    }

    void unite(int v, int u) {
        /* enter your code */
		int k=find(v);
		int l=find(u);
		if(k!=l)
		{
			if(size[k]>=size[l])
			{
				Pi[l]=k;
				size[k]+=size[l];
				this.num=this.num-1;
			}
			else
			{
				Pi[k]=l;
				size[l]+=size[k];
				this.num=this.num-1;
			}
		}
    }

    int setCount() {
        /* enter your code */
        return this.num; // change appropriately 
    }
    
    void print() {
        System.out.println("Set Union Data Structure");
        for (int k=1; k<=N; k++) {
            System.out.println("Pi["+k+"]="+Pi[k]);
            if (Pi[k]==k) System.out.println("size["+k+"]="+size[k]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Test UnionFind");

        int N = 16; 
        UnionFind UF = new UnionFind(N);
        
        for (int k=1; k<=3; k++){
            UF.unite(k+1,k);
            UF.unite(k+5,k+4);
            UF.unite(k+9,k+8);
            UF.unite(k+13,k+12);
        }
        UF.unite(1,5);
        UF.unite(9,13);
        UF.print();
        UF.unite(1,13);
        UF.find(2);
        UF.print();
    }
}
