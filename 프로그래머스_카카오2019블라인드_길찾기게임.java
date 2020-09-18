import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스_카카오2019블라인드_길찾기게임 {
	static ArrayList<Integer> list;

	public static void main(String[] args) {
		int[][] nodeinfo = { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
				{ 2, 2 } };
		int[][] answer = new int[2][nodeinfo.length];
		Node[] n = new Node[nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++) {
			n[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
		}
		Arrays.sort(n, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.y == o2.y) {
					return o1.x - o2.x;
				}
				return o2.y - o1.y;
			}
		});
		Node root = n[0];
		for (int i = 1; i < n.length; i++) {
			connect(root, n[i]);
		}
		list = new ArrayList<Integer>();

		preorder(root);
		for (int i = 0; i < nodeinfo.length; i++) {
			answer[0][i] = list.get(i);
		}
		list.clear();
		postorder(root);
		for (int i = 0; i < nodeinfo.length; i++) {
			answer[1][i] = list.get(i);
		}

	}

	private static void postorder(Node root) {
		if (root.left != null) {
			postorder(root.left);
		}
		if (root.right != null) {
			postorder(root.right);
		}
		list.add(root.v);
	}

	private static void preorder(Node root) {
		list.add(root.v);
		if (root.left != null) {
			preorder(root.left);
		}
		if (root.right != null) {
			preorder(root.right);
		}

	}

	private static void connect(Node root, Node node) {
		if (root.x > node.x) {
			if (root.left != null) {
				connect(root.left, node);
			} else {
				root.left = node;
			}
		} else {
			if (root.right != null) {
				connect(root.right, node);
			} else {
				root.right = node;
			}
		}
	}

	static class Node {
		int v;
		int x;
		int y;
		Node left;
		Node right;

		public Node(int v, int x, int y) {
			super();
			this.v = v;
			this.x = x;
			this.y = y;
		}

	}
}
