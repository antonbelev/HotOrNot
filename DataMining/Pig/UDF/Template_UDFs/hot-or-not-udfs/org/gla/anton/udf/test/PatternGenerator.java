package org.gla.anton.udf.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class PatternGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		String filePath = "C:/Users/antonbelev/Desktop/HotOrNot_repo/DataMining/Pig/Data/venues_extended_2.csv";
		PrintWriter printWriter = new PrintWriter("venues_regex.txt");
		Scanner scanner = new Scanner(new File(filePath));
		if (!scanner.hasNextLine())
		{
			scanner.close();
			printWriter.close();
			return;			
		}
			
		scanner.nextLine();
		
		printWriter.write(".*(");
		boolean first = true;
		while(scanner.hasNextLine())
		{
			if (!first && scanner.hasNextLine())
				printWriter.write("|");
			printWriter.write("(" + scanner.nextLine().split(",")[0] +")");
			first = false;
		}
		scanner.close();
		printWriter.write(").*");
		printWriter.close();
	}

}
