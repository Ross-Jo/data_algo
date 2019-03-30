package dataStructure;

// cf. ArrayList를 이용한 스택 방식도 있음 

/*
 * [array를 이용한 스택]
 * - pop, push는 분할상환 시간복잡도 분석 방법으로 O(1)의 시간복잡도를 가진다.
 *   - 배열이 꽉 찬 경우 2배
 *   - 배열이 1/4정도 찬 경우는 값을 담는 배열의 크기를 반으로 줄여줌 
 */

/*
 * [linkedList를 이용한 스택]
 * - pop, push, isEmpty는 가장 나쁜 경우에도 O(1)의 시간복잡도를 가짐 
 * - 사이즈 재조정을 피할 수 있음
 * - 단, storage of references에 대한 오버헤드가 있다
 * - links를 다루는 것에 대한 시간 오버헤드가 있다 
 */

/*
 * [자바에서 스택의 쓰임]
 * 자바는 런타임 메모리를 2가지의 분리된 섹션으로서 관리한다. (스택, 힙)
 * - 힙: 모든 배열들, 클래스 변수들 및 모든 객체를 저장한다. (ex. static을 이용해 선언된 것들)
 * - 스택: 메소드의 모든 지역 변수들, 그리고 파라미터들을 저장한다. 
 * 
 * 메소드가 불려지게 되면 JVM은 스택 프레임을 생성하게 되고, 그것은 호출된 함수의 지역 변수 및 파라미터들을 저장하게 된다.
 * 자바에서는 메소드가 메소드의 연속 호출구조 때문에 스택프레임들의 스택을 내부적으로 생성하여 유지하는데, 'main'이 가장 아래로가고 top에 가장 최근의 메소드가 오게 된다. 
 * 그리고 메소드의 실행이 끝나면 그 메소드의 스택 프레임은 스택의 꼭대기에서 pop()되서 나오고, 그것의 지역 변수는 영원히 지워진다.  
 * 
 */

/*
 * [스택이 응용되는 곳]
 * - 웹 브라우저에서 방문 기록을 나타낼때
 * - 텍스트 에디터의 undo 순서
 * - 펠린드롬 확인
 * - 미로찾기
 * - 괄호매칭
 * - 후위 표기 연산 평가 
 * - 중위 표기 연산 -> 후위 표기 연산 변환
 * - 트리 탐색
 * - 그래프 탐색 (DFS)
 */

/*
 * [후위 표기 연산의 평가]
 * while(왼쪽부터 오른쪽으로 스캔해 나간다) {
 *   if(피연산자를 만나면) 그것을 스택에 넣는다
 *   else if(연산자를 만나면) {
 *   	스택에서 2번 pop연산을 하고
 *      2개의 피연산자와 만난 연산자를 이용하여 결과값을 계산한다. 
 *      계산의 결과값을 다시 스택에 넣어준다. 
 *   }
 * }
 */

/*
 * [중위 연산 -> 후위 연산 알고리즘]
 * while(왼쪽부터 오른쪽으로 수식을 스캔해 나간다) {
 *   if('('를 만나면) '('를 연산자 스택에 집어넣는다
 *   else if(')'를 만나면) '('이 제일 위에 있을때까지 스택에서 pop을 시켜주고, '('를 팝해준다.
 *   else if(피연산자를 만나면) 아웃풋 array로 넣어준다. 
 *   else if(연산자를 만나면) {
 *   	if (만난 연산자가 top에 있는 연산자보다 높은 우선순위를 가지고 있으면) 해당 연산자를 연산자 스택에 넣어준다. 
 *      else {
 *      	top의 연산자가 만난 연산자보다 낮은 우선순위를 가질 때 까지 pop해서 pop된 연산자를 아웃풋 array에 집어 넣는다. 
 *          이후 만난 연산자를 연산자 스택에 집어 넣는다. 
 *      }
 *   } 
 * }
 * 스캐닝이 끝나면 연산자 스택에 남은 연산자를 아웃풋 array에 넣는다. 
 */

public class MyStack<T> {
	private T[] s;
	private int size;
	
	public MyStack() {
		s = (T[]) new Object[10];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public T peek() {
		if(isEmpty()) throw new Error();
		return s[size-1];
	}
	
	public void push(T newItem) { // size변수 유의해서 볼 것 
		if (size == s.length) resize(2*s.length);
		s[size++] = newItem;
	}
	
	public T pop() {
		if(isEmpty()) throw new Error();
		T item = s[--size];
		s[size] = null; // for GC
		if (size > 0 && size == s.length/4) {
			resize(s.length/2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		T[] tmp = (T[]) new Object[capacity];
		for (int i=0; i<size; i++) tmp[i] = s[i];
		s = tmp;
	}
	
	public static void main(String args[]) {
		MyStack<Integer> ms = new MyStack<Integer>();
		ms.push(5);
		ms.push(8);
		ms.push(2);
		ms.push(9);
		
		System.out.println(ms.isEmpty());
		System.out.println(ms.peek());
		System.out.println(ms.pop());
		System.out.println(ms.peek());
	}
}
