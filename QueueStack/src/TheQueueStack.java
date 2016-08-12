
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
		in[inTop++] = obj;
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
		out[outTop++] = obj;
	}
	
	private Item outPop() {
		return out[--outTop];
	}
	
	@SuppressWarnings("unused")
	private void inPush(Item obj) {
		in[inTop++] = obj;
	}
	
	private Item inPop() {
		return in[--inTop];
	}
	
	private boolean isOutEmpty() {
		return outTop == 0;
	}
	
	@SuppressWarnings("unused")
	private boolean isInEmpty() {
		return inTop == 0;
	}
}
