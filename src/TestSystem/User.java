package TestSystem;

import java.util.Scanner;

public class User {
	private String name;
	private double grade;
	private String time;
	
	// 有参构造函数
	public User(String name, double grade, String time) {
		this.name = name;
		this.grade = grade;
		this.time = time;
	}
	
	// getter,setter方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	// 输出用户的姓名、成绩和测验时间
	public String toString() {
		String msg;
		msg = "姓名：" + this.name;
		msg += " 成绩：" + this.grade;
		msg += " 测验时间：" + this.time;

		return msg;
	}
}
