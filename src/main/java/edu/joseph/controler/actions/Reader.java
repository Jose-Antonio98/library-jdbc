package edu.joseph.controler.actions;

import java.util.Scanner;

public class Reader {

	private static Scanner reader;

	static {
		reader = new Scanner(System.in);
	}

	public static String readeData() {
		String text = reader.nextLine();
		return text;
	}
}
