import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_움직이는미로탈출 {
   static final int size = 8;
   static char map[][];
   static int ukje[], result;
   static boolean visited[][][], over8visited[][];
   public static void main(String[] args) throws Exception{
      input();
      result = go();
      System.out.println(result);
   }
   static int[] dy= {-1,0,1,0,1,-1,1,-1,0}, dx= {0,1,0,-1,1,1,-1,-1,0};
   private static int go() {
      int temp[], ny, nx, qSize, count=-1;
      Queue<int[]> q = new LinkedList<>();
      q.offer(new int[] {size-1, 0});
      while(!q.isEmpty()) {
         qSize = q.size();
         count++;
         for(int i=0; i<qSize; i++) {
            temp = q.poll();
            if(map[temp[0]][temp[1]]=='#') continue;
            if(temp[0]==0 && temp[1]==size-1) return 1;
            for(int d=0; d<=8; d++) {
               ny = temp[0]+dy[d];
               nx = temp[1]+dx[d];
               if(ny<0||nx<0||ny>=size||nx>=size||map[ny][nx]=='#') continue;
               if(count>=8) {
                  if(over8visited[ny][nx]) continue;
                  over8visited[ny][nx]=true;
               }else{
                  if(visited[ny][nx][count]) continue;
                  visited[ny][nx][count]=true;
               }
               q.offer(new int[] {ny, nx});
               
            }
         }
         dropTheMap(count);
      }
      return 0;
   }
   
   private static void dropTheMap(int thisTurn) {
      if(thisTurn>=8) return;
      //0부터 들어올 것임
      for(int i=size-1; i>thisTurn; i--) {
         Arrays.fill(map[i], '.');
         for(int j=0; j<size; j++) {
            map[i][j]= map[i-1][j];
         }
      }
      Arrays.fill(map[thisTurn], '.');
   }

   private static void input() throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      map = new char[size][size];
      visited = new boolean[size][size][size];
      over8visited = new boolean[size][size];
      for(int i=0; i<size; i++) {
         map[i]=br.readLine().toCharArray();
      }
   }
}