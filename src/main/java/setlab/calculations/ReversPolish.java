package setlab.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversPolish {

    public static float get(String command) {
        ArrayList<String> input = getTokens(command);
        return calc(input);
    }

    private static float calc(ArrayList<String> input) {
        HashMap<String, Integer> operations;
        operations = new HashMap<>();
        operations.put("+", 1);
        operations.put("-", 1);
        operations.put("*", 2);
        operations.put("/", 2);
        operations.put("^", 3);

        Stack<Integer> A = new Stack<>();
        Stack<String> B = new Stack<>();

        return 0f;
    }

    private static ArrayList<String> getTokens(String line) {
        ArrayList<String> res = new ArrayList<>();
        String pattern = "([\\s]{0,}(\\d{0,7})[\\s]{0,})|\\W";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(line);

        try {
            while (m.find()) {
                res.add(m.group(0));
            }
        } catch (Exception ex) {
            System.err.println("Ошибка getTokens() не найдено совпадение");
        }
        return res;
    }
}
