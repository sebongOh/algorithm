import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 백준_낚시왕 {
	static int[][] map;
	static int R, C, M;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };
	static ArrayList<shark> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		list = new ArrayList<>();
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		M = Integer.parseInt(str[2]);
		map = new int[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			list.add(new shark(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]),
					Integer.parseInt(str[3]), Integer.parseInt(str[4])));
		}
		int sum = 0;
		Collections.sort(list);
		for (int i = 1; i <= C; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).y == i) {
					sum += list.get(j).z;
					list.remove(j);
					break;
				}
			}
			go();
			sharkremove();
		}

		System.out.println(sum);

	}

	private static void sharkremove() {
		for (int i = 0; i < list.size() - 1; i++) {
			shark s = list.get(i);
			if (s.x == list.get(i + 1).x && s.y == list.get(i + 1).y) {
				list.remove(i + 1);
				i--;
			}
		}
	}

	private static void go() {
		for (int i = 0; i < list.size(); i++) {
			shark s = list.get(i);
			int speed = s.s;
			for (int j = 0; j < speed; j++) {
				if (s.d < 3) { // 위아래
					if ((s.x == R && s.d == 2) || (s.x == 1 && s.d == 1)) {
						int next = s.d + 1;
						if (next % 2 == 0) {
							s.d = next;
						} else {
							s.d = next - 2;
						}
					}
					s.x += dx[s.d];
				} else { // 좌우
					if ((s.y == C && s.d == 3) || (s.y == 1 && s.d == 4)) {
						int next = s.d + 1;
						if (next % 2 == 0) {
							s.d = next;
						} else {
							s.d = next - 2;
						}
					}
					s.y += dy[s.d];
				}
			} // 이동끝
		} // 모든 리스트 이동 끝
		Collections.sort(list);
	}

	static class shark implements Comparable<shark> {
		int x;
		int y;
		int s;
		int d;
		int z;

		public shark(int x, int y, int s, int d, int z) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "shark [x=" + x + ", y=" + y + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

		@Override
		public int compareTo(shark o) {
			if (o.y == this.y) {
				if (o.x == this.x) {
					return o.z - this.z;
				}
				return this.x - o.x;
			} else {
				return this.y - o.y;
			}
		}
	}
}
