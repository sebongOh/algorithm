import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int a = sc.nextInt();
//		int b = sc.nextInt();
		int d = -1000;
		int N = 4;
		while(d<0) {
			d += N;
		}
		System.out.println(d);
	}
}