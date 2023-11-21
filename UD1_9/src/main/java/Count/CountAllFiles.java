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
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                ProcessBuilder ps = new ProcessBuilder();
                ps.command().add("java");
                ps.command().add("-cp");
                String classpath = System.getProperty("java.class.path");
                ps.command().add(classpath);
                ps.command().add("main.Count.CountNumbersFile");
                ps.command().add(args[0]);
                String fileData = file.getName();
                ps.command().add(file.getName());
                FileWriter fw = new FileWriter(fileDest, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(fileData);
                pw.println("====================");
                pw.flush();
                ps.redirectOutput(ProcessBuilder.Redirect.appendTo(fileDest));
                Process p = ps.start();
                p.waitFor();
                pw.println("====================");
                pw.println();
                pw.flush();
            }
        }
    }
}
