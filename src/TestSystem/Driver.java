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
	static int num = 0; // 题目数量
	static int number = 0; // 测验人员人数
	static User[] array = new User[100]; // 成绩排序数组
	static String name; // 测验人员姓名
	static String gradeList; // 本次测验成绩列表

	public static void main(String args[]) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入测验人员的姓名:");
		name = scan.next();

		int select;
		for (;;) { // 主菜单
			System.out.println("--------------------------------------");
			System.out.println("简单算术运算测验系统");
			System.out.println("1出题 \n2答题\n3查询\n4排行榜\n5退出");
			System.out.println("请选择（1-5）：");
			System.out.println("--------------------------------------");
			select = scan.nextInt();
			switch (select) {
			case 5:
				System.out.println("您确认要退出吗（y/n）：");
				char s = scan.next().charAt(0);
				if (s == 'y') {
					System.out.println("退出成功！欢迎下次继续使用");
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
				System.out.println("输入错误！只能输入1-5，请重新输入：");
				break;
			}
		}
	}

	public static void question() { // 出题
		int n;
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入题目数量(输入0退出):");

		n = scan.nextInt();
		if (n == 0) {
			System.out.println("退出成功！");
			return;
		} else {
			num = n;
		}
		System.out.println("题目数量为" + num);
	}

	public static void answer() throws IOException { // 答题
		File file = new File("F:/JuniorFirst/软件测试/SimpleArithmeticTestSystem/grade.txt");
		Scanner scan = new Scanner(System.in);
		int i = 0; // 当前第i题
		int correct = 0; // 做对的题数
		String rate; // 正确率
		int inputResult = 0;
		double point = 100.00 / num; // 每道题的分值
		double grade = 0.00;

		if (num == 0) {
			System.out.println("请先出题！");
			return;
		}
		if (!file.exists()) {
			file.createNewFile();
		} else {
			gradeList = "";
			if (number != 0) {
				System.out.println("请输入测验人员的姓名：");
				name=scan.next();
			}
			
			gradeList = gradeList + "姓名：" + name + "\n";
			number = number + 1;

			do {
				Expression expression = new Expression(Generate.getRandom(), Generate.getRandom(), Generate.getOperator());
				System.out.println(expression); // 输出运算表达式
				
				System.out.println("请输入运算结果：");
				inputResult = scan.nextInt(); // 得到用户输入的结果

				gradeList = gradeList + "题目：" + expression + "\n" + "自己的答案：" + inputResult + " 正确答案：" + expression.getResult() 
						+ "\n"; 

				if (inputResult == expression.getResult()) { // 回答正确
					correct++; 
					System.out.println("答案正确!");
					grade = grade + point; // 加上一题的分值
				} else { // 回答错误
					System.out.println("答案错误！\n正确答案为" + expression.getResult());
				}
				i++;
			} while (i < num);
			
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.println("答题完毕，恭喜你获得了" + df.format(grade) + "分！");
			
			// 创建一个数值格式化对象
			NumberFormat numberFormat = NumberFormat.getInstance();  
			   
	        // 设置精确到小数点后2位  
	        numberFormat.setMaximumFractionDigits(2);  
	   
	        rate = numberFormat.format((float) correct / (float) num * 100);	        	     
	        
			gradeList = gradeList + "成绩：" + df.format(grade) + "\n" + "正确率：" + rate + "%" + "\n";
			
			BigDecimal bd = new BigDecimal(grade); 
			double g = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
			Date today = new Date();
			String todayStr = sdf.format(today);
			User user = new User(name, g, todayStr); // 实例化用户对象
			array[number] = user; // 保存用户信息到数组
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String last = br.readLine();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(last + user.toString()); // 以文件形式存放测验人的姓名，测验成绩，测验时间
			
			System.out.println("成绩已保存至F:\\JuniorFirst\\软件测试\\SimpleArithmeticTestSystem\\grade.txt");
			bw.close();
			br.close();
		}
	}

	public static void query() { // 查询
		if (number == 0) {
			System.out.println("请先答题!");
			return;
		}
		System.out.println("本次测验情况如下:");
		System.out.println(gradeList);
	}

	public static void sort() { // 排序
		if (number == 0) {
			System.out.println("请先答题!");
			return;
		}
		System.out.println("升序显示所有测验人员排名：");
		for (int i = 1; i <= number; i++) {
			for (int j = i + 1; j <= number; j++) {
				// 根据名次由小到大即成绩由大到小排序
				if (array[i].getGrade() < array[j].getGrade()) {
					User temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		int k = 1;
		for (; k <= number; k++) {
			System.out.println("排名：" + k + " " + array[k] + "");
		}
	}
}
