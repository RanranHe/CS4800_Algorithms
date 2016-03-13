// username: heranran
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static int count_silly;
    static int count_bubble;
    static int count_merge;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array_silly = new int[n];
        int[] array_bubble = new int[n];
        int[] array_merge = new int[n];
        for (int i = 0; i<n; i++) {
            int num = scan.nextInt();
            array_silly[i] = num;
            array_bubble[i] = num;
            array_merge[i] = num;
        }
        
        if (n>9) {
            System.out.println("-1");
        }
        else {
            Solution.silly_sort(array_silly);
            System.out.println(count_silly);
        }
        if (n>Math.pow(10, 5)) {
            System.out.println("-1");
        } else {
            Solution.bubble_sort(array_bubble);
            System.out.println(count_bubble);
        }
        Solution.merge_sort(array_merge);
        System.out.println(count_merge);
    }
    
    public static int[] silly_sort(int[] array) {
        int len = array.length;
        int[] maybe_sorted = new int[len];
        
        if (len<2) {
            return array;
        }
        else {
            for (int i = 0; i<len; i++) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                maybe_sorted[0] = array[0];
                int[] anArray = silly_sort(Arrays.copyOfRange(array, 1, len));
                for (int j = 1; j<len; j++) {
                    maybe_sorted[j] = anArray[j-1];
                }
                
                int n = 0;
                for (int m = 0; m<len-1; m++) {
                    count_silly++;
                    if (maybe_sorted[m] > maybe_sorted[m+1]) {
                        n = 1;
                        break;
                    }
                }
                if (n == 0) {
                    return maybe_sorted;
                }
                else {
                    temp = array[0];
                    array[0] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    static int[] bubble_sort(int[] array) {
        int len = array.length;
        while(true) {
            boolean swapped_this_turn = false;
            for (int i = 0; i<len-1; i++) {
                count_bubble++;
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    swapped_this_turn = true;
                }
            }
            if (!swapped_this_turn) {
                return array;
            }
        }
    }
    
    static int[] merge_sort(int[] array) {
        int len = array.length;
        if (len<2) {
            return array;
        }
        int mi = len/2;
        int[] left = Arrays.copyOfRange(array, 0, mi);
        int[] right = Arrays.copyOfRange(array, mi, len);
        left = merge_sort(left);
        right = merge_sort(right);
        int i = 0;
        int j = 0;
        int k = 0;
        
        while(i<left.length && j<right.length) {
            count_merge++;
            if (left[i]<right[j]) {
                array[k] = left[i];
                k++;
                i++;
            } else {
                array[k] = right[j];
                k++;
                j++;
            }
        }
        if (i<left.length) {
            for(;k<len; k++) {
                array[k] = left[i];
                i++;
            }
        } else {
            for (int a = k; a<len; a++) {
                array[a] = right[j];
                j++;
            }
        }
        return array;
    }
}