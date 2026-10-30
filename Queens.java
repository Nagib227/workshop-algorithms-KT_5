import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 4. Сколько способов поставить n ферзей, не задевая запрещённые клетки.
public class Queens {
    static int n;
    static int all;
    static int[] blocked;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        all = (1 << n) - 1;
        blocked = new int[n];

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(in.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            blocked[row] |= 1 << col;
        }

        place(0, 0, 0, 0);
        out.println(ans);
        out.flush();
    }

    // cols — занятые столбцы, diag и anti сдвигаются при переходе на следующую строку
    private static void place(int row, int cols, int diag, int anti) {
        if (row == n) {
            ans++;
            return;
        }

        int free = all & ~(cols | diag | anti | blocked[row]);
        while (free != 0) {
            int bit = free & -free;
            free ^= bit;
            place(row + 1, cols | bit, (diag | bit) << 1, (anti | bit) >>> 1);
        }
    }
}
