package com.sharat.ds.stacknqueue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfixToPostfix {

	private static Map<Character, Integer> priorityMap = new ConcurrentHashMap<Character, Integer>();

	static {
		priorityMap.put('-', 0);
		priorityMap.put('+', 1);
		priorityMap.put('*', 2);
		priorityMap.put('/', 3);
		priorityMap.put('(', 4);
		priorityMap.put(')', 4);
	}

	public String convert(String infix) throws Exception {
		int infixLength;
		if (null == infix || (infixLength = infix.length()) == 0) {
			return infix;
		}
		infix = infix.replace(" ", "");
		char b;
		StringBuilder postFixBuilder = new StringBuilder();
		Stack<Character> operatorStack = new Stack<Character>(infixLength);
		for (int i = 0; i < infixLength; i++) {
			char a = infix.charAt(i);
			if (priorityMap.get(a) != null) {
				if (!operatorStack.isEmpty() && isOperatorAppendEligible((b = operatorStack.peek()), a)) {
					if (b != '(' && b != ')') {
						postFixBuilder.append(operatorStack.pop());
					} else if (b == ')') {
						operatorStack.pop();
						while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
							postFixBuilder.append(operatorStack.pop());
						}
						operatorStack.pop();
					}
				}
				operatorStack.push(a);
			} else {
				postFixBuilder.append(a);
			}
		}
		while (!operatorStack.isEmpty()) {
			b = operatorStack.pop();
			if (b != '(' && b != ')') {
				postFixBuilder.append(b);
			}
		}
		return postFixBuilder.toString();
	}

	private boolean isOperatorAppendEligible(char a, char b) {
		return priorityMap.get(a) >= priorityMap.get(b);
	}

	public double calculateExpression(String expression) throws Exception {
		int expressionLength;
		if (null == expression || (expressionLength = (expression = expression.replace(" ", "")).length()) == 0) {
			throw new IllegalArgumentException();
		}
		char b;
		String number = "";
		Stack<Character> operatorStack = new Stack<Character>(expressionLength);
		Stack<Double> numbersList = new Stack<Double>(expressionLength);
		Double last, previous;
		for (int i = 0; i < expressionLength; i++) {
			char a = expression.charAt(i);
			if (priorityMap.containsKey(a)) {
				if (!"".equals(number)) {
					numbersList.push(Double.valueOf(number));
					number = "";
				}
				if (!operatorStack.isEmpty() && isOperatorAppendEligible((b = operatorStack.peek()), a)) {
					if (b != '(' && b != ')') {
						last = numbersList.pop();
						previous = numbersList.pop();
						numbersList.push(calculate(previous, last, operatorStack.pop()));
					} else if (b == ')') {
						operatorStack.pop();
						while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
							last = numbersList.pop();
							previous = numbersList.pop();
							numbersList.push(calculate(previous, last, operatorStack.pop()));
						}
						operatorStack.pop();
					}
				}
				operatorStack.push(a);
			} else {
				number = number + a;
			}
		}
		if (!"".equals(number)) {
			numbersList.push(Double.valueOf(number));
			number = "";
		}
		while (!operatorStack.isEmpty()) {
			b = operatorStack.pop();
			if (b != '(' && b != ')') {
				last = numbersList.pop();
				previous = numbersList.pop();
				numbersList.push(calculate(previous, last, b));
			}

		}
		return numbersList.pop();
	}

	private Double calculate(Double previous, Double last, Character pop) {
		switch (pop) {
		case '+':
			return previous + last;
		case '-':
			return previous - last;
		case '*':
			return previous * last;
		case '/':
			return previous / last;
		}
		return (double) -1;
	}

	public static void main(String[] args) throws Exception {
		InfixToPostfix infixToPostfix = new InfixToPostfix();
		/********** Infix to Postfix ***************/
		String infix = "A+B-C"; // AB+C-
		// String infix = "A+B*C"; // ABC*+
		// String infix = "A*(B+C)"; // ABC+*
		// String infix = "A+B*(C-D)"; // ABCD-*+
		// String infix = "A*B+(C/D*(E+F))*G"; // AB*CD/EF+*G*+
		// String infix = "A*B+(C/(D*E+F))*G"; // AB*CDE*F+/G*+
		// String infix = "A+B*(C/(D*E+F))*G"; // ABCDE*F+/G**+
		System.out.println("Infix : " + infix + ", Postfix : " + infixToPostfix.convert(infix));
		
		/************* Expression Calculator****************/
		String expression = "2+3-5"; // 0
		// String expression = "2+3*5"; // 17
		// String expression = "2*(3+5)"; // 16
		// String expression = "2+3*(5-7)"; // -4
		// String expression = "2*3+(5/8*(11+13))*17"; // 261
		// String expression = "2+(3/(5*7+11))*13"; // 2.8478260869565215
		// String expression = "2+3*(5/(7*11+13))*17"; // 4.833333333333333
		System.out.println("Expression : " + expression + ", Result : " + infixToPostfix.calculateExpression(expression));
	}

}
