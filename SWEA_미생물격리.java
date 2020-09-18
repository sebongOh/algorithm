import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SWEA_미생물격리 {
	static int N, M, K;
	static List<virus> list;
	static int[] dx = { 0, -1, 1, 0, 0 }; // 상,하,좌,
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			list = new ArrayList<>();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			K = Integer.parseInt(str[2]);
			for (int i = 0; i < K; i++) {
				str = br.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int c = Integer.parseInt(str[2]);
				int d = Integer.parseInt(str[3]);
				list.add(new virus(x, y, c, d));
			}
			for (int time = 0; time < M; time++) {
				for (int i = 0; i < list.size(); i++) {
					virus v = list.get(i);
					v.x = v.x + dx[v.dir];
					v.y = v.y + dy[v.dir];
					if (v.x == 0 || v.x == N - 1 || v.y == 0 || v.y == N - 1) {
						v.cnt = v.cnt / 2;
						if (v.dir == 1) {
							v.dir = 2;
						} else if (v.dir == 2) {
							v.dir = 1;
						} else if (v.dir == 3) {
							v.dir = 4;
						} else if (v.dir == 4) {
							v.dir = 3;
						}
						if(v.cnt==0) {
							list.remove(i);
							i--;
						}
					}
				}//
				
				System.out.println(list);
				
				Collections.sort(list);
				for(int i=0;i<list.size()-1;i++) {
					virus now = list.get(i);
					virus next = list.get(i+1);
					if(now.x==next.x && now.y==next.y) {
						now.cnt += next.cnt;
						list.remove(i+1);
						i--;
					}
				}
			}
			
			int res =0;
			for(int i=0;i<list.size();i++) {
				res += list.get(i).cnt;
			}
			
			System.out.println("#"+tc+" "+res);
		}
	}

	static class virus implements Comparable<virus> {
		int x;
		int y;
		int cnt;
		int dir;

		public virus(int x, int y, int cnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(virus o) {
			if (this.x == o.x && this.y == o.y) {
				return o.cnt - this.cnt;
			}
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}

		@Override
		public String toString() {
			return "virus [x=" + x + ", y=" + y + ", cnt=" + cnt + ", dir=" + dir + "]";
		}
		
		

	}
}
