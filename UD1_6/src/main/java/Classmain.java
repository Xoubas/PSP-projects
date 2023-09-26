import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * Create a program to execute the command find in Windows to count the number of 
 * lines that one String appears in one file. The number obtained must be written to another
 * file. The String, the name of the file where to search the text and the name of the file
 * where to put the result must be arguments in the command line. Use the ProcessBuilder
 * methods redirectInput and redirectOutput.
 */

public class Classmain {
	public static void main(String[] args) throws IOException {
		String word;
		ProcessBuilder pb = new ProcessBuilder();
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce a string: ");
		word = sc.nextLine();
		sc.close();
		pb.command("cmd.exe", "/c", "find /i /c \"" + word + "\"").redirectInput(new File("Constitucion.txt"));
		pb.redirectOutput(new File("cosa.txt"));
		pb.start();
	}
}
