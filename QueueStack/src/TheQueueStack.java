
public class TheQueueStack<Item> {
	private Item[] in, out;
	private int inTop, outTop;
	
	@SuppressWarnings("unchecked")
	public TheQueueStack() {
		in = (Item[]) new Object[5];
		out = (Item[]) new Object[5];
		inTop = 0;
		outTop = 0;
	}
	
	public void queue(Item obj) {
		inPush(obj);
	}
	
	public Item dequeue() {
		if(isOutEmpty()) {
			while(inTop != 0) {
				outPush(inPop());
			}
		}
		return outPop();
	}
	
	private void outPush(Item obj) {
		if(outTop == out.length)
			resizeArrayOut(out.length * 2);
		out[outTop++] = obj;
	}
	
	private Item outPop() {
		if(outTop > 0 && outTop == out.length/4)
			resizeArrayOut(out.length/2);
		if(outTop > 0)
			return out[--outTop];
		else
			return null;
	}
	
	private void inPush(Item obj) {
		if(inTop == in.length)
			resizeArrayIn(in.length * 2);
		in[inTop++] = obj;
	}
	
	private Item inPop() {
		if(inTop > 0 && inTop == in.length/4)
			resizeArrayIn(in.length/2);
		return in[--inTop];
	}
	
	private boolean isOutEmpty() {
		return outTop == 0;
	}
	
	@SuppressWarnings("unused")
	private boolean isInEmpty() {
		return inTop == 0;
	}
	
	private void resizeArrayIn(int newLength) {
		Item[] tempIn = (Item[]) new Object[newLength];
		for(int i=0; i<inTop; i++) {
			tempIn[i] = in[i];
		}
		in = tempIn;
	}
	
	private void resizeArrayOut(int newLength) {
		Item[] tempOut = (Item[]) new Object[newLength];
		for(int i=0; i<outTop; i++) {
			tempOut[i] = out[i];
		}
		out = tempOut;
	}
}
