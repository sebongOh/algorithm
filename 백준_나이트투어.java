import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_나이트투어 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] visited = new boolean[6][6];
		int sx = 0, sy = 0;
		char[] c = br.readLine().toCharArray();
		sx = c[0] - 'A';
		sy = c[1] - '0' - 1;
		boolean flag = false;
		int x = sx;
		int y = sy;
		
		int tx = 0, ty = 0;
		for (int i = 0; i < 36; i++) {
			if (i == 35) {
				tx = sx;
				ty = sy;
			} else {
				c = br.readLine().toCharArray();
				tx = c[0] - 'A';
				ty = c[1] - '0' - 1;
			}
			if (!visited[tx][ty]) {
				if ((Math.abs(x - tx) == 2 || Math.abs(y - ty) == 1)
						&& (Math.abs(x - tx) == 1 || Math.abs(y - ty) == 2)) {
					visited[tx][ty] = true;
					x = tx;
					y = ty;
				}
			} else {
				flag = true;
				break;
			}
		}

		if (flag) {
			System.out.println("Invalid");
		} else {
			System.out.println("Valid");
		}

	}
}
