package DynamicProgramming;

/*
Given two strings str1 and str2 and below operations that can performed on str1.
Find minimum number of edits (operations) required to convert ‘str1? into ‘str2?.

Insert
Remove
Replace
All of the above operations are of equal cost.
*/

public class EditDistance {
    /*
    Native recursive implementation:

    m: Length of str1 (first string)
    n: Length of str2 (second string)

    1. If last characters of two strings are same, nothing much to do. Ignore last
       characters and get count for remaining strings. So we recur for lengths m-1 and n-1.

    2. Else (If last characters are not same), we consider all operations on ‘str1?,
       consider all three operations on last character of first string, recursively
       compute minimum cost for all three operations and take minimum of three values.
            a. Insert: Recur for m and n-1
            b. Remove: Recur for m-1 and n
            c. Replace: Recur for m-1 and n-1

    Time complexity: O(3^m) in worst case when the two strings are match.
     */
    public static int editDist(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        return editDist(str1, str2, m, n);
    }

    public static int editDist(String str1, String str2, int m, int n) {
        if(m == 0)
            return n;
        if(n == 0)
            return m;

        //based on the analysis above, do if
        if(str1.charAt(m-1) == str2.charAt(n-1))
            return editDist(str1, str2, m-1, n-1);

        //based on the analysis above, do the else
        return 1+ Math.min(editDist(str1, str2, m, n-1),
                Math.min(editDist(str1, str2, m - 1, n),
                        editDist(str1, str2, m - 1, n - 1)));
    }



    /*
    DP solution
    Time complexity: O(mn)
     */
    public static int editDistBetter(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                //str1 is empty, so insert all str2
                if(i == 0)
                    dp[i][j] = j;
                //str2 is empty, so insert all str1
                else if(j == 0)
                    dp[i][j] = i;
                //last char is same, so ignore last char for both string
                else if(str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                //last char is not same
                else
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";

        System.out.println("Native recursive implementation: " + editDist(str1, str2));
        System.out.println("Dynamic Programming solution: : " + editDistBetter(str1, str2));
    }
}
