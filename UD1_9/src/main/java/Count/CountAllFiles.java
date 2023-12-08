package Count;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CountAllFiles {
    public static void main(String[] args) throws InterruptedException, IOException {
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        File fileDest = new File(args[1]);

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileDest, true)))) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());

                    // Start a new Java process for CountNumbersFile
                    ProcessBuilder ps = new ProcessBuilder(
                            "java",
                            "-cp",
                            System.getProperty("java.class.path"),
                            "Count.CountNumbersFile",
                            file.getAbsolutePath(),
                            fileDest.getAbsolutePath()
                    );

                    // Redirect the output of the subprocess to the file
                    ps.redirectOutput(ProcessBuilder.Redirect.appendTo(fileDest));

                    // Start the process
                    Process p = ps.start();

                    // Wait for the process to finish
                    p.waitFor();
                    pw.flush();
                }
            }
        }
    }
}
