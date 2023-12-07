import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Classmain {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to print?: ");
        String phrase = sc.next();
        sc.close();
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("cmd.exe", "/c", "echo " + phrase).inheritIO();
        builder.start();
        Map<String, String> envir = builder.environment();
        for (String clave : envir.keySet()) {
            String valor = envir.get(clave);
            System.out.println("CLAVE: " + clave + "   VALOR: " + valor);
        }
    }
}
