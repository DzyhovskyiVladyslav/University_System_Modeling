package labs;

import java.util.Random;

public class L2 {
	public static void main(String[] args) {
		 int n = 6, m = 6, k = 3;
		   boolean[][] log = new boolean[n][n];
		   boolean[] mod = new boolean[m];
		   boolean[] y = new boolean[k];
		   Random rand = new Random();
		   System.out.println("Початкові дані:");
		   for(int i = 0; i < n; i++) {
			   log[i][0] = rand.nextBoolean();
			   System.out.println("x"+ (i + 1) +" = " + ((log[i][0]) ? 1 : 0) + ";");
		   }
		   System.out.println("Перші результати:");
		   for(int i = 0; i < n / 2; i++) {
			   log[2 * i][1] = log[2 * i][0] | log[2 * i + 1][0];
			   log[2 * i + 1][1] = log[2 * i][0] & log[2 * i + 1][0]; 
		   }
		   for(int i = 2; i < 4; i++) {
			   log[0][i] = log[0][i - 1] | log[2][i - 1]; 
			   log[1][i] = log[0][i - 1] & log[2][i - 1];
			   log[2][i] = log[1][i - 1] | log[4][i - 1];
			   log[3][i] = log[1][i - 1] & log[4][i - 1];
			   log[4][i] = log[3][i - 1] | log[5][i - 1];
			   log[5][i] = log[3][i - 1] & log[5][i - 1];
		   }
		   log[1][4] = log[1][3] | log[2][3];
		   log[2][4] = log[1][3] & log[2][3];
		   log[3][4] = log[3][3] | log[4][3];
		   log[4][4] = log[3][3] & log[4][3];
		   
		   log[0][5] = log[0][3];
		   log[1][5] = log[1][4];
		   log[2][5] = log[2][4] | log[3][4];
		   log[3][5] = log[2][4] & log[3][4];
		   log[4][5] = log[4][4];
		   log[5][5] = log[5][3];
		   for(int i = 0; i < n; i++) {
			   System.out.println("log("+ (n - i) +"," + (n - 1) + ") = " + ((log[n - i - 1][n - 1]) ? 1 : 0) + ";");
		   }
		   System.out.println("Другі результати:");
		   mod[5] = false ^ log[5][n - 1];
		   System.out.println("mod(6) = " + (mod[5] ? 1 : 0) + ";");

		   for(int i = 1; i < n; i++) {
			   mod[m - i - 1] = log[n - i - 1][n - 1]^log[n - i][n - 1];
			   System.out.println("mod(" + (m - i) + ") = " + (mod[m - i - 1] ? 1 : 0) + ";");
		   }
		   System.out.println("Кінцеві результати:");
		   y[0]= mod[0]|mod[2]|mod[4];
		   y[1]= mod[1]|mod[2]|mod[5];
		   y[2]= mod[3]|mod[4]|mod[5];
		   for(int i = 0; i < k; i++) {
			   System.out.println("y" + (i + 1) + " = " + (y[i] ? 1 : 0) + ";");
		   }
	}
}