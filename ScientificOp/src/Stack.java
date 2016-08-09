

public class Stack<Item> {
	
	/*
	 * I know I could have used a linked-list type of implementation. I just wanted to use an Array implementation.
	 */
	
	private int position;
	private Item[] theStack;
	
	public Stack() {
		position = 0;
		theStack = (Item[]) new Object[5];
	}
	
	public void push(Item token) {
		if(position == theStack.length)
			resizeArray(theStack.length*2);
		theStack[position++] = token;
		
	}
	
	public Item pop() {
		if(position >= 0)
			return theStack[--position];
		else
			return null;
	}
	
	public Item peek() {
		if(position >= 0)
			return theStack[position-1];
		else
			return null;
	}
	
	private void resizeArray(int cap) {
		Item[] temp = (Item[])new Object[cap];
		for(int i=0; i<position; i++) {
			temp[i] = theStack[i];
		}
		theStack = temp;
	}
}
