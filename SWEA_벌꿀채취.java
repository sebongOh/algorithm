import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_벌꿀채취 {
	static int N, M, C;
	static int[][] arr;
	static int[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 0; tc < T; tc++) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			C = Integer.parseInt(str[2]);
			
			arr = new int[N][N];
			memo = new int[N][N];

			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}
			//1. 각 i,j, 위치에서
			makeMaxMap();
			//2.
			System.out.println("#"+(tc+1)+" "+getMaxBenefit());
		} // testcase

	} // main

	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}

	// i : 행위치 j: 열위치 cnt: 고려한원소수
	// sum : 부분집합에 속한 원소의 합
	// powSum : 부분집합에 속한 원소의 이익
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {

		if (sum > C)
			return; // 부분집합의 합이 목표량 c를 초과하면 리턴
		if (cnt == M) {
			// 0 0 m=2
			// 0,0:0 0,1:1 0,2:2
			if (memo[i][j - M] < powSum) {
				memo[i][j - M] = powSum;
			}
			return;
		}
		// i,j위치 원소 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + arr[i][j], powSum + (arr[i][j] * arr[i][j]));
		// i,j위치 원소 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);

	}
	
	private static int getMaxBenefit() {
		int max = 0 ; //조합적선택후의 최대이익값
		int temp = 0;
		//1. 일꾼 A 기준선택
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) { //a일꾼
				//2. 일꾼 B선택 
				//같은 행 기준 선택
				for(int j2 = j+M; j2<= N-M ;j2++) {
					temp = memo[i][j] + memo[i][j2];
					if(max < temp) {
						max = temp;
					}
				}
				//다음행부터 마지막행까지 선택
				for(int i2= i+1; i2<N;i2++) {
					for(int j2=0; j2<= N-M; j2++) {
						temp = memo[i][j] + memo[i2][j2];
						if(max <temp) {
							max = temp ;
						}
					}
				}
			}
		}
		return max;
	}

}
