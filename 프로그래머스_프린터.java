import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_프린터 {
	public static void main(String[] args) {
		int[] priorities = { 2, 1, 3, 2 };
		int location = 2;
		int res = solution(priorities, location);
		System.out.println(res);
	}

	private static int solution(int[] priorities, int location) {
		int cnt = 0;
		boolean flag = false;
		Queue<Node> pq = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			pq.add(new Node(i, priorities[i]));
		}

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			for (Node node : pq) {
				if (n.order < node.order) {
					flag = true;
					break;
				}
			}
			if (flag) { // 있으면
				pq.add(n);
			} else {
				cnt++;
				if (n.x == location) {
					return cnt;
				}
			}
			flag = false;

		}

		return 0;
	}

	static class Node {
		int x;
		int order;

		public Node(int x, int order) {
			this.x = x;
			this.order = order;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", order=" + order + "]";
		}
		
	}
}
