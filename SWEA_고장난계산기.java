import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_고장난계산기 {
	static int X;
	static int min;
	static boolean[]btn;
	static int [] memo;
	static int size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String [] str;
		
		for(int tc=1;tc<=T;tc++) {
			min = Integer.MAX_VALUE;
			str = br.readLine().split(" ");
			btn = new boolean[10];
			int num;
			for(int i=0;i<10;i++) {
				num = Integer.parseInt(str[i]);
				if(num == 1) {
					btn[i] = true;
				}
			}
			
			X = Integer.parseInt(br.readLine());
			size = (int)Math.sqrt(X);
			memo = new int [size];
			find(X,0);
			min = min==Integer.MAX_VALUE?-1:min;
			System.out.println("#"+tc+" "+min);
			
		}
		
	}

	private static int find(int x, int cnt) {
		if(x<size && memo[x]!=0) {
			return memo[x];
		}
		if(isMake(x+"")) {
			int count = len(x)+1;
			if(cnt==0){
				min = count;
			}
			if(x < size) {
				memo[x] = count;
			}
			return count;
		}
		int result = -1;
		for(int i=2,end = (int)Math.sqrt(x)+1;i<end;i++) {
			if(x % i ==0 && isMake(i+"")) {
				int len1 = len(i)+1;
				int len2 = find(x/i,cnt+1);
				if(len2>-1) {
					result = len1 + len2;
					if(result<min && x==X) {
						min = result;
					}
				}
			}
		}
		
		if(x<size) {
			memo[x] = result;
		}
		return result;
	}

	private static int len(int x) {
		return (int)Math.log10(x)+1;
	}

	private static boolean isMake(String x) {
		for(char c : x.toCharArray()) {
			if(!btn[c-'0']) {
				return false;
			}
		}
		return true;
	}
}
