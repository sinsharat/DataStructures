package com.sharat.datastructures.stacknqueue;

public class BracketProtocolValidator {
		
	public boolean validate(String inputString) throws Exception {
		Stack<Character> bracketsStack = new Stack<Character>(inputString.length()/2);
		for (char a: inputString.toCharArray()) {
			switch(a) {
			
			case '[':
			case '{':
			case '(':
				bracketsStack.push(a);
				break;
			case ']':
				if (bracketsStack.pop() != '[') {
					return false;
				}
				break;
			case '}':
				if (bracketsStack.pop() != '{') {
					return false;
				}
				break;
			case ')':
				if (bracketsStack.pop() != '(') {
					return false;
				}
				break;
			}
		}
		if (!bracketsStack.isEmpty())
		{
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		String inputString = "[a{b}(c)(d){e}[f][({g})]]"; // valid input
		//String inputString = "[{a}b(c)[d]"; // invalid input
		//String inputString = "[{a}b[(]c)[d]]"; // invalid input
		BracketProtocolValidator validator = new BracketProtocolValidator();
		if (validator.validate(inputString)) {
			System.out.println(inputString + " is a valid input.");
		} else {
			System.out.println(inputString + " is an invalid input.");
		}
	}
}
