package dataStructure;

//참고 : https://github.com/TheAlgorithms/Java/blob/master/DataStructures/Trees/TrieImp.java

class Trie {
	class Node {
		Node[] child;
		boolean end;
		int counter;

		public Node() {
			child = new Node[26];
			end = false;
			counter = 1;
		}
	}

	private final Node root;

	public Trie() {
		root = new Node();
	}
	
	public void insert(String word) {
		Node curNode = root;
		for (int i = 0; i < word.length(); i++) {
			Node node = curNode.child[word.charAt(i) - 'a'];

			if (node == null) {
				node = new Node();
				curNode.child[word.charAt(i) - 'a'] = node;
			} else {
				node.counter++;
			}
			curNode = node;
		}
		curNode.end = true;
	}
	
	public boolean search(String word) {
		Node curNode = root;
		for (int i = 0; i < word.length(); i++) {
			Node node = curNode.child[word.charAt(i) - 'a'];

			if (node == null) {
				return false;
			}

			curNode = node;
		}
		return curNode.end;
	}
	
	public boolean delete(String word) {
		Node curNode = root;
		for (int i = 0; i < word.length(); i++) {
			Node node = curNode.child[word.charAt(i) - 'a'];

			if (node == null) {
				return false;
			}

			curNode = node;
		}
		if (curNode.end == true) {
			curNode.end = false;
			return true;
		}
		return false;
	}
}

public class MyTrie {
	public static void main(String[] args) {
		String[] words = {
			"word", "war", "warrior", "world"
		};
		
		Trie trie = new Trie();
		
		for (String word: words) {
			trie.insert(word);
		}
	}
}
