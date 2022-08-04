import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static PrintWriter pw;
    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Type what to copy : ");
            Path from = Path.of(scan.nextLine());
            System.out.println("Type where to paste : ");
            Path to = Path.of(scan.nextLine());

            copyFiles(from, to);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void copyFiles(Path from, Path to) throws IOException {

        File[] current = new File(String.valueOf(from)).listFiles();

        for (int i = 0; i < Objects.requireNonNull(current).length; i++) {
            if(current[i].isDirectory()) {
                copyDirectory(Path.of(from + File.separator + current[i].getName()),
                        Path.of(to + File.separator + current[i].getName()), current[i]);
            } else {
                Files.copy(current[i].toPath(), new FileOutputStream(to + File.separator + current[i].getName()));
            }
        }
    }

    private static void copyDirectory(Path from, Path to, File file) throws IOException {
        pw = new PrintWriter(new FileWriter(String.valueOf(new File(String.valueOf(to)).mkdir())));

        File[] current = file.listFiles() != null ? file.listFiles() : null;
        if(current == null) {
            return;
        }

        for (File c : current) {
            if (c.isDirectory()) {
                copyDirectory(Path.of(from + File.separator + c.getName()),
                        Path.of(to + File.separator + c.getName()), c);
            } else {
                Files.copy(c.toPath(), new FileOutputStream(to + File.separator + c.getName()));
            }
        }

    }
}
