package TestSystem;

import java.util.Random;
import java.util.Scanner;

public class Generate {
	// �������һ����λ�����ڵ�����
	public static int getRandom() {

		Random ran = new Random();

		return ran.nextInt(99);
	}

	// �������һ��������
	public static String getOperator() {

		String[] operateEle = { "+", "-", "*", "/" };
		Random ran = new Random();

		return operateEle[ran.nextInt(4)];
	}
}
