import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {

	public WordPuzzle(int x, int y) {
		row = x;
		column = y;
		Random random = new Random();
		array = new String[row][column];
		for (int i = 0; i < row ; i++) {
			for (int j = 0 ; j < column ; j++) {
				array[i][j] =  Character.toString((char) (random.nextInt(26) + 97));
			}
		}

		//		array[0][0]= "t"; array[0][1]= "h"; array[0][2]= "i"; array[0][3]= "s";
		//		array[1][0]= "w"; array[1][1]= "a"; array[1][2]= "t"; array[1][3]= "s"; 
		//		array[2][0]= "o"; array[2][1]= "a"; array[2][2]= "h"; array[2][3]= "g"; 
		//		array[3][0]= "f"; array[3][1]= "g"; array[3][2]= "d"; array[3][3]= "t";


	}

	public void printWordPuzzle() {
		// print 
		System.out.println("Word Puzzle Print :");
		for (int j = 0; j < row ; j++) {
			StringBuffer s = new StringBuffer();
			for (int k = 0; k < column; k++) {
				s.append(array[j][k] + " ");
			}
			System.out.println(s);
		}
	}

	private boolean find(StringBuffer s,MyHashTable<String> myHashTable, boolean prefixSearch){
		boolean[] b = myHashTable.containsIsWord(s.toString());   //b[0] is for isActive, b[1] is for isWord
		if(b[1]) {
			wordCount++;
			System.out.println(s);
		} else if(prefixSearch && !b[0]) {
			return false;
		}
		return true;
	}

	public void find(MyHashTable<String> myHashTable, boolean prefixSearch) {
		// horizontal search
		//System.out.println(" =======horizontal search===========");
		wordCount = 0;
		for (int i = 0 ; i < row ; i++ ) {
			for (int j = 0; j < column ; j++) {
				StringBuffer s = new StringBuffer();
				for (int k = j; k < column; k++) {
					s.append(array[i][k]);
					//if (contains(s.toString(),myHashTable, prefixSearch)) {
					if (!find(s,myHashTable, prefixSearch)) {
						break;
					}
				}
				//System.out.println(s);
			}
		}
		// horizontal reverse search
		//System.out.println(" ========horizontal reverse search==========");
		for (int i = 0; i < row ; i++) {
			for (int j = column -1 ; j >= 0; j--) {
				StringBuffer s = new StringBuffer();
				for (int k = j ; k >=0 ; k--) {
					s.append(array[i][k]);
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}

		// vertical search
		//System.out.println(" ========vertical search==========");
		for (int i = 0; i < column ; i ++) {
			for (int j = 0 ; j < row ; j++) {
				StringBuffer s = new StringBuffer();
				for (int k = j ; k < row ; k++ ) {
					s.append(array[k][i]);
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}

		// vertical reverse search 
		//System.out.println("========vertical reverse search ==========");
		for (int i = 0; i < column ; i++) {
			for (int j = row -1 ; j >= 0; j--) {
				StringBuffer s = new StringBuffer();
				for (int k = j ; k >=0 ; k--) {
					s.append(array[k][i]);
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}

		// diagonal (0,0) to (3,3) end upwards
		//System.out.println("======== diagonal (0,0) to (3,3) end upwards==========");
		for(int i = 0 ; i < row; i++ ) {
			int r = i;  // rows
			int ri = r;
			for (int j = 0 ; j <= i ; j++) {
				StringBuffer s = new StringBuffer();
				r = ri - j;
				for (int k = j ; k < column && r >=0 ; k++) {
					s.append(array[r][k]);
					r--;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		for (int i = 1; i < column; i++) {
			int r = row-1;
			int ri = r;
			for (int j = 0 ; j < column-i; j++) {
				StringBuffer s = new StringBuffer();
				r = ri - j;
				for (int k = i+j; k < column && r >=0; k++ ) {
					s.append(array[r][k]);
					r--;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//	System.out.println(s);
			}

		}

		// diagonal (0,0) to (3,3) end downwards
		//System.out.println("======== diagonal (0,0) to (3,3) end downwards ==========");
		for(int i = 0 ; i < row; i++ ) {
			int r = 0;  // rows
			int ri = r;
			for (int j = 0 ; j <= i ; j++) {
				StringBuffer s = new StringBuffer();
				r = ri + j;
				for (int k = i-j ; k >= 0 && r <row ; k--) {
					// System.out.println(r + " " + k);
					s.append(array[r][k]);
					r++;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		for (int i = 1; i < column; i++) {
			int r = i;
			int ri = r;
			for (int j = 0 ; j < column-i; j++) {
				StringBuffer s = new StringBuffer();
				r = ri + j;
				for (int k = column -1-j; k >0 && r < row; k-- ) {
					//System.out.println(r + " " + k);
					s.append(array[r][k]);
					r++;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		// diagonal (3,0) to (0,3) end downwards
		//System.out.println("======== diagonal (3,0) to (0,3) end downwards ==========");
		for(int i = 0 ; i < row; i++ ) {
			int r = row-1 - i;  // rows
			int ri = r;
			for (int j = 0 ; j <= i ; j++) {
				StringBuffer s = new StringBuffer();
				r = ri + j;
				for (int k = j ; k < column && r < row ; k++) {
					//System.out.println(r + " " + k);
					s.append(array[r][k]);
					r++;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		for (int i = 1; i < column; i++) {
			int r = 0;
			int ri = r;
			for (int j = 0 ; j < column-i; j++) {
				StringBuffer s = new StringBuffer();
				r = ri + j;
				for (int k = i+j; k < column && r < row; k++ ) {
					//System.out.println(r + " " + k);
					s.append(array[r][k]);
					r++;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		//System.out.println("======== diagonal (3,0) to (0,3) end upwards ==========");
		for(int i = 0 ; i < row; i++ ) {
			int r = row - 1;  // rows
			int ri = r;
			for (int j = 0 ; j <= i ; j++) {
				StringBuffer s = new StringBuffer();
				r = ri - j;
				for (int k = i-j ; k >= 0 && r >= 0 ; k--) {
					//System.out.println(r + " " + k);
					s.append(array[r][k]);
					r--;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		for (int i = 1; i < column; i++) {
			int r = row - 1 -i;
			int ri = r;
			for (int j = 0 ; j < column-i; j++) {
				StringBuffer s = new StringBuffer();
				r = ri - j;
				for (int k = column -1-j; k >=0 && r >= 0 ; k-- ) {
					//System.out.println(r + " " + k);
					s.append(array[r][k]);
					r--;
					if(s.length()!=1) {
						if (!find(s,myHashTable, prefixSearch)) {
							break;
						}
					}
				}
				//System.out.println(s);
			}
		}
		System.out.println("wordCount =" + wordCount);

	}

	private int row = 0, column = 0;
	private String [][] array;
	private int wordCount = 0;

	public static void main(String[] args) {

		System.out.println(" ==== Generate Random Word Table : ====");
		int row , column;
		boolean prefixSearch = false;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter row :");
		row = sc.nextInt();
		System.out.println("Enter column :");
		column = sc.nextInt();
		WordPuzzle w = new WordPuzzle(row,column);
		System.out.println("Prefix search enhancement :");
		prefixSearch = sc.nextBoolean();
		sc.close();

		//WordPuzzle w = new WordPuzzle(4,4);
		w.printWordPuzzle();
		System.out.println("===== creating HashTable from dictionary ====");
		long startTime = System.currentTimeMillis( );
		String file = "C:\\Users\\hp\\eclipse-workspace\\WordPuzzle\\src\\dictionary.txt"; // change dictionary path
		String line = null;
		MyHashTable<String> myHashTable = new MyHashTable<>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				//System.out.println(line);
				if(prefixSearch) {
					int i = 0;
					int l = line.length();
					StringBuilder s = new StringBuilder();
					while(i < l) {
						s.append(line.charAt(i));
						if(i == l-1) {
							myHashTable.insert(s.toString(), true);
						} else {
							myHashTable.insert(s.toString(), false);
						}
						i++;
					}
				} else {
					myHashTable.insert(line, true);	
				}
			}
			bufferedReader.close();
			long endTime = System.currentTimeMillis( );
			System.out.println( "Elapsed time: " + (endTime - startTime) );

		} catch (FileNotFoundException ex) {
			System.out.println("unable to open file" + file + "");
		}
		catch(IOException ex) {
			System.out.println("Error reading file " + file + "");
		}


		System.out.println("\n \n === find words : === \n");
		if(prefixSearch) {
			System.out.println("==== Enhanced Search Result : ====");
			long startTime2 = System.currentTimeMillis( );
			w.find(myHashTable, prefixSearch);
			long endTime2 = System.currentTimeMillis( );
			System.out.println( "Elapsed time Enhanced search : " + (endTime2 - startTime2) );
		}
		System.out.println("==== Simple Search Result : ====");
		long startTime3 = System.currentTimeMillis( );
		w.find(myHashTable, false);
		long endTime3 = System.currentTimeMillis( );

		System.out.println( "Elapsed time Simple search : " + (endTime3 - startTime3) );
		//myHashTable.print();

	}

}
