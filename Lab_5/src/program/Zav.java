package program;

import java.util.Scanner;

public class Zav {
	public static void main(String[] args) {
		String numb = new String();
		String dvaich = new String();
		String vosmer = new String();
		String shistnac = new String();
		String RL = new String();
		boolean minus = false;
		Scanner in = new Scanner(System.in);
		System.out.print("Введіть число: ");
		numb = in.next();
		if(numb.charAt(0) == '-') {
			minus = true;
			dvaich = "-";
			vosmer = "-";
			shistnac = "-";
		}
		int tochka = numb.indexOf('.');
		int celoe = Integer.parseInt(numb.substring(minus ? 1 : 0, (tochka == -1) ? numb.length() : tochka));
		double drob = 0;
		if(tochka != -1) {
			drob = Double.parseDouble("0."+ numb.substring(tochka+1));			
		}
		if(drob != 0) {
			dvaich = dvaich + ConvertCeloe(celoe, 2) + ConvertDrob(drob, 2);
			vosmer = vosmer + ConvertCeloe(celoe, 8) + ConvertDrob(drob, 8);
			shistnac = shistnac + ConvertCeloe16(celoe) + ConvertDrob16(drob);
		}
		else {
			dvaich = dvaich + ConvertCeloe(celoe, 2);
			vosmer = vosmer + ConvertCeloe(celoe, 8);
			shistnac = shistnac + ConvertCeloe16(celoe);
		}
		RL = ConvertRL(dvaich);
		System.out.println("Двійкова система: " + dvaich);
		System.out.println("Восьмирічна система: " + vosmer);
		System.out.println("Шістнадцятирічна система " + shistnac);
		System.out.println("Розрядно-логарифмічна система: " + RL);	
	}
	
	static String ConvertCeloe(int Chislo, int Syst) {
		String Celoe = "";
		for (int i = 0; Chislo > 0; i++) {
			int bub = (Chislo - ((Chislo/Syst)*Syst));			
			Celoe  = bub + Celoe;
			Chislo /= Syst;
		}
		return Celoe;
	}
	
	static String ConvertDrob(double Chislo, int Syst) {
		String Drob = ".";
		for (int i = 1; i <= 10; i++) {
			Chislo *= Syst;
			Drob = Drob + (int)Math.floor(Chislo);
			Chislo -= Math.floor(Chislo);
			if(Chislo == 0) {
				break;
			}
		}

		return Drob;
	}
	
	static String ConvertCeloe16(int Chislo) {
		String Celoe = "";
		for (int i = 0; Chislo > 0; i++) {
			int bub = (Chislo - ((Chislo/16)*16));
			switch(bub) {
			case (10):
				Celoe  = "A" + Celoe;
			break;
			case (11):
				Celoe  = "B" + Celoe;
			break;
			case (12):
				Celoe  = "C" + Celoe;
			break;
			case (13):
				Celoe  = "D" + Celoe;
			break;
			case (14):
				Celoe  = "E" + Celoe;
			break;
			case (15):
				Celoe  = "F" + Celoe;
			break;
			default:
				Celoe  = bub + Celoe;
				break;
			}
			Chislo /= 16;
		}
		return Celoe;
	}
	
	static String ConvertDrob16(double Chislo) {
		String Drob = ".";
		for (int i = 1; i <= 10; i++) {
			Chislo *= 16;
			switch((int)Math.floor(Chislo)) {
			case (10):
				Drob  = Drob + "A";
			break;
			case (11):
				Drob  = Drob + "B";
			break;
			case (12):
				Drob  = Drob + "C";
			break;
			case (13):
				Drob  = Drob + "D";
			break;
			case (14):
				Drob  = Drob + "E";
			break;
			case (15):
				Drob  = Drob + "F";
			break;
			default:
				Drob = Drob + (int)Math.floor(Chislo);
				break;
			}
			Chislo -= Math.floor(Chislo);
			if(Chislo == 0) {
				break;
			}
		}

		return Drob;
	}
	
	static String ConvertRL(String DV) {
		String res = "";
		int point = DV.indexOf(".");
		if (point == -1)
			point = DV.length();
		int start = -1;
		int i;
		for(i = point-1; i >= 0; i--) {
			if(DV.charAt(i) == '-' )
				break;
			start++;
		}
		int counter = 0;
		for(i++; i < DV.length(); i++) {
			if(DV.charAt(i) == '.')
				continue;
			if(DV.charAt(i) == '1') {				
			res = res + start + ".";
			counter++;
			}
			start--;
		}
		res = (DV.charAt(0) == '-') ? "1." : "0." + counter + "." + res;
		return res;
	}	
}
