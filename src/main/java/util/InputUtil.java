package util;

import java.util.Scanner;

public class InputUtil {
    public static String readLine(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int readInt(Scanner sc, String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }
}
