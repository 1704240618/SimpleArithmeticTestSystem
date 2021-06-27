package TestSystem;

import java.util.Random;
import java.util.Scanner;

public class Generate {
	// 随机产生一个两位数以内的整数
	public static int getRandom() {

		Random ran = new Random();

		return ran.nextInt(99);
	}

	// 随机产生一个操作符
	public static String getOperator() {

		String[] operateEle = { "+", "-", "*", "/" };
		Random ran = new Random();

		return operateEle[ran.nextInt(4)];
	}
}
