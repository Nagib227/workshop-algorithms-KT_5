import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// Задача 1. Все правильные скобочные последовательности из n пар, в словарном порядке.
public class Brackets {
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine().trim());
        build(new StringBuilder(), 0, 0, n);
        out.flush();
    }

    // сначала ставим '(', он в алфавите раньше ')'
    private static void build(StringBuilder cur, int open, int close, int n) {
        if (cur.length() == n * 2) {
            out.println(cur);
            return;
        }
        if (open < n) {
            cur.append('(');
            build(cur, open + 1, close, n);
            cur.setLength(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            build(cur, open, close + 1, n);
            cur.setLength(cur.length() - 1);
        }
    }
}
