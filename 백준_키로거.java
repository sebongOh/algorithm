import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_키로거 {
	static ArrayList<Character> list;
	static ArrayList<Character> temp;
	static char [] ch;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			list = new ArrayList<>();
			temp = new ArrayList<>();
			String s = br.readLine();
			ch = s.toCharArray();
			for (int i = 0; i <ch.length; i++) {
				if (ch[i] == '-') {
					if(list.size()==0)continue;
					list.remove(list.size() - 1);
				} else if (ch[i] == '<') {
					if(list.size()==0)continue;
					temp.add(list.get(list.size() - 1));
					list.remove(list.size() - 1);
				}else if(ch[i]=='>') {
					if(temp.size()==0)continue;
					list.add(temp.get(temp.size()-1));
					temp.remove(temp.size()-1);
				}else {
					list.add(ch[i]);
				}
			}
			if(temp.size()>0) {
				for(int i=0;i<temp.size();i++) {
					list.add(temp.get(i));
				}
			}
			
			for(int i=0;i<list.size();i++) {
				System.out.print(list.get(i));
			}
			
		}

	}

}