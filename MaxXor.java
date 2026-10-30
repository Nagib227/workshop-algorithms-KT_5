import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 6. Максимальный XOR непрерывного фрагмента.
public class MaxXor {
    static int[][] child;
    static int nodes = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine().trim());
        child = new int[n * 31 + 5][2];

        StringTokenizer st = new StringTokenizer(in.readLine());
        insert(0);

        int xor = 0;
        int best = 0;
        for (int i = 0; i < n; i++) {
            xor ^= Integer.parseInt(st.nextToken());
            int got = query(xor);
            if (got > best)
                best = got;
            insert(xor);
        }

        out.println(best);
        out.flush();
    }

    // старшие биты важнее: на каждом шаге пытаемся взять противоположный
    private static void insert(int x) {
        int v = 0;
        for (int bit = 29; bit >= 0; bit--) {
            int b = (x >>> bit) & 1;
            if (child[v][b] == 0)
                child[v][b] = nodes++;
            v = child[v][b];
        }
    }

    private static int query(int x) {
        int v = 0;
        int ans = 0;
        for (int bit = 29; bit >= 0; bit--) {
            int b = (x >>> bit) & 1;
            int want = b ^ 1;
            if (child[v][want] != 0) {
                ans |= 1 << bit;
                v = child[v][want];
            } else {
                v = child[v][b];
            }
        }
        return ans;
    }
}
