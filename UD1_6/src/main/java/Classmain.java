import java.io.File;
import java.io.IOException;

/*
 * Create a program to execute the command find in Windows to count the number of
 * lines that one String appears in one file. The number obtained must be written to another
 * file. The String, the name of the file where to search the text and the name of the file
 * where to put the result must be arguments in the command line. Use the ProcessBuilder
 * methods redirectInput and redirectOutput.
 */

public class Classmain {
    public static void main(String[] args) throws IOException {
        try {
            String word = "ley";
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("cmd.exe", "/c", "find /i /c \"" + word + "\"");
            pb.redirectInput(new File("Constitucion.txt"));
            pb.redirectOutput(new File("cosa.txt"));
            Process p = pb.start();
            System.out.println("The process has ended " + p.waitFor());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
