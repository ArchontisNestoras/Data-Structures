//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;
import java.util.Random;

public class RedBlackTree<Key extends Comparable<Key>, Item> {

    BSTreeNode root;        // root of binary search tree

    class BSTreeNode {

        Key key;            // key associated with the item stored at node
        Item item;          // item stored at node
        BSTreeNode left;    // left child
        BSTreeNode right;   // right child
        BSTreeNode parent;  // node's parent
        int height;         // node's height
        int N;              // number of descendants
		boolean isRed;

        // create new node
        BSTreeNode(Key key, Item item, BSTreeNode parent) {
            this.key = key;
            this.item = item;
            this.parent = parent;
            this.height = 1;
            this.N = 1;
			this.isRed = true;
        }
    }

    // search for item with key; returns the last node on the search path 
    BSTreeNode searchNode(Key key) {
        BSTreeNode v = root;
        BSTreeNode pv = null; // parent of v
        while (v != null) {
            int c = key.compareTo(v.key);  // compare with the key of node v
            pv = v;
            if (c < 0) {
                v = v.left;
            } else if (c > 0) {
                v = v.right;
            } else {
                return v; // item found; return node that contains it
            }
        }
        return pv; // item not found; return last node on the search path
    }

    // search for item with key
    public Item search(Key key) {
        if (root == null) {
            return null; // tree is empty
        }
        BSTreeNode v = searchNode(key);
        int c = key.compareTo(v.key);
        if (c == 0) {
            return v.item;    // item found
        } else {
            return null;      // item not found
        }
    }

    // return the height of a node x; if x is null return 0
    private int getHeight(BSTreeNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.height;
        }
    }

    // return the number of descendants of a node x; if x is null return 0
    private int getN(BSTreeNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }
    
    // update the height and the number of descendants of a node
    private void updateNode(BSTreeNode x) {
        int leftHeight = getHeight(x.left);
        int rightHeight = getHeight(x.right);
        int bf = leftHeight - rightHeight; // balance factor
        if (bf < 0) {
            x.height = rightHeight + 1;
        } else {
            x.height = leftHeight + 1;
        }
        
        int leftN = getN(x.left);
        int rightN = getN(x.right);
        x.N = leftN + rightN + 1;
    }
    
    // update the height v's ancestors
    private void updatePath(BSTreeNode v) {
        BSTreeNode u = v;
        while (u != null) {
            updateNode(u);
            u = u.parent;
        }
    }
    
    // return the height of the binary search tree
    int getTreeHeight() {
        return getHeight(root);
    }

    // insert item with key and return inserted node
    BSTreeNode insertNode(Key key, Item item) {
        if (root == null) { // tree is empty
            root = new BSTreeNode(key, item, null);
            return root;
        }

        BSTreeNode v = searchNode(key); // v is the last node on the search path
        int c = key.compareTo(v.key);
        if (c == 0) { // key already exists in v; overwrite node's item with new item
            v.item = item;
			return v;
        }

        BSTreeNode u = new BSTreeNode(key, item, v); // new node becomes child of v
        if (c < 0) {
            v.left = u;
        } else {
            v.right = u;
        }
        
        return u;
    }

    // insert item with key
    public void insert(Key key, Item item) {
        BSTreeNode v = insertNode(key, item);
		this.fix(v);
		updatePath(v);
    }
	
	private BSTreeNode rotateLeft(BSTreeNode x) {
		BSTreeNode y = x.right;
		x.right = y.left;
		if(x.right != null) {
			x.right.parent = x;
		}
		y.left = x;
		y.parent = x.parent;
		if(x.parent != null) {
			if(x.parent.left == x) {
				x.parent.left = y;
			} else{
				x.parent.right = y;
			}
		}else{
			root=y;
		}
		x.parent = y;
		updateNode(x);
		updateNode(y);
		return y;
	}
	
	private BSTreeNode rotateRight(BSTreeNode y) {
		BSTreeNode x = y.left;
		y.left = x.right;
		if (y.left != null) {
			y.left.parent = y;
		}
		x.right = y;
		x.parent = y.parent;
		if (y.parent != null) {
			if (y.parent.left == y) {
				y.parent.left = x;
			} else {
				y.parent.right = x;
			}
		}else{
			root=x;
		}
		y.parent = x;
		updateNode(y);
		updateNode(x);
		return x;
	}

	private void fix(BSTreeNode x){
		if(x==root){
			x.isRed=false;
			return;
		}
		BSTreeNode y;
		y=x.parent;
		if(y.parent==null){
			return;
		}
		if(y.isRed==false){
			updatePath(y);
			return;
		}
		BSTreeNode z;
		BSTreeNode uncle=null;
		BSTreeNode w;
		z=y.parent;
		w=y.parent;
		if(y==z.left){
			uncle=z.right;
		}else{
			uncle=z.left;
		}
		while(true){
			if(uncle==null || uncle.isRed==false){
				if(x==y.left){
					if(y==z.left){
						z.isRed=true;
						y.isRed=false;
						rotateRight(z);
						updatePath(w);
						break;
					}else{
						rotateRight(y);
						z.isRed=true;
						x.isRed=false;
						rotateLeft(z);
						updatePath(w);
						break;
					}
				}else{
					if(y==z.left){
						rotateLeft(y);
						z.isRed=true;
						x.isRed=false;
						rotateRight(z);
						updatePath(w);
						break;
					}else{
						z.isRed=true;
						y.isRed=false;
						rotateLeft(z);
						updatePath(w);
						break;
					}
				}
			}else{
				if(z==root){
					z.isRed=false;
					y.isRed=false;
					uncle.isRed=false;
					updatePath(w);
					break;
				}else{
					z.isRed=true;
					y.isRed=false;
					uncle.isRed=false;
					if(z.parent.isRed==true){
						x=y.parent;
						y=z.parent;
						z=z.parent.parent;
						if(y==z.left){
							uncle=z.right;
						}else{
							uncle=z.left;
						}
					}else{
						break;
					}
				}
			}
		}
	}

    // inorder traversal: prints the key of each node
    void printNode(BSTreeNode v, int level) {
        if (v == null) {
            return;
        }
        printNode(v.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.print("" + v.key + "(" + v.height + "," + v.N + ")");
		if(v.isRed==true){
			System.out.println("[red]");
		}else{
			System.out.println("[black]");
		}
        printNode(v.left, level + 1);
    }

    // print binary tree
    public void print() {
        System.out.println("Printing binary search tree");
        System.out.println("");
        printNode(root, 0);
        System.out.println("");
    }
	
	public BSTreeNode getRoot(){
		return root;
	}
	
	private int rank(Key k, BSTreeNode x){
		if(x==null){
			return 0;
		}
		int c=k.compareTo(x.key);
		if(c<0){
			return rank(k,x.left);
		}
		else if(c>0){
			return 1+getN(x.left)+rank(k,x.right);
		}
		else{
			if(x.left==null && x.right==null){
				return 0;
			}
			return getN(x.left);
		}
	}
	
	public int rank(Key k){
		return rank(k,root);
	}
	
	public int range(Key k,Key m){
		if(this.search(k)==null && this.search(m)==null){
			return rank(m)-rank(k);
		}
		else if((this.search(k)!=null && this.search(m)!=null) || this.search(k)==null){
			return rank(m)-rank(k)+1;
		}
		else{
			return rank(m)-rank(k)+2;
		}
	}

    public static void main(String[] args) {
        System.out.println("Test Binary Search Tree");
        int n = Integer.parseInt(args[0]);
        System.out.println("number of keys n = " + n);

        RedBlackTree T = new RedBlackTree<Integer, String>();

        Random rand = new Random(0);
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) { // store n random numbers in [0,2n)
        	keys[i] = rand.nextInt(2*n);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String item = "item" + i;
            T.insert(keys[i], item);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("construction time = " + totalTime);
        //T.print();
		
        System.out.println("tree height = " + T.getTreeHeight());

		System.out.println("keys in range ["+n/4+","+3*n/4+"] = "+ T.range(n/4,3*n/4));
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if (T.search(keys[i]) == null) {
                System.out.println("key " + keys[i] + " not found!");
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("search time = " + totalTime);
    }
}
