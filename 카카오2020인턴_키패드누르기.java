import java.util.ArrayList;

public class 카카오2020인턴_키패드누르기 {
	public static void main(String[] args) {
		int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";
		String answer = "";

		int left = 10;
		int right = 11;

		ArrayList<Pair> list = new ArrayList<Pair>();
		list.add(new Pair(3, 1));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				list.add(new Pair(i, j));
			}
		}
		list.add(new Pair(3, 0));
		list.add(new Pair(3, 2));

		System.out.println(list);
		int idx = 0;
		for (int i = 0; i < numbers.length; i++) {
			idx = numbers[i];
			if (idx == 1 || idx == 4 || idx == 7) {
				answer += "L";
				left = idx;
			} else if (idx == 3 || idx == 6 || idx == 9) {
				answer += "R";
				right = idx;
			} else {
				int rx = list.get(right).x;
				int ry = list.get(right).y;
				int lx = list.get(left).x;
				int ly = list.get(left).y;

				int ldist = Math.abs(lx - list.get(idx).x) + Math.abs(ly - list.get(idx).y);
				int rdist = Math.abs(rx - list.get(idx).x) + Math.abs(ry - list.get(idx).y);

				if (ldist < rdist) {
					answer += "L";
					left = idx;
				} else if (ldist > rdist) {
					answer += "R";
					right = idx;
				} else {
					if (hand.equals("right")) {
						answer += "R";
						right = idx;
					} else {
						answer += "L";
						left = idx;
					}
				}
			}
		}

		System.out.println(answer);

	}

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}

	}

}
