package lol;
import java.util.ArrayList;
import java.util.Scanner;

public class Zav {
	
	public static void main(String[] args) {
		String StrA, StrB;
		Scanner in = new Scanner(System.in);
		System.out.print("������ �� ��� a: ");
		RLcode a = new RLcode(in.next());
		System.out.print("������ �� ��� b: ");
		RLcode b = new RLcode(in.next());
		RLcode c = new RLcode();
		c.sum(a, b);
		System.out.println("���� a � b: " + c.printRLcode());	
		c.sub(a, b);
		System.out.println("г����� a � b: " + c.printRLcode());	
	}	
	public static int compare(RLcode a, RLcode b){ //����� ��� ���������
		if(!a.minus && b.minus)
			return 1;
		if(a.minus && !b.minus)
			return -1;
		int reverse = 1;
		if(a.minus && b.minus)
			reverse = -1;	
		int size = (a.num > b.num) ? a.num : b.num;
		for(int i = 0; i < size; i++) {
			if (i == a.num && i != b.num && reverse == 1)
				return -1;
			if (i == a.num && i != b.num && reverse == -1)
				return 1;
			if (i == b.num && i != a.num && reverse == 1)
				return 1;	
			if (i == b.num && i != a.num && reverse == -1)
				return -1;			
			if(a.Data.get(i)*reverse > b.Data.get(i)*reverse)
				return 1;
			else if(a.Data.get(i)*reverse < b.Data.get(i)*reverse)
				return -1;
		}
		return 0;
	}
}

class RLcode{ //���� �� ����
	public boolean minus = false;
	public int num = 0;
	public ArrayList<Integer> Data = new ArrayList();
	RLcode(){}		
	RLcode(String str){//�����������, ���� ����� �� ��� 
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
	public void combine(RLcode a, RLcode b) {//��'������� ���� �� ����
		minus = a.minus;
		num = a.num + b.num;
		for(int i = 0; i < a.num; i++)
			Data.add(a.Data.get(i));
		for(int i = 0; i < b.num; i++)
			Data.add(b.Data.get(i));
	}
	public String printRLcode() {//��������� �� ���� �� �����
		String str = minus ? "1." : "0.";
		str = str + num + ".";
		for (int i = 0; i < num; i++)
			str = str + Data.get(i) + ".";
		return str;
	}
	public void sort() { //���������� �� ����
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
	public void round() {//�������� �� �������
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
	public void sum(RLcode a, RLcode b) { //��������� ����� ��'�������, ���������� �� ��������
		if(a.num == 0 && b.num != 0) {
			this.minus = b.minus;
			this.num = b.num;
			this.Data = b.Data;
		}
		else if(a.num != 0 && b.num == 0) {
			this.minus = a.minus;
			this.num = a.num;
			this.Data = a.Data;
		}
		else if(a.num == 0 && b.num == 0) {
			this.minus = false;
			this.num = 0;
			this.Data.clear();
		}
		else {
			this.combine(a, b);
			this.sort();
			this.round();
		}
	}
	public void sub(RLcode a, RLcode b) { //³�����
		if(a.num == 0 && b.num != 0) {
			this.minus = b.minus;
			this.num = b.num;
			this.Data = b.Data;
		}
		else if(a.num != 0 && b.num == 0) {
			this.minus = a.minus;
			this.num = a.num;
			this.Data = a.Data;
		}
		else if(a.num == 0 && b.num == 0) {
			this.minus = false;
			this.num = 0;
			this.Data.clear();
		}
		else {
			RLcode zmen;
			RLcode vid;
			if(Zav.compare(a, b) == 1) {//����������, ��� � ���� ����� �����
				zmen = a;
				vid = b;
				this.minus = zmen.minus;
				
			}
			else if(Zav.compare(a, b) == -1) {
				zmen = b;
				vid = a;
				this.minus = !zmen.minus;
			}
			else {
				this.minus = false;
				this.num = 0;
				this.Data.clear();
				return;
			}
			while (vid.num != 0) {//����������, ���� ��'����� �� ������� 0
				for(int i = 0; i < zmen.num; i++) {//���������� ��������� �������
					for(int j = 0; j < vid.num; j++) {
						if(zmen.Data.get(i) == vid.Data.get(j)) {
							zmen.Data.remove(i);
							zmen.num--;						
							vid.Data.remove(j);
							vid.num--;							
							j--;							
						}
					}
				}
				if(vid.num != 0) { //����������, ���� ��'����� �� ���� � ���� ��������
					if (zmen.num == 1) {//���� � ���������� �� ���� ���� �������� ���� ����������� �� ���
						zmen.Data.add(zmen.Data.get(0)-1);
						zmen.Data.add(zmen.Data.get(0)-1);
						zmen.Data.remove(0);
						zmen.num++;
					}
					else //���� � ���������� �� ����� �� ���� �������� 
						for(int i = 1; i < zmen.num; i++) {//��������� ��������� ����� �� ��'������
							if(i ==  zmen.num-1) { //��������� �������� ������� ��������� 
								zmen.Data.add(zmen.Data.get(i)-1);
								zmen.Data.add(zmen.Data.get(i)-1);
								zmen.Data.remove(i);
								zmen.num++;
								break;
							}
							if(zmen.Data.get(i) < vid.Data.get(0)) {//�������� i-�� ������������ �� ������� ��'������
								zmen.Data.add(zmen.Data.get(i-1)-1);
								zmen.Data.add(zmen.Data.get(i-1)-1);
								zmen.Data.remove(i-1);
								zmen.num++;
								zmen.sort();
								break;
							}
						}
					
				}
				
			}			
			this.num = zmen.num;
			this.Data = zmen.Data;
		}
	}
}