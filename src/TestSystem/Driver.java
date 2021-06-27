package TestSystem;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver {
	static int num = 0; // ��Ŀ����
	static int number = 0; // ������Ա����
	static User[] array = new User[100]; // �ɼ���������
	static String name; // ������Ա����
	static String gradeList; // ���β���ɼ��б�

	public static void main(String args[]) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("�����������Ա������:");
		name = scan.next();

		int select;
		for (;;) { // ���˵�
			System.out.println("--------------------------------------");
			System.out.println("�������������ϵͳ");
			System.out.println("1���� \n2����\n3��ѯ\n4���а�\n5�˳�");
			System.out.println("��ѡ��1-5����");
			System.out.println("--------------------------------------");
			select = scan.nextInt();
			switch (select) {
			case 5:
				System.out.println("��ȷ��Ҫ�˳���y/n����");
				char s = scan.next().charAt(0);
				if (s == 'y') {
					System.out.println("�˳��ɹ�����ӭ�´μ���ʹ��");
					return;
				} 
				break;
			case 1:
				question();
				break;
			case 2:
				answer();
				break;
			case 3:
				query();
				break;
			case 4:
				sort();
				break;
			default:
				System.out.println("�������ֻ������1-5�����������룺");
				break;
			}
		}
	}

	public static void question() { // ����
		int n;
		Scanner scan = new Scanner(System.in);
		System.out.println("��������Ŀ����(����0�˳�):");

		n = scan.nextInt();
		if (n == 0) {
			System.out.println("�˳��ɹ���");
			return;
		} else {
			num = n;
		}
		System.out.println("��Ŀ����Ϊ" + num);
	}

	public static void answer() throws IOException { // ����
		File file = new File("F:/JuniorFirst/�������/SimpleArithmeticTestSystem/grade.txt");
		Scanner scan = new Scanner(System.in);
		int i = 0; // ��ǰ��i��
		int correct = 0; // ���Ե�����
		String rate; // ��ȷ��
		int inputResult = 0;
		double point = 100.00 / num; // ÿ����ķ�ֵ
		double grade = 0.00;

		if (num == 0) {
			System.out.println("���ȳ��⣡");
			return;
		}
		if (!file.exists()) {
			file.createNewFile();
		} else {
			gradeList = "";
			if (number != 0) {
				System.out.println("�����������Ա��������");
				name=scan.next();
			}
			
			gradeList = gradeList + "������" + name + "\n";
			number = number + 1;

			do {
				Expression expression = new Expression(Generate.getRandom(), Generate.getRandom(), Generate.getOperator());
				System.out.println(expression); // ���������ʽ
				
				System.out.println("��������������");
				inputResult = scan.nextInt(); // �õ��û�����Ľ��

				gradeList = gradeList + "��Ŀ��" + expression + "\n" + "�Լ��Ĵ𰸣�" + inputResult + " ��ȷ�𰸣�" + expression.getResult() 
						+ "\n"; 

				if (inputResult == expression.getResult()) { // �ش���ȷ
					correct++; 
					System.out.println("����ȷ!");
					grade = grade + point; // ����һ��ķ�ֵ
				} else { // �ش����
					System.out.println("�𰸴���\n��ȷ��Ϊ" + expression.getResult());
				}
				i++;
			} while (i < num);
			
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println("������ϣ���ϲ������" + df.format(grade) + "�֣�");
			
			// ����һ����ֵ��ʽ������
			NumberFormat numberFormat = NumberFormat.getInstance();  
			   
	        // ���þ�ȷ��С�����2λ  
	        numberFormat.setMaximumFractionDigits(2);  
	   
	        rate = numberFormat.format((float) correct / (float) num * 100);	        	     
	        
			gradeList = gradeList + "�ɼ���" + df.format(grade) + "\n" + "��ȷ�ʣ�" + rate + "%" + "\n";
			
			BigDecimal bd = new BigDecimal(grade); 
			double g = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
			Date today = new Date();
			String todayStr = sdf.format(today);
			User user = new User(name, g, todayStr); // ʵ�����û�����
			array[number] = user; // �����û���Ϣ������
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String last = br.readLine();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(last + user.toString()); // ���ļ���ʽ��Ų����˵�����������ɼ�������ʱ��
			
			System.out.println("�ɼ��ѱ�����F:\\JuniorFirst\\�������\\SimpleArithmeticTestSystem\\grade.txt");
			bw.close();
			br.close();
		}
	}

	public static void query() { // ��ѯ
		if (number == 0) {
			System.out.println("���ȴ���!");
			return;
		}
		System.out.println("���β����������:");
		System.out.println(gradeList);
	}

	public static void sort() { // ����
		if (number == 0) {
			System.out.println("���ȴ���!");
			return;
		}
		System.out.println("������ʾ���в�����Ա������");
		for (int i = 1; i <= number; i++) {
			for (int j = i + 1; j <= number; j++) {
				// ����������С���󼴳ɼ��ɴ�С����
				if (array[i].getGrade() < array[j].getGrade()) {
					User temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		int k = 1;
		for (; k <= number; k++) {
			System.out.println("������" + k + " " + array[k] + "");
		}
	}
}
