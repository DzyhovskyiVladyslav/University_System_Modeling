package lol;
import java.util.ArrayList;
import java.util.Scanner;

public class Zav {
	
	public static void main(String[] args) {
		String StrA, StrB;
		Scanner in = new Scanner(System.in);
		System.out.print("Введіть РЛ код a: ");
		RLcode a = new RLcode(in.next());
		System.out.print("Введіть РЛ код b: ");
		RLcode b = new RLcode(in.next());
		RLcode c = new RLcode();
		RLcode d = new RLcode();
		c.mul(a, b);
		System.out.println("Добуток a і b: " + c.printRLcode());	
		d.dil(a, b);
		System.out.println("Частка a і b: " + d.printRLcode());	
	}	
}
class RLcode{ //Клас РЛ коду
	public boolean minus = false;
	public int num = 0;
	public ArrayList<Integer> Data = new ArrayList();
	RLcode(){}		
	RLcode(String str){//Конструктор, який зчитує РЛ код 
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
	public String printRLcode() {//Виведення РЛ коду на єкран
		if(num == 0) {
			return "Error";
		}
		String str = minus ? "1." : "0.";
		str = str + num + ".";
		for (int i = 0; i < num; i++)
			str = str + Data.get(i) + ".";
		return str;
	}
	public void sort() { //Сорутвання РЛ коду
		for(int i = 0; i < num-1; i++) {
			for(int j = i + 1; j < num; j++) {
				if(Data.get(i) < Data.get(j)) {
					int buble = Data.get(i);
					Data.set(i, Data.get(j));
					Data.set(j, buble);
				}
			}
		}
	}
	public void round() {//Зведення до подібних
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
	public void mul(RLcode a, RLcode b) { //Множення
		if(a.num == 0 || b.num == 0) {
			this.minus = false;
			this.num = 0;
			this.Data.clear();
			return;
		}
		for(int i = 0; i < a.num; i++) {
			for(int j = 0; j < b.num; j++) {
				this.Data.add(a.Data.get(i)+b.Data.get(j));
				this.num++;
			}
		}
		this.round();
		if(a.minus == b.minus)
			this.minus = false;
		else
			this.minus = true;
	}
	public void dil(RLcode a, RLcode b) { //Ділення
		if(a.num == 0 || b.num == 0) {
			this.minus = false;
			this.Data.clear();
			if(b.num == 0)
				this.num = -1;
			else
				this.num = 0;
			return;
		}
		while(a.num > 0) {
			this.num++;
			int bub = a.Data.get(0)-b.Data.get(0);
			this.Data.add(bub);
			ArrayList<Integer> bubs = new ArrayList();
			for(int i = 0; i < b.num; i++) {
				bubs.add(b.Data.get(i)+bub);
			}
			if (comparebubs(a.Data, bubs)) {
				while(bubs.size() > 0) {
					if(a.Data.indexOf(bubs.get(0)) != -1){
						a.Data.remove(a.Data.indexOf(bubs.get(0)));
						a.num--;
						bubs.remove(0);
					}
					else {
						for(int i = 1; i < a.num; i++) {
							if(i == a.num - 1 && a.Data.get(i) > bubs.get(0)) {
								a.Data.add(a.Data.get(i)-1);
								a.Data.remove(i);
								bubs.remove(0);
								break;
							}
							if(a.Data.get(i) < bubs.get(0)) {
								a.Data.add(a.Data.get(i-1)-1);
								a.Data.remove(i-1);
								bubs.remove(0);
								break;
							}
						}
					}
				}
			}
			else
				for(int i = 0; i < this.num; i++) {
					this.Data.set(i, this.Data.get(i)-1);
				}
			this.sort();
			if(this.Data.indexOf(-5) != -1) {
				break;
			}
		}
		if(a.minus == b.minus)
			this.minus = false;
		else
			this.minus = true;
	}
	public boolean comparebubs(ArrayList<Integer> first, ArrayList<Integer> second) { //Порівняння для ділення
		int i = 0; 
		while(i < first.size() && i < second.size()) {
			if(first.get(i) > second.get(i))
				return true;
			if(first.get(i) < second.get(i))
				return false;
			i++;
		}
		if(i != first.size() && i == second.size())
			return true;
		if (i == first.size() && i != second.size()) 
			return false;
		return true;
	}
}