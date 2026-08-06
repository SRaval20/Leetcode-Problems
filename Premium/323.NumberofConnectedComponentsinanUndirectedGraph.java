// You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
// Return the number of connected components in the graph.

// Input: n = 5, edges = [[0,1],[1,2],[3,4]]
// Output: 2

// Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
// Output: 1
 

// Constraints:

// 1 <= n <= 2000
// 1 <= edges.length <= 5000
// edges[i] = [ai, bi]
// ai != bi
// There are no repeated edges.

class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge: edges){
            uf.union(edge[0], edge[1]);
        }

        return uf.components;
    }

    class UnionFind{
        int[] rank;
        int[] root;
        int components;
        public UnionFind(int size){
            components = size;
            rank = new int[size];
            root = new int[size];
            for(int i = 0; i<size; i++){
                rank[i] = 1;
                root[i] = i;
            }
        }

        int find(int x){
            if(root[x] ==x){
                return root[x];
            }
            return root[x] = find(root[x]);
        }

        void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX!=rootY){
                if(rank[rootX] > rank[rootY]){
                    root[rootY] = rootX;
                }
                else if(rank[rootY] > rank[rootX]){
                    root[rootX] = rootY;
                }
                else{
                    root[rootY] = rootX;
                    rank[rootX]++;
                }
                components--;
            }
        }
    }
}  
