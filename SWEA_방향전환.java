import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_방향전환 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			int result = 0;
			int sx = 0;
			int sy = 0;
			int ex = 0;
			int ey = 0;
			str = br.readLine().split(" ");
			sx = Integer.parseInt(str[0]);
			sy = Integer.parseInt(str[1]);
			ex = Integer.parseInt(str[2]);
			ey = Integer.parseInt(str[3]);

			int row = Math.abs(sx - ex);
			int col = Math.abs(sy - ey);
			if (Math.abs(row - col) == 0) {
				result = row + col;
			} else {

				result = Math.min(row, col) * 2;
				if (Math.abs(row - col) % 2 == 0) {
					result += Math.abs(row - col) * 2;
				} else {
					result += Math.abs(row - col) * 2 - 1;
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
