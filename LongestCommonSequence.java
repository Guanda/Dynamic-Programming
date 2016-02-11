package DynamicProgramming;

/*
LCS Problem Statement: Given two sequences, find the length of longest
subsequence present in both of them. A subsequence is a sequence that
appears in the same relative order, but not necessarily contiguous.
For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences
of “abcdefg”. So a string of length n has 2^n different possible subsequences.

 */

public class LongestCommonSequence {
    /*
    A Naive recursive implementation of LCS problem.

    If last characters of both sequences match (or X[m-1] == Y[n-1]) then
    L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])

    If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
    L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2])

    Time Complexity: O(2^n) in worst case when all chars of s1 and s2 are mismatch
     */
    public static int lcs(String s1, String s2) {
        char[] charArr1 = s1.toCharArray();
        char[] charArr2 = s2.toCharArray();

        return lcs(charArr1, charArr2, charArr1.length, charArr2.length);
    }

    public static int lcs(char[] charArr1, char[] charArr2, int m, int n) {
        if(m == 0 || n == 0)
            return 0;

        if(charArr1[m - 1] == charArr2[n - 1])
            return 1 + lcs(charArr1, charArr2, m-1, n-1);
        else
            return Math.max(lcs(charArr1, charArr2, m, n-1), lcs(charArr1, charArr2, m - 1, n));
    }


    /*
    DP solution: Store the progress result to an array
    Time complexity: O(mn)
     */
    public static int lcsBetter(String s1, String s2) {
        char[] charArr1 = s1.toCharArray();
        char[] charArr2 = s2.toCharArray();

        int[][] resultArray = new int[charArr1.length + 1][charArr2.length + 1];

        for(int i = 0; i <= charArr1.length; i++) {
            for(int j = 0; j <= charArr2.length; j++) {
                if(i == 0 || j == 0)
                    resultArray[i][j] = 0;
                else if(charArr1[i - 1] == charArr2[j - 1])
                    resultArray[i][j] = 1 + resultArray[i - 1][j - 1];
                else
                    resultArray[i][j] = Math.max(resultArray[i][j - 1], resultArray[i - 1][j]);
            }
        }
        return resultArray[charArr1.length][charArr2.length];
    }


    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("Native recursive implementation: " + lcs(s1, s2));
        System.out.println("Dynamic Programming solution: " + lcsBetter(s1, s2));
    }
}
