import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class SWEA_모래성쌓기 {
    static int H, W;
    static int[][] arr;
    static int day;
    static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
    static List<Node> sand;
    static List<Node> list;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] str;
 
        for (int tc = 1; tc <= t; tc++) {
            list = new ArrayList<>();
            str = br.readLine().split(" ");
            H = Integer.parseInt(str[0]);
            W = Integer.parseInt(str[1]);
            arr = new int[H][W];
            day = 0;
            for (int i = 0; i < H; i++) {
                str = br.readLine().split("");
                for (int j = 0; j < W; j++) {
                    if (str[j].charAt(0) == '.') {
                        arr[i][j] = -1;
                    } else {
                        arr[i][j] = str[j].charAt(0) - '0';
 
                    }
                }
            }
            int a=0;
            int b=0;
            while (true) {
                for (int i = 1; i < H - 1; i++) {
                    for (int j = 1; j < W - 1; j++) {
                        if (arr[i][j] >= 1 && arr[i][j] < 9) {
                            solve(i, j);
                        }
                    }
                }
                if (list.size() == 0) {
                    break;
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        arr[list.get(i).x][list.get(i).y] = -1;
                    }
                    list.clear();
                }
                day++;
            } // while
 
            System.out.println("#" + tc + " " + day);
 
        } // t
    }
 
    private static void solve(int x, int y) {
        int tx = 0;
        int ty = 0;
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            tx = x + dx[i];
            ty = y + dy[i];
            if (arr[tx][ty] == -1) {
                cnt++;
            }
            if (cnt >= arr[x][y]) {
                list.add(new Node(x, y));
                break;
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
 
    }
}
