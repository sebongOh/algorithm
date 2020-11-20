import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		int[] a = { 1, 2, 4, 5 };
		int[] b = { 1, 2, 3, 4 };
		int[] c = { 2, 5, 4, 3 };
		int[] d = { 2, 4, 6, 2 };
		int[] e = { 3, 4, 5, 2 };
		int[] f = { 2, 1, 6, 2 };
		int[] g = { 2, 4, 2 };
		int[][] res = new int[7][];
		res[0] = a;
		res[1] = b;
		res[2] = c;
		res[3] = d;
		res[4] = e;
		res[5] = f;
		res[6] = g;

		for (int[] aa : res) {
			System.out.println(Arrays.toString(aa));
		}
		System.out.println();

		Arrays.sort(res, (o1, o2) -> {
			if (o1.length == o2.length) {
				if (o1[0] == o2[0]) {
					for (int i = 0; i < res.length; i++) {
						if (o1[i] == o2[i]) {
							continue;
						}
						return Integer.compare(o1[i], o2[i]);
					}
				}
				return Integer.compare(o1[0], o2[0]);
			}
			return Integer.compare(o2.length, o1.length);
		});

		for (int[] aa : res) {
			System.out.println(Arrays.toString(aa));
		}

	}
}