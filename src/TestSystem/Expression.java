package TestSystem;

public class Expression{
	private int number1; // �������1
	private int number2; // �������2
	private String operator; // �����
	private static int grade = 0; // �ɼ�
	
	// �вι��캯��
	public Expression(int number1, int number2, String operator) {

		this.number1 = number1;
		this.number2 = number2;
		this.operator = operator;
	}
	
	// getter,setter����
	public float getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public float getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	
	// ���������ɵ�������ʽ
	public String toString() {

		return this.number1 + this.operator + this.number2;
	}

	// ���ݲ�ͬ��������õ���Ӧ��������
	public int getResult() {

		int result = 0;
		switch (this.operator) {

		case "+":
			result = this.number1 + this.number2;
			break;

		case "-":
			result = this.number1 - this.number2;
			break;

		case "*":
			result = this.number1 * this.number2;
			break;

		case "/":
			result = this.number1 / this.number2;
			break;
		}

		return result;
	}
}
