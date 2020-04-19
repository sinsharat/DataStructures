package com.sharat.ds.stacknqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class InToPost {

	private Stack<Character> theStack;
	private Stack<Integer> intStack;
	private String input;
	private String output = "";

	public InToPost(String in) // constructor
	{
		input = in;
	}

	public String doTrans() // do translation to postfix
	{
		theStack = new Stack<Character>();
		for (int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			// theStack.displayStack("For "+ch+" "); // *diagnostic*
			switch (ch) {
			case '+': // it's + or -
			case '-':
				gotOper(ch, 1); // go pop operators
				break; // (precedence 1)
			case '*': // it's * or /
			case '/':
				gotOper(ch, 2); // go pop operators
				break; // (precedence 2)
			case '(': // it's a left paren
				theStack.push(ch); // push it
				break;
			case ')': // it's a right paren
				gotParen(ch); // go pop operators
				break;
			default: // must be an operand
				output = output + ch; // write it to output
				break;
			} // end switch
		} // end for
		while (!theStack.isEmpty()) // pop remaining opers
		{
			// theStack.displayStack("While "); // *diagnostic*
			output = output + theStack.pop(); // write to output
		}
		// theStack.displayStack("End "); // *diagnostic*
		return output; // return postfix
	} // end doTrans()

	public void gotOper(char opThis, int prec1) { // got operator from input
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') // if it's a '('
			{
				theStack.push(opTop); // restore '('
				break;
			} else // it's an operator
			{
				int prec2; // precedence of new op
				if (opTop == '+' || opTop == '-') // find new op prec
					prec2 = 1;
				else
					prec2 = 2;
				if (prec2 < prec1) // if prec of new op less
				{ // than prec of old
					theStack.push(opTop); // save newly-popped op
					break;
				} else // prec of new not less
					output = output + opTop; // than prec of old
			} // end else (it's an operator)
		} // end while
		theStack.push(opThis); // push new operator
	} // end gotOp()

	public void gotParen(char ch) { // got right paren from input
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(') // if popped '('
				break; // we're done
			else // if popped operator
				output = output + chx; // output it
		} // end while
	} // end popOps()

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public int doParse() {
		intStack = new Stack<Integer>(); // make new stack
		char ch;
		int j;
		int num1, num2, interAns;
		for (j = 0; j < input.length(); j++) // for each char,
		{
			ch = input.charAt(j); // read from input
			// intStack.displayStack("" + ch + " "); // *diagnostic*
			if (ch >= '0' && ch <= '9') // if it's a number
				intStack.push((int) (ch - '0')); // push it
			else // it's an operator
			{
				num2 = intStack.pop(); // pop operands
				num1 = intStack.pop();
				switch (ch) // do arithmetic
				{
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				} // end switch
				intStack.push(interAns); // push result
			} // end else
		} // end for
		interAns = intStack.pop(); // get answer
		return interAns;
	} // end doParse()

	public static void main(String[] args) throws IOException {
		String input, output;
		int result;
		input = "A*B+(C/D*(E+F))*G";
		InToPost theTrans = new InToPost(input);
		output = theTrans.doTrans(); // do the translation
		System.out.println("Postfix is " + output + '\n');
		input = "1*3+(5/7*(11+13))*17";
		InToPost theTrans1 = new InToPost(input);
		result = theTrans1.doParse(); // do the translation
		System.out.println("Result is " + result + '\n');
	} // end main()
} // end class InfixApp
