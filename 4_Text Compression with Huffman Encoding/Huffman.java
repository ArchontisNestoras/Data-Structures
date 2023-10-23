//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;

public class Huffman {

    private int n;          // number of different letters
    private char[] C;       // array containing all letters from a to z
    private int[] F;        // array containing the frequency (number of occurencies) of each letter
    private String[] code;  // array containing the Huffman code of each letter

    // Huffman tree node
    private class Node {

        private char c;     // letter stored at node
        private Node left;  // left child
        private Node right; // right child

        // create new tree node
        Node(char c, Node left, Node right) {
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }

    public Huffman(int N) {
        n = N;
        // initialize C with the characters from a to z
        C = new char[n + 1];
        char j = 'a';
        for (int i = 1; i <= n; i++, j++) {
            C[i] = j;
        }

        // initialize F with 0
        F = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            F[i] = 0;
        }

        // initialize code
        code = new String[n + 1];
        for (int i = 1; i <= n; i++) {
            code[i] = "";
        }
    }

    // count the letters in word s
    private void processWord(String s) {
        int c;
        for (int i = 0; i < s.length(); i++) {
            // if letter c is in upper case convert it to lower case
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                c = s.charAt(i) + 32;
                F[c - 96]++;
            } // do not convert if already in lower case
            else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                c = s.charAt(i);
                F[c - 96]++;
            }
        }
    }

    // print the total number of occurrences of each letter
    private void printCount() {
        for (int i = 1; i <= n; i++) {
            System.out.println("letter = " + C[i] + ", count = " + F[i]);
        }
        System.out.println("");
    }

    // print Huffman code
    private void printCode() {
        for (int i = 1; i <= n; i++) {
            if (F[i] != 0) {
                System.out.println("letter " + C[i] + ", code = " + code[i]);
            }
        }
    }

   
    // encode letters by running Huffman algorithm 
    private void encode() {
        MinPQ<Node, Integer> PQ = new MinPQ<Node, Integer>(n);
        for (int i = 1; i <= n; i++) {
            if (F[i] == 0) { // ignore letters that do not appear in input file
                continue;
            }
            Node x = new Node(C[i], null, null); // construct new leaf node
            //System.out.println("insert item " + C[i] + " key " + F[i]);
            PQ.insert(x, F[i]);
        }
        /* enter your code! */
		Node zzz=new Node(' ',null,null);
		while(PQ.size()>1){
			int x=PQ.minKey();
			Node xx=PQ.minItem();
			PQ.delMin();
			int y=PQ.minKey();
			Node yy=PQ.minItem();
			PQ.delMin();
			int z=x+y;
			Node zz=new Node(' ',yy,xx);
			PQ.insert(zz,z);
			zzz=zz;
		}
		preorder(zzz,"");
	}
	
	public void preorder(Node node,String s_code){
		if(node.left==null && node.right==null){
			for(int i=1;i<=n;i++){
				if(node.c==C[i]){
					code[i]=s_code;
				}
			}
			return;
		}
		preorder(node.left, s_code+"0");
		preorder(node.right, s_code+"1");
	}

    public static void main(String[] args) {
        System.out.println("Test Huffman coding");

        Huffman H = new Huffman(26);

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            if (s.isEmpty()) {
                continue;
            }
            H.processWord(s);
        }
        H.printCount();
        H.encode();
        H.printCode();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime);
    }
}
