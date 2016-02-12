package DynamicProgramming;

/*
Given a value N, if we want to make change for N cents, and we have infinite
supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we
make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions:
{1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For N = 10 and S = {2, 5, 3, 6},
there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
So the output should be 5.

*/
public class CoinChange {
    /*
    Native recursive solution
    Time Complexity: O(2^n)
     */
    public static int count(int[] S, int n) {
        if(S == null || S.length == 0)
            return 0;

        return count(S, S.length, n);
    }

    public static int count(int[] S, int m, int n) {
        //when target value is 0, so there is 1 way(do not include any coins).
        if(n == 0)
            return 1;

        if(n < 0)
            return 0;

        //there is no conins and target value is larger than 0, then no solution
        if(m <= 0 && n >= 1)
            return 0;

        return count(S, m-1, n) + count(S, m, n - S[m-1]);
    }


    /*
    DP solution
    Time Complexity: O(mn), space: O(n^2)
     */
    public static int countBetter(int[] S, int n) {
        int m = S.length;
        int[][] table = new int[m+1][n+1];

        //fill the table
        for(int i = 0; i < m+1; i++) {
            table[i][0] = 1;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int x = (j - S[i-1] >= 0) ? table[i][j - S[i-1]] : 0;
                int y = (i >= 1) ? table[i - 1][j] : 0;
                table[i][j] = x + y;
            }
        }
        return table[m][n];
    }

    /*
    DP solution
    Time Complexity: O(mn), space: O(n)
     */
    public static int countBest(int[] S, int n) {
        int m = S.length;
        int[] table = new int[n+1];

        //base case
        table[0] = 1;

        for(int i = 0; i < m; i++) {
            for(int j = S[i]; j <= n; j++) {
                table[j] = table[j] + table[j - S[i]];
            }
        }

        return table[n];
    }



    public static void main(String[] args) {
        int[] S = {1,2,3};

        System.out.println("Native recursive implementation: " + count(S, 100));
        System.out.println("Dynamic Programming solution: : " + countBetter(S, 100));
        System.out.println("Dynamic Programming best solution: : " + countBest(S, 100));
    }
}
