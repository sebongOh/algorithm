import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_감시피하기 {
	static int N;
	static char[][] arr;
	static boolean flag;
	static ArrayList<Node> teacher;
	static ArrayList<Node> list;
	static ArrayList<Node> select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		teacher = new ArrayList<>();
		list = new ArrayList<>();
		select = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = str[j].charAt(0);
				if (arr[i][j] == 'T') {
					teacher.add(new Node(i, j));
				} else if (arr[i][j] == 'X') {
					list.add(new Node(i, j));
				}
			}
		}

		dfs(0, 0);
		if(flag) {
			System.out.println("NO");
		}
		
	}

	private static void dfs(int depth, int cur) {
		if (depth == 3) {
			solve();
			if(!flag) {
				System.out.println("YES");
				return;
			}
			return;
		}
		for (int i = cur; i < list.size(); i++) {
			select.add(list.get(i));
			dfs(depth + 1, i + 1);
			if(!flag) {
				return;
			}
			select.remove(select.size() - 1);
		}

	}

	private static void solve() {
		flag = false;
		int tx=0;
		int ty=0;
		
		char[][] copy = new char[N][N];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(arr[i], N);
		}

		for (int i = 0; i < select.size(); i++) {
			copy[select.get(i).x][select.get(i).y] = 'O';
		}
		
		
		for(int i=0;i<teacher.size();i++) {
			Node n = teacher.get(i);
			
			for(int j=n.x;j<N;j++) { //아래방향
				if(copy[j][n.y]=='X')continue;
				else if(copy[j][n.y]=='O')break;
				else if(copy[j][n.y]=='S') {
					flag=true;
					return;
				}
			}
			
			for(int j=n.x;j>=0;j--) { //위방향
				if(copy[j][n.y]=='X')continue;
				else if(copy[j][n.y]=='O')break;
				else if(copy[j][n.y]=='S') {
					flag=true;
					return;
				}
			}
			
			for(int j=n.y;j<N;j++) {
				if(copy[n.x][j]=='X')continue;
				else if(copy[n.x][j]=='O')break;
				else if(copy[n.x][j]=='S') {
					flag=true;
					return;
				}
			}
			
			for(int j=n.y;j>=0;j--) {
				if(copy[n.x][j]=='X')continue;
				else if(copy[n.x][j]=='O')break;
				else if(copy[n.x][j]=='S') {
					flag=true;
					return;
				}
			}
		}

	}

	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}

}
