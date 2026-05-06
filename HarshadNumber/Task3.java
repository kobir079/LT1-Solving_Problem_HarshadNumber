import java.io.*;
import java.util.*;

public class Task3 {
    private static final int MAX = 1000001;
    private static boolean[] isGenerated = new boolean[MAX];
    private static boolean[] isPrime = new boolean[MAX];
    private static int[] prefixSum = new int[MAX];

    public static void main(String[] args) throws IOException {
        precompute();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String line = br.readLine();
        if (line == null) return;
        
        int Q = Integer.parseInt(line.trim());
        
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            if (A > B) {
                out.println(0);
            } else {
                int count = prefixSum[B] - (A > 0 ? prefixSum[A - 1] : 0);
                out.println(count);
            }
        }
        out.flush();
        out.close();
    }

    private static void precompute() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p < MAX; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < MAX; i += p)
                    isPrime[i] = false;
            }
        }

        for (int i = 1; i < MAX; i++) {
            int nextVal = i + digitSum(i);
            if (nextVal < MAX) {
                isGenerated[nextVal] = true;
            }
        }

        int currentCount = 0;
        for (int i = 0; i < MAX; i++) {
            if (isPrime[i] && !isGenerated[i]) {
                currentCount++;
            }
            prefixSum[i] = currentCount;
        }
    }

    private static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}