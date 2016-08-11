

public class Stack<Item> {
	
	/*
	 * I know I could have used a linked-list type of implementation. I just wanted to use an Array implementation.
	 */
	
	private int topOfStack;
	private Item[] theStack;
	
	@SuppressWarnings("unchecked")
	public Stack() {
		topOfStack = 0;
		theStack = (Item[]) new Object[5];
	}
	
	public void push(Item token) {
		if(topOfStack == theStack.length)
			resizeArray(theStack.length*2);
		theStack[topOfStack++] = token;
		
	}
	
	public Item pop() {
		if(topOfStack >= 0) {
			Item obj = theStack[--topOfStack];
			theStack[topOfStack] = null;
			if(topOfStack > 0 && topOfStack == theStack.length/4)
				resizeArray(theStack.length/2);
			return obj;
		}
		else
			return null;
	}
	
	public Item peek() {
		if(topOfStack >= 0)
			return theStack[topOfStack];
		else
			return null;
	}
	
	public int getStackLength() {
		return theStack.length;
	}
	
	public int getTopOfStackPos() {
		return topOfStack;
	}
	
	public boolean isEmpty() {
		return topOfStack == 0;
	}
	
	public Item peekAt(int position) {
		if(position < 0 || position >= getTopOfStackPos())
				return null;
		return theStack[position];
	}
	
	public Item popAt(int position) {
		if(position > this.topOfStack || position < 0) {
			System.err.println("Index out of bounds");
			return null;
		}
		Item item = theStack[position];
		shiftDown(position);
		return item;
		
	}
	
	public void pushAt(Item obj, int position) {
		if(this.topOfStack == theStack.length)
			resizeArray(theStack.length*2);
		for(int i=getTopOfStackPos(); i>=position; i--) {
			theStack[i+1] = theStack[i];
		}
		theStack[position] = obj;
		this.topOfStack++;
	}
	
	private void shiftDown(int pos) {
		for(int i=pos; i<getTopOfStackPos()-1; i++) {
			theStack[i] = theStack[i+1];
		}
		topOfStack--;
	}

	@SuppressWarnings("unchecked")
	private void resizeArray(int cap) {
		Item[] temp = (Item[])new Object[cap];
		for(int i=0; i<topOfStack; i++) {
			temp[i] = theStack[i];
		}
		theStack = temp;
	}
}
