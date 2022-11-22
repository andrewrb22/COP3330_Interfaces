import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class sportRunner{  // Runner class
	static Scanner sc = null;
	public static void main(String[] args) throws IOException{
		sc = new Scanner(System.in);
		String files =sc.nextLine();
		List<String> lines = Files.readAllLines(Paths.get(files)); //To read a text file line by line into a List
		Football football = new Football();  // object of football class
		Basketball basketball = new Basketball(); //object of basketball class
		int n = Integer.parseInt(lines.get(0));
		int index = 1;
		Set<Integer> foot = new HashSet<>(); // contains index of football team
		Set<Integer> basket = new HashSet<>(); // contains index of basketball team
		for(int i=0;i<n;i++) {
			String team = lines.get(index);
			if(team.equals("Basketball")) {
				basket.add(i);
			}
			else {
				foot.add(i);
			}
			index++;
		}
		int score[] = new int[n];  // store the score of each team
		int s = Integer.parseInt(lines.get(index++));
		for(int i=0;i<s;i++) {
			String str[] = lines.get(index).trim().split(" "); 
			int ind = Integer.parseInt(str[0]);
			if(foot.contains(ind)) {
				if(str[1].equals("l")) {
					score[ind]+=football.score_large();
				}
				else if(str[1].equals("m")) {
					score[ind]+=football.score_med();
				}
				else {
					score[ind]+=football.score_small();
				}
			}
			else{
				if(str[1].equals("l")) {
					score[ind]+=basketball.score_large();
				}
				else if(str[1].equals("m")) {
					score[ind]+=basketball.score_med();
				}
				else {
					score[ind]+=basketball.score_small();
				}
			}
			index++;
		}
		ArrayList<Pair> list = new ArrayList<>();  // storing index and score as a pair in list
		for(int i=0;i<n;i++) {
			list.add(new Pair(i,score[i]));
		}
		Collections.sort(list,(o1,o2)->{
			return o2.y-o1.y;
		});
		for(Pair p : list) {
			System.out.println(p.x+" - "+p.y);
		}
	}
	static class Pair{
		int x;  // store index
		int y;  // store score
		Pair(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}