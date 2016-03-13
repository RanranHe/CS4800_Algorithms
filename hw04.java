import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // lies
        ArrayList<Integer> k = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            k.add(in.nextInt());
        }

        int m = in.nextInt(); // illusions
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            l.add(in.nextInt());
        }

        ArrayList<Integer> results = new ArrayList<>();
//
//        System.out.println("lies: " + n + k);
//        System.out.println("illusions: " + m + l);
        
        int result = 0;
        for (int i = n + m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (0 <= i - j && i - j < m) {
                    result = result + k.get(n - j - 1) * l.get(m - 1 - i + j);
                }
            }
            results.add(result);
            result = 0;  
        }
        
        if (results.get(0) == 0) {
            results.remove(0);
        }
        
        for (int i = 0; i < results.size() - 1; i++) {
            System.out.print(results.get(i) + " ");
        }
        System.out.println(results.get(results.size() - 1));
    }
}