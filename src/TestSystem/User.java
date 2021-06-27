package TestSystem;

import java.util.Scanner;

public class User {
	private String name;
	private double grade;
	private String time;
	
	// �вι��캯��
	public User(String name, double grade, String time) {
		this.name = name;
		this.grade = grade;
		this.time = time;
	}
	
	// getter,setter����
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
	
	// ����û����������ɼ��Ͳ���ʱ��
	public String toString() {
		String msg;
		msg = "������" + this.name;
		msg += " �ɼ���" + this.grade;
		msg += " ����ʱ�䣺" + this.time;

		return msg;
	}
}
