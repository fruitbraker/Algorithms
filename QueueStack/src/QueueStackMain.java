
public class QueueStackMain {
	public static void main(String...args) {
		TheQueueStack<String> queue = new TheQueueStack<String>();
		
		queue.queue("1");
		queue.queue("2");
		queue.queue("3");
		queue.queue("4");
		queue.queue("5");
		queue.queue("6");
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
	}
}
