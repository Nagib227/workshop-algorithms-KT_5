import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 3. Два числа встречаются по одному разу, остальные по два.
public class TwoSingles {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());

        int xor = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            xor ^= a[i];
        }

        // бит, в котором два одиночных числа различаются
        int bit = xor & -xor;
        int first = 0;
        int second = 0;
        for (int x : a) {
            if ((x & bit) == 0)
                first ^= x;
            else
                second ^= x;
        }

        if (first > second) {
            int temp = first;
            first = second;
            second = temp;
        }

        out.println(first + " " + second);
        out.flush();
    }
}
