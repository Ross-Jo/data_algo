package dataStructure;

/*
 * [링크드 리스트의 활용]
 * - 스택
 * - 큐
 * - 해시 cf. separate chaining
 * - 리눅스
 *   - 프로세스들이 링크드 리스트에 저장되어 있음
 *   - 큐를 사용해 job을 스케쥴하는 FIFO스케쥴러 
 *   - 스택에 activation을 push하는 함수호출 
 * - 블록체인(인증)
 */

public class MyLinkedList {
	
	static class MySinglyLinkedList {
		
		static class Node {
			protected int element;
			protected Node next;
			public Node (int i, Node n) {
				element = i;
				next = n;
			}
			public int getElement() {return element;}
			public Node getNext() {return next;}
			public void setElement(int i) {element = i;}
			public void setNext(Node n) {next = n;}
		}
		
		protected Node head;
		protected int size;
		
		public MySinglyLinkedList() {
			head = null;
			size = 0;
		}
		
		public int size() {return size;}
		public boolean isEmpty() {return size==0;}
		
		public void insertFront(int num) {
			head = new Node(num, head);
			size++;
		}
		
		public void insertAfter(Node p, int num) {
			p.next = new Node(num, p.next);
		}
		
		public void removeFront() {
			if (isEmpty()) throw new Error();
			else head = head.next;
		}
		
		public Node searchNode(int k) {
			Node p = this.head;
			while(p!=null) {
				if(k==p.getElement()) return p;
				else p = p.next;
			}
			return null;
		}
	}
	
	static class DoublyLinkedList {
		
		static class DNode {
			protected int element;
			protected DNode prev, next;
			public DNode(int i, DNode p, DNode n) {
				element = i;
				prev = p;
				next = n;
			}
			public int getElement() {return element;}
			public DNode getPrev() {return prev;}
			public DNode getNext() {return next;}
			public void setElement(int i) {element = i;}
			public void setPrev(DNode p) {prev=p;}
			public void setNext(DNode n) {next=n;}
		}
		
		protected DNode header, trailer;
		protected int size;
		public DoublyLinkedList() {
			header = new DNode(0, null, null); // dummy
			trailer = new DNode(0, header, null); // dummy
			header.setNext(trailer);
			size = 0;
		}
		
		public int size() {return size;}
		public boolean isEmpty() {return size==0;}
		
		public DNode getFirst() {
			if(isEmpty()) throw new Error(); // illegal state
			return header.getNext();
		}
		
		public DNode getLast() {
			if(isEmpty()) throw new Error(); // illegal state
			return trailer.getPrev();
		}
		
		public DNode getPrev(DNode v) {
			if(v==header) throw new Error(); // illegal argument
			return v.getPrev();
		}
		
		public DNode getNext(DNode v) {
			if(v==trailer) throw new Error(); // illegal argument
			return v.getNext();
		}
		
		public void addBefore(DNode x, DNode newNode) {
			DNode t = getPrev(x);
			newNode.setPrev(t);
			newNode.setNext(x);
			x.setPrev(newNode);
			t.setNext(newNode);
			size++;
		}
		
		public void addAfter(DNode x, DNode newNode) {
			DNode t = getNext(x);
			newNode.setPrev(x);
			newNode.setNext(t);
			t.setPrev(newNode);
			x.setNext(newNode);
			size++;
		}
		
		public void addFirst(DNode newNode) {
			addAfter(header, newNode);
		}
		
		public void addLast(DNode newNode) {
			addBefore(trailer, newNode);
		}
		
		public void remove(DNode x) {
			DNode a = getPrev(x);
			DNode b = getNext(x);
			b.setPrev(a);
			a.setNext(b);
			x.setPrev(null);
			x.setNext(null);
			size--;
		}
		
		public boolean hasPrev(DNode v) {
			return v!=header;
		}
		
		public boolean hasNext(DNode v) {
			return v!=trailer;
		}
		
	}
	
	public static void main(String args[]) {
		
	}
}
