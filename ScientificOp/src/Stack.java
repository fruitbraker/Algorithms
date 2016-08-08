

public class Stack {
	
	/*
	 * I know I could have used a linked-list type of implementation. I just wanted to use an Array implementation.
	 */
	
	private int position;
	private String[] theStack;
	
	public Stack() {
		position = -1;
		theStack = new String[5];
	}
	
	public void push(String token) {
		if(position == theStack.length)
			resizeArray(theStack.length*2);
		theStack[position++] = token;
	}
	
	public String pop() {
		if(position >= 0)
			return theStack[--position];
		else
			return null;
	}
	
	public String peek() {
		if(position >= 0)
			return theStack[position];
		else
			return null;
	}
	
	private void resizeArray(int cap) {
		String[] temp = new String[cap];
		for(int i=0; i<position; i++) {
			temp[i] = theStack[i];
		}
		theStack = temp;
	}
}
