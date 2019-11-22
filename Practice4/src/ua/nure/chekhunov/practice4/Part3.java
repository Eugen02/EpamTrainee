package ua.nure.chekhunov.practice4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part3 {

	private static final String LS = System.lineSeparator();

	public static final String ENCODING = "Cp1251";

	private static boolean flag;

	private static String fileName = "part3.txt";

	Part3() {
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		Part3.fileName = fileName;
	}

	public void run() throws FileNotFoundException {
		final String res = readFile(fileName, ENCODING);
		input(res);
	}

	public static String readFile(final String path, final String encoding) throws FileNotFoundException {
		final StringBuilder builder = new StringBuilder();
		try (Scanner scan = new Scanner(new FileInputStream(new File(path)), encoding)) {
			while (scan.hasNextLine()) {
				builder.append(scan.nextLine()).append(System.lineSeparator());
			}
			if (flag) {
				throw new IllegalArgumentException();
			}

		}
		return builder.toString();
	}

	public void input(String res) {

		String regex;
		final String letter = "a-zA-Z\\p{InCyrillic}";
		final String startStr = "(^|\\s)([" + letter + "]";
		final String endStr = ")(?=\\s|" + LS + "|$)";
		final String dec = "[0-9]";
		final String startDec = "(^|\\s)([+-]?(";
		final String endDec = "))(?=\\s|" + LS + "|$)";
		Scanner sc = new Scanner(System.in, ENCODING);
		while (sc.hasNext()) {
			String str = sc.nextLine();
			switch (str) {
			case "char":
				regex = startStr + "{1}" + endStr;
				System.out.println(getResTypeParse(regex, res));
				continue;

			case "String":
				regex = startStr + "{2,}" + endStr;
				System.out.println(getResTypeParse(regex, res));
				continue;

			case "int":
				regex = startDec + dec + "+" + endDec;
				System.out.println(getResTypeParse(regex, res));
				continue;

			case "double":
				regex = startDec + "(" + dec + "+[.]" + dec + "*)|([.]" + dec + "+)" + endDec;
				System.out.println(getResTypeParse(regex, res));
				continue;

			case "stop":
				return;

			default:
				System.out.println("Incorrect input");
			}
		}
	}

	public String getResTypeParse(final String regex, String input) {
		String word;
		StringBuilder out = new StringBuilder();
		Matcher m = Pattern.compile(regex).matcher(input);
		boolean found = false;
		while (m.find()) {
			word = m.group(2);
			if (!found) {
				out.append(word);
				found = true;
			} else {
				out.append(" ".concat(word));
			}
		}
		if (!found) {
			out.append("No such values");
		}
		out.append(LS);

		return out.toString();
	}

	public static void main(String[] args) throws IOException {
		Part3 part3 = new Part3();
		part3.run();

	}

}