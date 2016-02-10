package DynamicProgramming;


/*
The longest Increasing Subsequence (LIS) problem is to find the length
of the longest subsequence of a given sequence such that all elements
of the subsequence are sorted in increasing order. For example, length
of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is
{10, 22, 33, 50, 60, 80}.
 */

public class LongestIncreasingSubsequence {
    /*
    The idea is we can get L(i) as the length of LIS till index i such that
    array[i] is the last element in LIS, then recursively get all L(i).
    The method below is simple recursive implementation of LIS. It just call
    LIS() recursively and not store the result so there are a lot subproblems
    have been solved again and again.
     */
    static int result = 1;
    public static int LIS(int[] array) {
        if(array == null || array.length == 0)
            return 0;

        LIS(array, array.length);

        return result;
    }

    public static int LIS(int[] array, int n) {
        if(array.length == 1)
            return 1;

        //use res to store every previous L(i)
        int res;
        int max = 1;
        for(int i = 1; i < n; i++) {
            res = LIS(array, i);
            if(array[i - 1] < array[n - 1] && res + 1 > max) {
                max = res + 1;
            }
        }

        if(result < max)
            result = max;

        //return length of LIS ending with array[n-1].
        return max;
    }


    /*
    The algo below is not using recursive, just use an array to store
    the L(i) and later each time we use it we can easily access it.
     */
    public static int LISBetter(int[] array) {
        if(array == null || array.length == 0)
            return 0;

        int lis[] = new int[array.length];
        for(int i = 0; i < lis.length; i++)
            lis[i] = 1;

        for(int i = 1; i < array.length; i++) {
            for(int j = 0; j < i; j++) {
                if(array[i] > array[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        //find out the largest value in lis array
        int max = 0;
        for(int i = 0; i < lis.length; i++) {
            if(lis[i] > max)
                max = lis[i];
        }

        return max;
    }


    public static void main(String[] args) {
        int[] array = {10,22,9,33,21,50,41,60};

        System.out.println("Method 1: " + LIS(array));
        System.out.println("Method 1: " + LISBetter(array));
    }
}
