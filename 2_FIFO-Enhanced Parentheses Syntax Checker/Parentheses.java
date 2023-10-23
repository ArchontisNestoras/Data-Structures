//Archontis Nestoras 4747
//Spyridon Chalidias 4830

import java.io.*;

public class Parentheses {

    private static void PrintOutput(Queue<Integer> Q) {
        /* enter your code! */
		System.out.println("Syntax is correct!");
		while(!Q.isEmpty())
		{
			int x=Q.get();
			int y=Q.get();
			System.out.println("range = "+x+","+y);
		}
    }

    private static void ParseInput(String str) {
		int xy=str.length();
        Stack<Pair> S = new Stack<Pair>(xy);
        Queue<Integer> Q = new Queue<Integer>(xy);

        int N = str.length(); // number of characters in str
        for (int i = 0; i < N; i++) {
            char c = str.charAt(i); // character at position i of str
            /* enter your code! */
			if(c=='(' || c=='[' || c=='{' || c==')' || c==']' || c=='}')
			{
				if(c=='(' || c=='[' || c=='{')
				{
					Pair x=new Pair(c,i);
					S.push(x);
					System.out.println("i = "+i+", "+c);
				}
				else if(S.isEmpty())
				{
					System.out.println("Syntax error!");
					return;
				}
				else if(c==')' || c==']' || c=='}')
				{
					Pair y=S.pop();
					char k=y.getC();
					int j=y.getP();
					System.out.println("i = "+i+", "+c);
					if(k=='('&&c==')' || k=='['&&c==']' || k=='{'&&c=='}')
					{
						Q.put(j);
						Q.put(i);
					}
					else
					{
						System.out.println("Syntax error!");
						return;
					}
				}
			}
			else
			{
				continue;
			}
        }
		if(!S.isEmpty())
		{
			System.out.println("Syntax error!");
			return;
		}
        PrintOutput(Q);
    }

    public static void main(String[] args) {
        System.out.print("Enter input string > ");
        In.init();
        String str = In.getString();   // read next character
        System.out.println("Input string = " + str + " , length = " + str.length());

        ParseInput(str);
    }
}
