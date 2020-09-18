import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class SWEA_방향전환1 {
    static int[][] arr;
    static int result;
    static int[] row = { -1, 1 }; // 가로이동
    static int[] col = { -1, 1 }; // 세로이동
    static boolean[][][] visited;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] str;
 
        for (int tc = 1; tc <= t; tc++) {
            int sx = 0;
            int sy = 0;
            int ex = 0;
            int ey = 0;
            visited = new boolean[201][201][2];
            str = br.readLine().split(" ");
            sx = Integer.parseInt(str[0])+100;
            sy = Integer.parseInt(str[1])+100;
            ex = Integer.parseInt(str[2])+100;
            ey = Integer.parseInt(str[3])+100;
            int tx = 0;
            int ty = 0;
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(sx, sy, 0, 0));
            q.add(new Node(sx, sy, 1, 0));
            visited[sx][sy][0] = true;
            visited[sx][sy][1] = true;
             
            while (!q.isEmpty()) {
                Node n = q.poll();
                if (n.x == ex && n.y == ey) {
                    result = n.cnt;
                    break;
                }
                int dir = n.dir;
                if (dir == 0) { // 0이면 가로이동
                    for (int i = 0; i < 2; i++) {
                        tx = n.x + row[i];
                        ty = n.y;
                        if (tx < 0 || tx > 200 || ty < 0 || ty > 200)
                            continue;
                        if(visited[tx][ty][0])continue;
                        visited[tx][ty][0]=true;
                        q.add(new Node(tx, ty, 1, n.cnt + 1));
                    }
                } else { // 1이면 세로이동
                    for (int i = 0; i < 2; i++) {
                        tx = n.x;
                        ty = n.y + col[i];
                        if (tx < 0 || tx > 200 || ty < 0 || ty > 200)
                            continue;
                        if(visited[tx][ty][1])continue;
                        visited[tx][ty][1]=true;
                        q.add(new Node(tx, ty, 0, n.cnt + 1));
                    }
                }
            }
 
            System.out.println("#" + tc + " " + result);
        }
    }
 
    static class Node {
        int x;
        int y;
        int dir;
        int cnt;
 
        public Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}