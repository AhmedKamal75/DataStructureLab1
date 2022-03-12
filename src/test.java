import java.util.Scanner;

public class test {
    public static void main(String[] args) {

    }

    private static void foo(){
        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        int input = scanner.nextInt();
        switch (input) {
            case 0 -> {
//                Integer.parseInt(input)
                System.out.println(0);
                int i = 1;
                i++;
                System.out.println(i);
            }
            case 1 -> {
                System.out.println(1);
                int k = 1;
                k++;
                System.out.println(k);
            }
            case 2 -> {
                System.out.println(2);
                int j = 1;
                j++;
                System.out.println(j);
            }
            default -> System.out.println("default: " + input);
        }
    }
}


