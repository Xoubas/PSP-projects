package Count;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class CountNumbersFile {
    public static void countNumbers(File fIn, File fOut) throws IOException {
        String line;
        BigDecimal total = new BigDecimal(0);
        BufferedReader br = null;
        BufferedWriter bw = null;

        // Read file

        try {
            br = new BufferedReader(new FileReader(fIn));
            while ((line = br.readLine()) != null) {
                double n = Double.parseDouble(line);
                total = total.add(BigDecimal.valueOf(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write file
        try {
            bw = new BufferedWriter(new FileWriter(fOut, true));
            bw.write(total.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            bw.close();
        }
    }

    public static void main(String[] args) throws IOException {
        File fIn = new File(args[0]);
        File fOut = new File(args[1]);
        countNumbers(fIn, fOut);
    }
}
