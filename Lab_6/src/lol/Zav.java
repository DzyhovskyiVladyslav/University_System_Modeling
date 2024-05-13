package lol;
import java.util.ArrayList;
import java.util.Scanner;

public class Zav {
	
	public static void main(String[] args) {
		String StrA, StrB;
		Scanner in = new Scanner(System.in);
		System.out.print("Ââåä³òü ĞË êîä a: ");
		RLcode a = new RLcode(in.next());
		System.out.print("Ââåä³òü ĞË êîä b: ");
		RLcode b = new RLcode(in.next());
		RLcode c = new RLcode();
		System.out.println(compare(a, b));
		c.combine(a, b);
		System.out.println(c.printRLcode());
		c.sort();
		System.out.println(c.printRLcode());
		c.round();
		System.out.println(c.printRLcode());		
	}	
	static String compare(RLcode a, RLcode b){
		if(!a.minus && b.minus)
			return "a > b";
		if(a.minus && !b.minus)
			return "a < b";
		int reverse = 1;
		if(a.minus && b.minus)
			reverse = -1;	
		int size = (a.num > b.num) ? a.num : b.num;
		for(int i = 0; i < size; i++) {
			if (i == a.num && i != b.num && reverse == 1)
				return "a < b";
			if (i == a.num && i != b.num && reverse == -1)
				return "a > b";
			if (i == b.num && i != a.num && reverse == 1)
				return "a > b";	
			if (i == b.num && i != a.num && reverse == -1)
				return "a < b";			
			if(a.Data.get(i)*reverse > b.Data.get(i)*reverse)
				return "a > b";
			else if(a.Data.get(i)*reverse < b.Data.get(i)*reverse)
				return "a < b";
		}
		return "a = b";
	}
}

class RLcode{
	public boolean minus = false;
	public int num = 0;
	public ArrayList<Integer> Data = new ArrayList();
	RLcode(){}		
	RLcode(String str){
		minus = (str.charAt(0) == '1') ? true : false;
		int i;
		for(i = 2; i < str.length(); i++) {
			if (str.charAt(i) == '.')
				break;
			num = num*10 + Integer.parseInt(String.valueOf(str.charAt(i)));			
		}		
		int j = 0;
		Data.add(0);
		for(i++; i < str.length(); i++) {
			if(str.charAt(i) == '.') {
				if(i == str.length()-1)
					break;
				j++;	
				Data.add(0);
				continue;
			}
			Data.set(j, Data.get(j)*10 + Integer.parseInt(String.valueOf(str.charAt(i))));
		}
	}
	public void combine(RLcode a, RLcode b) {
		minus = a.minus;
		num = a.num + b.num;
		for(int i = 0; i < a.num; i++)
			Data.add(a.Data.get(i));
		for(int i = 0; i < b.num; i++)
			Data.add(b.Data.get(i));
	}
	public String printRLcode() {
		String str = minus ? "1." : "0.";
		str = str + num + ".";
		for (int i = 0; i < num; i++)
			str = str + Data.get(i) + ".";
		return str;
	}
	public void sort() {
		for(int i = 0; i < num-1; i++)
			for(int j = i + 1; j < num; j++) {
				if(Data.get(i) < Data.get(j)) {
					int buble = Data.get(i);
					Data.set(i, Data.get(j));
					Data.set(j, buble);
				}
			}
	}
	public void round() {
		sort();
		int i = 0; 
		while(i < num-1) {
			if(Data.get(i) == Data.get(i+1)) {
				Data.set(i, Data.get(i)+1);
				Data.remove(i+1);
				i = 0;
				num--;
			}
			else {
				i++;
			}
		}
	}
}
