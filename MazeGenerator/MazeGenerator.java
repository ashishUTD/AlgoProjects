import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MazeGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r,c;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter number of rows:");
		r = s.nextInt();
		System.out.println("Enter number of columns:");
		c = s.nextInt();
		if(r <= 0 || c <= 0) {
			System.out.println(" Please enter values for rows and column greater than 0. ");
		} else {
			DisjSets d = new DisjSets(r*c);
			Random rand = new Random();
			boolean[] hline = new boolean[r*c];
			boolean[] vline = new boolean[r*c];
			Arrays.fill(hline, true);
			Arrays.fill(vline, true);
			int count = 1;

			while(count < r*c) {
				int block1 = rand.nextInt(r*c);
				int block2 = 0;
				int adjBlock = 1+rand.nextInt(4); // choosing random adjacent block (1 -> right, 2 ->down, 3 -> left, 4 -> up)
				if(adjBlock == 1) {
					block2 = block1 + 1;
					if(block2 % c == 0){
						continue;
					}

				} else if (adjBlock == 2) {
					block2 = block1 + c;
					if(block2 >= r*c) {
						continue;
					}
				} else if (adjBlock == 3) {
					block2 = block1 -1;
					if(block1 % c == 0) {
						continue;
					}
				} else {
					block2 = block1 - c;
					if(block2<0) {
						continue;
					}
				}
				if(d.find(block1) != d.find(block2)) {
					//System.out.println(block1 + " " + block2);
					if(block2 == block1+1) {
						vline[block2] = false;
					} else if(block2 == block1+c) {
						hline[block1] = false;
					} else if(block2 == block1-1) {
						vline[block1]= false;
					} else if(block2 == block1- c) {
						hline[block2] = false;
					}
					d.union(d.find(block1), d.find(block2));
					count++;
				}
			}

			for(int i = 0; i < c-1; i++) {
				if(i==0) {
					System.out.print("   ");
				}
				System.out.print("_ ");
			}
			System.out.println( );
			for(int i = 0 ; i < r*c ; i++) {

				if(vline[i] && i !=0) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
				if(hline[i] && i != c*r -1) {
					System.out.print("_");
				} else {
					System.out.print(" ");
				}
				if((i+1)%c == 0 && i != c*r-1) {
					System.out.println("|");
				}
			}
		}
	}
}
