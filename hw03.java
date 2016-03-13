import java.io.*;
import java.util.*;

public class Solution {
   
    public static class FastUnionFind {
        public int[] u;
        public FastUnionFind(int n){
            u=new int[n+1];
            for(int i=1;i<=n;i++){
                u[i]=i;
            }
        }
        public int find(int x){
            return u[x];
        }
        public void union(int x,int y){
            u[y]=u[x];
        }
    }

   
    public static class EdgeNode implements Comparable{
        float weight;
        int u;
        int v;
        public EdgeNode(int uu,int vv,float ww){
            u=uu;
            v=vv;
            weight=ww;
        }
        @Override
        public int compareTo(Object x) {
            float xw=((EdgeNode)x).weight;
            if(weight<xw) return -1;
            if(xw==weight) return 0;
            return 1;
        }
    }
   
    public static void kruskal(int n,LinkedList<EdgeNode> E,EdgeNode[] t, ArrayList<ArrayList<Integer>> checkLines){
        FastUnionFind U=new FastUnionFind(n);
        int k=0;
        boolean result = false;
        while(k<n-1){
            EdgeNode x=E.peek();
            int a=U.find(x.u);
            int b=U.find(x.v);
            if(a!=b){
                t[k++]=x;
                U.union(a, b);
            }
            E.pop();
        }
        for (int j = 0; j<checkLines.size();j++) {
            int c1 = checkLines.get(j).get(0);
            int c2 = checkLines.get(j).get(1);
            for(int i=0;i<k;i++){
                int b1 = t[i].u;
                int b2 = t[i].v;
                if ((b1== c1 && b2 == c2) || (b1 == c2 && b2 == c1)) {
                    result = result || true;              
                }
                else {
                    result = result || false;
                }
            }
            if (result) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            result = false;
        }  
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //number of stations
        int n = scan.nextInt();
        
        //number of wires
        int m = scan.nextInt();
        int wires = m;
        
        //number of wires found
        int f = scan.nextInt();
        int foundWire = f;
        
        LinkedList<EdgeNode> E=new LinkedList<EdgeNode>();
        
        for (int i = 0; i < wires; i++) {
            E.add(new EdgeNode(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        
        ArrayList<ArrayList<Integer>> testLines = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < foundWire; i++) {
            testLines.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < foundWire; i++) {
            for (int j = 0; j < 2; j++) {
                testLines.get(i).add(scan.nextInt());
            } 
        }
        
        Collections.sort(E);
        EdgeNode[] t=new EdgeNode[n];
        kruskal(n,E,t, testLines);
    }
}