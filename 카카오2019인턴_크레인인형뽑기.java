import java.util.Stack;

public class 카카오2019인턴_크레인인형뽑기 {
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		int answer = 0;

		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < moves.length; i++) {
			int idx = 0;
			a:while (idx < board[0].length) {
				int data = board[idx][moves[i]-1];
				if (data != 0) {
					board[idx][moves[i]-1]=0;
					if (s.isEmpty()) {
						s.push(data);
						break;
					} else {
						if (data == s.peek()) {
							answer+=2;
							s.pop();
							break;
						} else {
							s.push(data);
							break;
						}
					}
				}
				idx++;
			} // while
		} // for
		System.out.println(answer);

	}
}
