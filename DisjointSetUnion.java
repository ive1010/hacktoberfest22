/*
    created by: nitin23329
    on Date: 01/07/21
*/
package helperFunctionsAndClasses;

public class DisjointSetUnion {

    static class DSU{
        int [] parent,size;
        // initialization takes O(n) time
        public DSU(int n){
            parent = new int[n+1];  // 1-based indexing
            size = new int[n+1];
            for(int i=0;i<=n;i++)
                makeSet(i);
        }
        public void makeSet(int x){
            parent[x] = x;
            size[x] = 1;
        }
        // find() take O(logn) time
        public int find(int x){
            while (x != parent[x])x = parent[x];
            return x;
        }
        // union() takes O(logn) time
        public void union(int x,int y){
            x = find(x);
            y = find(y);
            if(x==y)return;
            if(size[x]>=size[y]) {
                parent[y] = x;
                size[x] += size[y];
            }
            else{
                parent[x] = y;
                size[y] += size[x];
            }
        }
        // isInSameSet() take O(logn)
        public boolean isInSameSet(int x,int y){
            return find(x) == find(y);
        }
        // findSetSize() take O(logn)
        public int findSetSize(int x){
            return size[find(x)];
        }
        // countConnectedComponents() take O(n)
        public int countConnectedComponents(int n){
            int count = 0;
            for(int i=1;i<=n;i++)
                if(parent[i]==i)count++;
            return count;
        }
    }
}
