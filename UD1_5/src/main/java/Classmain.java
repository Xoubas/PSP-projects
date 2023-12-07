import java.io.File;
import java.io.IOException;

public class Classmain {
	public static void main(String[] args) throws IOException {
//		Create a process to launch in the command line a tracert to iessanclemente.net.
//		Redirect the process output to a file called outputTracert.txt using the redirectOutput method of ProcessBuilder
//		Wait for 500 miliseconds after the start of the process and destroy the process after that time throwing an InterruptedException.
//		Verify the content of the outputTracert.txt file.

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command("cmd.exe", "/c", "tracert www.iessanclemente.net");
			builder.redirectOutput(new File("Output.txt"));
			Process p =builder.start();
			Thread.sleep(500);
			p.destroy();
			p.waitFor();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
