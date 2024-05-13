package labs;

import java.util.Scanner;

public class Lab_3 {
	public static void main(String[] args) {
		String text = new String();
		Scanner in = new Scanner(System.in);
		System.out.println("Введіть опис моделі:");
		text = in.next();
		if(text.equals("0")) {
			text = "(A1A2OP1)(FO1OP2)(FO2OP3)(FO3OP4)(FO4OP5)(FO5OP6)(FO2FO6OP7)(FO7OP8)(FO2FO8OP9)(FO9OP10)(FO10OP11)(FO11OP12)(FO12OP13)(FO13OP14)(FO5FO14OP15)(FO15OP16)(FO16OP17)";
			//(A1A2OP1)(FO1OP2)(FO2OP3)(FO1FO3OP4)(FO4OP5)(FO5FO1OP6)(FO6FO1OP7)
			//(A1A2OP1)(FO1OP2)(FO2OP3)(FO2OP4)(FO4OP5)(FO4OP6)(FO5FO6OP7)(FO3FO7OP8)
			//(A1A2OP1)(FO1OP2)(FO2OP3)(FO3OP4)(FO1OP5)(FO5OP6)(FO6OP7)(FO4FO7OP8)(A1A2OP1)(FO1OP2)(FO2OP3)(FO3OP4)(FO1OP5)(FO5OP6)(FO6OP7)(FO4FO7OP8)
			System.out.println(text);
		}
		System.out.println("\n(\t500i\t500i\t400i\t)\tРанг\tНомер\t|");
		System.out.println("--------------------------------------------------------|");
		int rankcount = 0;
		for (int i = 0; i < text.length()-1; i++) {
			if(text.charAt(i) == 'O' && text.charAt(i+1) == 'P') {
				rankcount++;
			}		
		}
		int[] rank = new int[rankcount];
		int R1 = 1, R2 = 1;
		boolean switcher = false, error = false, nextFO = false;
		int strcount = 1, buf;
		for(int i = 0; i < text.length(); i++) {

			switch(text.charAt(i)) {
			case ('('):
				System.out.print("2000\t");
			break;
			case (')'):
				System.out.println("3000\t" + rank[strcount-1] + "\t" + strcount++ + "\t|");
			break;
			case('A'):
				buf = text.charAt(++i) - '0';
				i++;
				while(text.charAt(i) >= '0' && text.charAt(i) <= '9') {
					buf = buf*10+text.charAt(i++) - '0';
				}
				rank[0] = 2;
				i--;
				System.out.print((1000+buf) + "\t");
			break;
			case ('O'):
				i+=2;
				buf = text.charAt(i) - '0';
				i++;
				while(text.charAt(i) >= '0' && text.charAt(i) <= '9') {
					buf = buf*10+text.charAt(i++) - '0';
				}
				i--;
				if(nextFO) {
					System.out.print("0000\t");
					nextFO = false;
				}
				System.out.print((4000+buf) + "\t");
				if(strcount > 1) {
					rank[strcount - 1] = Math.max(R1, R2)+ 1;					
				}
				switcher = false;
			break;
			case ('F'):
				i+=2;
				buf = text.charAt(i) - '0';
				i++;
				while(text.charAt(i) >= '0' && text.charAt(i) <= '9') {
					buf = buf*10+text.charAt(i++) - '0';
				}
				i--;
				System.out.print((5000+buf) + "\t");
				if(switcher) {
					R2 = rank[buf-1];
				}
				else {
					R1 = rank[buf-1];
					switcher = true;
				}
				nextFO = !nextFO;
			break;
			default:
				error = true;
				System.out.print("\nДані були введенні неправильно");
			break;
			}
			if(error) {
				break;
			}
		}
	}
}
