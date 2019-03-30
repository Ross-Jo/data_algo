package dataStructure;

/*
 * [하나의 공간을 적게 사용한 환형 큐 구현]
 * - 이렇게 하는 이유는 front와 rear가 실제 원소를 가리키는 경우 삭제연산시,
 * empty케이스인 경우 front의 rear역전이 발생하는데 이 경우 따로 empty케이스에 대한 확인을 하고 front 및 rear를 0으로 초기화 해야 한다.
 * 하지만 앞 공간이 빈 환형 큐를 사용하는 경우 이러한 empty 체킹 및 예외케이스 처리가 필요 없어진다.
 * 코드 효율성도 이와 같이 구현할 때, 더 높게 나타난다.  
 */

/*
 * [큐의 다양한 활용]
 * - OS에서 실행대기중이거나 특정한 이벤트가 발생하기를 기다리는 프로세스의 job큐를 운용한다.
 * - 생산자 프로세스와 소비자 프로세스에서 아이템을 생산하고 그것을 소비하기 위해 큐를 사용한다.  
 */

public class MyQueue<T> {
	private int size;
	private int front;
	private int rear;
	private T[] q;
	
	public MyQueue() {
		q = (T[]) new Object[10];
		size = 0;
		front = 0;
		rear = 0;
	}
	
	boolean isEmpty() {
		return size == 0;
	}
	
	public void clearQueue() {
		q = (T[]) new Object[10];
		size = 0;
		front = 0;
		rear = 0;
	}
	
	public void enqueue(T newItem) {
		if((rear+1)%q.length == front) {
			resize(2*q.length);
		}
		rear = (rear+1)%q.length;
		q[rear] = newItem;
		size++;
	}
	
	public T dequeue() {
		if(isEmpty()) return null;
		front = (front + 1) % q.length;
		T item = (T) q[front];
		q[front] = null;
		size--;
		if (size > 0 && size == q.length/4) {
			resize(q.length/2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		T[] tmp = (T[]) new Object[capacity];
		for (int i=0; i<=size; i++) {
			tmp[i] = (T) q[(front+i)%q.length];
		}
		front = 0;
		rear = size;
		q = tmp;
	}
	
	public static void main(String args[]) {
		MyQueue<Integer> mq = new MyQueue<Integer>();
		mq.enqueue(1);
		mq.enqueue(2);
		mq.enqueue(3);
		mq.enqueue(4);
		mq.enqueue(5);
		mq.enqueue(6);
		mq.enqueue(7);
		mq.enqueue(8);
		mq.enqueue(9);
		mq.enqueue(10);
		mq.enqueue(11);
		mq.enqueue(12);
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
	}
}
