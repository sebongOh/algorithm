import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_개똥벌레 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int h = Integer.parseInt(str[1]);
		int[] top = new int[h + 1]; // 인덱스 = 높이, 높이별 갯수를 저장한다. 석순
		int[] bot = new int[h + 1]; // 종유석

		for (int i = 0; i < n / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}

		System.out.println(Arrays.toString(bot));
		System.out.println(Arrays.toString(top));

		int[] top_sum = new int[h + 1]; // 부분합을 저장할 배열. top_sum[h]는 높이가 h이하인 석순의 갯수를 저장한다.
		int[] bot_sum = new int[h + 1]; // 종유석의 부분합
		// 이렇게 부분합을 저장한다
		for (int i = 1; i <= h; i++) {
			top_sum[i] = top_sum[i - 1] + top[i];
			bot_sum[i] = bot_sum[i - 1] + bot[i];
		}

		System.out.println(Arrays.toString(bot_sum));
		System.out.println(Arrays.toString(top_sum));

		int min = n; // 최소 부딪힘 횟수
		int cnt = 0; // 최소 부딪힘 횟수가 발생하는 높이값의 갯수
		
		for (int i = 1; i <= h; i++) { // i = 높이
			int obs = 0; // 부딪히는 종유석, 석순의 갯수를 저장할 변수
			// 부딪히는 종유석의 갯수 = 전체 종유석갯수 - i-1이하인 종유석의 갯수
			obs += bot_sum[h] - bot_sum[i - 1];
			// 부딪히는 석순의 갯수 = 전체 석순의 갯수 - h-i이하인 석순의 갯수
			obs += top_sum[h] - top_sum[h - i];
			
			if (obs < min) { // 만약 부딪히는 횟수가 최솟값보다 작다면
				min = obs; // 갱신해주고
				cnt = 1; // 처음이니깐 카운트는 1로 초기화
			} else if (obs == min) { // 만약 같다면
				cnt++; // 카운트만 올리기
			}
		}
		System.out.println(min + " " + cnt);

	}
}
