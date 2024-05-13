package labs;

import java.util.Random;

public class L1 {
	public static void main(String[] args) {
	   int n = 6, m = n - 1, k = 3;
	   boolean[][] dyz = new boolean[m][m];
	   boolean[][] kon = new boolean[n][n];
	   boolean[] mod = new boolean[n];
	   boolean[] y = new boolean[k];
	   Random rand = new Random();
	   System.out.println("Початкові дані:");
	   for(int i = 0; i < n; i++) {
		   kon[0][i] = rand.nextBoolean();
		   System.out.println("x"+ (i + 1) +" = " + ((kon[0][i]) ? 1 : 0) + ";");
	   }
	   System.out.println("Перші результати:");
	   for(int i = 0; i < m; i++) {
		   dyz[i][i] = kon[i][i] | kon[i][i + 1];
		   System.out.print("dyz(" + (i + 1) + "," + (i + 1) +") = " + ((dyz[i][i]) ? 1 : 0) + ";");
		   kon[i + 1][i + 1] = kon[i][i] & kon[i][i + 1];
		   System.out.println("    kon(" + (i + 1) + "," + (i + 1) +") = " + ((kon[i + 1][i + 1]) ? 1 : 0) + ";");
		   for(int j = i + 1; j < m; j++) {
			   dyz[i][j] = dyz[i][j - 1] | kon[i][j + 1];
			   System.out.print("dyz(" + (i + 1) + "," + (j + 1) +") = " + ((dyz[i][j]) ? 1 : 0) + ";");
			   kon[i + 1][j + 1] = dyz[i][j - 1] & kon[i][j + 1];
			   System.out.println("    kon(" + (i + 1) + "," + (j + 1) +") = " + ((kon[i + 1][j + 1]) ? 1 : 0) + ";");
		   }
	   }
	   System.out.println("Винесення окремих результатів:");
	   System.out.println("kon(" + (n - 1) + "," + (n - 1) +") = " + ((kon[n - 1][n - 1]) ? 1 : 0) + ";");
	   for(int i = 0; i < m; i++) {
		   System.out.println("dyz(" + (m - i) + "," + m +") = " + ((dyz[m - i - 1][m - 1]) ? 1 : 0) + ";");
	   }
	   System.out.println("Другі результати:");
	   mod[n - 1] = false ^ kon[n - 1][n - 1];
	   System.out.println("mod(6) = " + (mod[n - 1] ? 1 : 0) + ";");
	   mod[n - 2] =  kon[n - 1][n - 1] ^ dyz[m - 1][m - 1];
	   System.out.println("mod(5) = " + (mod[n - 2] ? 1 : 0) + ";");
	   for(int i = 2; i < n; i++) {
		   mod[n - i - 1] = dyz[m - i][m - 1]^dyz[m - i + 1][m - 1];
		   System.out.println("mod(" + (n - i) + ") = " + (mod[n - i - 1] ? 1 : 0) + ";");
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