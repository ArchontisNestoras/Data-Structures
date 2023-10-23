//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;

public class BipartiteGraph {

    private final int N;                // number of vertices
    private int K;                      // number of edges
    private Collection<Integer>[] adj;  // adjacency lists
	private int[] Parent;
	private boolean[] color;
	private boolean[] marked;

    // construct graph with N nodes and no edges
    public BipartiteGraph(int N) {
        this.N = N;
        this.K = 0;
        adj = (Collection<Integer>[]) new Collection[N];  // array of references to collections
		Parent = new int[N];
		marked=new boolean[N];
		color=new boolean[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new Collection<Integer>();   // initialize collections to be empty
			marked[i]=false;
        }
    }

    public int nodes() // return the number of nodes
    {
        return N;
    }

    public int edges() // return the number of edges
    {
        return K;
    }

    // add edge {v,w}
    public void addEdge(int v, int w) {
        adj[v].insert(w);
        adj[w].insert(v);
        K++;
    }

    // nodes adjacent to node v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // print adjacency lists
    public void printGraph() {
        System.out.println("adjacency lists");
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            for (int w : adj(v)) {
                System.out.print(w + " ");
            }
            System.out.println("");
        }
    }

    // breadth-first search from vertex s
    void bfs(int s) {
        /* enter your code */
		Queue<Integer> Q = new Queue<Integer>(K);
		marked[s]=true;
		color[s]=true;
		Q.put(s);
		while(!Q.isEmpty()){
			int v=Q.get();
			for(int w:adj(v)){
				if(marked[w]==false){
					marked[w]=true;
					//color[w]=!(color[v]||false);
					color[w]=!color[v];
					Parent[w]=v;
					Q.put(w);
				}
				if(color[w]==color[v]){
					System.out.println("Input graph is NOT Bipartite!");
					printCycle(findOddCycle(v,w));
					return;
				}
			}
		}
		System.out.println("Input graph is Bipartite!");
    }

    // find odd-length cycle containing edge (u,v)
    Queue<Integer> findOddCycle(int u, int v) {
        Queue<Integer> C = new Queue<Integer>(K);
        /* enter your code */
		Integer [] x=new Integer[N];
		int k=0;
		int c=1;
		x[0]=u;
		C.put(u);
		while(u!=0){
			k=Parent[u];
			u=k;
			x[c]=k;
			C.put(k);
			c++;
		}
		Integer [] y=new Integer[N];
		y[0]=v;
		int l=0;
		int count=1;
		while(v!=0){
			l=Parent[v];
			v=l;
			y[count]=l;
			count++;
		}
		for(int i=count-1;i>=0;i--){
			if(y[i]==0){
				continue;
			}else{
				C.put(y[i]);
			}
		}
		C.put(x[0]);
        return C;
    }

    // print cycle stored in queue C
    void printCycle(Queue<Integer> C) {
        /* enter your code */
		System.out.println("Odd cycle found");
		System.out.println();
		System.out.print("	Cycle: ( ");
		while(!C.isEmpty()){
			System.out.print(C.get()+" ");
		}
		System.out.println(")");
		System.out.println();
    }

    public static void main(String[] args) {
        In.init();
        int N = In.getInt();
        BipartiteGraph G = (BipartiteGraph) new BipartiteGraph(N);
        int K = In.getInt();
        for (int i = 0; i < K; i++) {
            int v = In.getInt();
            int w = In.getInt();
            G.addEdge(v, w);
        }
        //G.printGraph();
        G.bfs(0);
    }
}
