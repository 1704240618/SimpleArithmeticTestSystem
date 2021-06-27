package TestSystem;

public class Expression{
	private int number1; // 随机整数1
	private int number2; // 随机整数2
	private String operator; // 运算符
	private static int grade = 0; // 成绩
	
	// 有参构造函数
	public Expression(int number1, int number2, String operator) {

		this.number1 = number1;
		this.number2 = number2;
		this.operator = operator;
	}
	
	// getter,setter方法
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
	
	// 输出随机生成的运算表达式
	public String toString() {

		return this.number1 + this.operator + this.number2;
	}

	// 根据不同的运算符得到相应的运算结果
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
