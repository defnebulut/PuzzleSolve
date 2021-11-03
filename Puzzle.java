import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Defne BULUT
 * @since 30.10.2021
 */
public class Puzzle {
    static ArrayList<String> puzzle = new ArrayList<>();
    static String first, second, result, all;
    static int counter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> S = new ArrayList<>();
        ArrayList<String> U = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            U.add(Integer.toString(i));

        do {
            puzzle.clear();
            System.out.print("Enter 1st input value:\t");
            first = sc.next();
            System.out.print("Enter 2nd input value:\t");
            second = sc.next();
            System.out.print("Enter Output value:\t");
            result = sc.next();
            all = first + second + result;
            for(char c : all.toCharArray())
                if (!puzzle.contains(String.valueOf(c))){
                    puzzle.add(String.valueOf(c));
                }
        }while (!puzzleCreation(first,second,result,puzzle)) ;
        sc.close();

        System.out.print("searching...");
        puzzleSolve(puzzle.size(),S,U);
        if (counter==0) System.out.print("not found");
    }
    public static void puzzleSolve(int k, ArrayList<String> S,
                                   ArrayList<String> U) {
        for (int i = 0; i < U.size(); i++) {
            String e = U.get(i);
            S.add(e);
            U.remove(e);
            if (k == 1) {
                check(S);
            } else {
                puzzleSolve(k - 1, S, U);
            }
            S.remove(e);
            U.add(i, e);
        }
    }
    public static void check(ArrayList<String> S) {
        String checking = all;
        int index = 0;
        for (String s : puzzle) {
            checking = checking.replaceAll(s, S.get(index));
            index++;
        }
        String firstTrying = checking.substring(0,first.length());
        String secondTrying = checking.substring(first.length()
                , second.length()+first.length());
        String resTrying = checking.substring(all.length()-result.length());

        if (Integer.parseInt(firstTrying)+Integer.parseInt(secondTrying) ==
        Integer.parseInt(resTrying)) {
            counter++;
            System.out.print("found!\n"+ first+":\t" + firstTrying + "\n");
            System.out.println(second + ":\t" + secondTrying);
            System.out.println(result + ":\t" + resTrying + "\n");
        }
    }

    public static boolean puzzleCreation(String first, String second,
                                         String result, ArrayList<String> s){
        System.out.println("Input lengths must be between 2-6 and " +
                "total size of unique letters must be less than 11.");
        return first.length() >= 2 && first.length() <= 6
                && second.length() >= 2 && second.length() <= 6
                && result.length() >= 2 && result.length() <= 6
                && s.size() <= 10;
    }
}
