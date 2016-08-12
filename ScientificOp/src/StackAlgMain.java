/*
 * A simple implementation of the Dijkstra's two-stack system
 */

	
public class StackAlgMain {
	
	public static void main(String...args) {
		final String operators = "/*-+";
		
		
		
		/*
		 * There has to be no spaces. Parentheses must be correct.
		 * Value operations: +,-,*,/
		 */
		String op = "((7*7+9)+(6+6))";
		
		int length = op.length();
		boolean isOneOp = false, seenLeftParen = false, seenRightParen = false, shouldSkip = false;
		
		Stack<String> operatorStack = new Stack<String>();
		Stack<Float> valueStack = new Stack<Float>();
		
		for(int i=0; i<op.length(); i++) {
			String token = op.charAt(i) + "";
//			System.out.print(token + "              ");
			if(token.equals("(")) {
//				System.out.println("1");
				seenLeftParen = true;
				seenRightParen = false;
				isOneOp = false;
				shouldSkip = false;
			} else if(operators.contains(token)) {
//				System.out.print("2      ");
				operatorStack.push(token);
				if(seenLeftParen) {
					if(!shouldSkip && !isOneOp) {
//						System.out.println("2.33");
						isOneOp = true;
					} else if(isOneOp && !shouldSkip) {
//						System.out.println("2.66");
						isOneOp = false;
						shouldSkip = true;
					}
				} else {
//					System.out.println();
				}
			} else if(token.equals(")")) {
				if(!seenRightParen) {
//					System.out.println("3");
					seenRightParen = true;
					seenLeftParen = false;
					if(isOneOp) {
//						System.out.println("3.5");
						float x = valueStack.pop();
						float y = valueStack.pop();
						String operation = operatorStack.pop();
						
						switch(operation) {
						case "+":
							valueStack.push(x+y);
							break;
						case "-":
							valueStack.push(x-y);
							break;
						case "*":
							valueStack.push(x*y);
							break;
						case "/":
							valueStack.push(x/y);
							break;
						default:
							System.err.println("Something dun goofed. switch statements");
						}
//						System.out.println(valueStack.peek() + "");
					}
				}
			} else {
//				System.out.println("4");
				valueStack.push(Float.parseFloat(token));
			}
		}
		
//		System.out.println("-------------");
//
//		System.out.println(valueStack.peekAt(0));
//		System.out.println(valueStack.peekAt(1));
//		System.out.println(valueStack.peekAt(2));
//		System.out.println(valueStack.peekAt(3));
//		System.out.println(valueStack.peekAt(4));
//		
//		System.out.println("-------------");
//		
//		System.out.println(operatorStack.getStackLength() + "    " + operatorStack.getTopOfStackPos());
//		System.out.println(operatorStack.peekAt(0));
//		System.out.println(operatorStack.peekAt(1));
//		System.out.println(operatorStack.peekAt(2));
//		System.out.println(operatorStack.peekAt(3));
//		System.out.println(operatorStack.peekAt(4));
		
		int precedenceTrack = 0, opTrack = 0, opTrackLength = operatorStack.getTopOfStackPos();
		
		while(opTrack < opTrackLength) {
			String operator = operatorStack.peekAt(precedenceTrack);
//			System.out.print(operator + "   ");
			if(operator.equals("*")) {
				float x = valueStack.popAt(precedenceTrack);
				float y = valueStack.popAt(precedenceTrack);
				operatorStack.popAt(opTrack);
				valueStack.pushAt(x*y, precedenceTrack);
			} else if(operator.equals("/")) {
				float x = valueStack.popAt(precedenceTrack);
				float y = valueStack.popAt(precedenceTrack);
				operatorStack.popAt(opTrack);
				valueStack.pushAt(x/y, precedenceTrack);
			} else {
				precedenceTrack++;
			}
			opTrack++;
		}
		
//		System.out.println("\n-------------");
//
//		System.out.println(operatorStack.peekAt(0));
//		System.out.println(operatorStack.peekAt(1));
//		System.out.println(operatorStack.peekAt(2));
//		System.out.println(operatorStack.peekAt(3));
//		System.out.println(operatorStack.peekAt(4));
//		System.out.println("-------------");
//
//		System.out.println(valueStack.peekAt(0));
//		System.out.println(valueStack.peekAt(1));
//		System.out.println(valueStack.peekAt(2));
//		System.out.println(valueStack.peekAt(3));
//		System.out.println(valueStack.peekAt(4));
//		System.out.println(valueStack.getTopOfStackPos());
//		System.out.println("-----------");
//		
//		System.out.println(operatorStack.getTopOfStackPos());
//		System.out.println("-----------");
		
		opTrackLength = operatorStack.getTopOfStackPos();
		for(int i=0; i<opTrackLength; i++) {
			String operator = operatorStack.pop();
			float x = valueStack.pop();
			float y = valueStack.pop();
			if(operator.equals("+")) {
				valueStack.push(x+y);
			} else if(operator.equals("-")) {
				valueStack.push(y-x);
			} else {
				System.err.println("Calculating final value");

			}
		}
		
		System.out.println("The answer is:  " + valueStack.pop());
	}	
}
