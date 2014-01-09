package org.gla.anton.udf.test;

public class GenerateVenuesRegex {

	public static void main(String args[]) {
		String Str = new String("Welcome to Tutorialspoint.com");

		System.out.print("Return Value :");
		System.out.println(Str.matches("((.*)Anto(.*))|((.*)to(.*))"));

		System.out.print("Return Value :");
		System.out.println(Str.matches("Tutorials"));

		System.out.print("Return Value :");
		System.out.println(Str.matches("Welcome(.*)"));
	}

}
