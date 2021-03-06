import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * class KruskalEdge maintains information about the edge including source and destination
 */
class KruskalEdge implements Comparable<KruskalEdge> {
    int source;
    int destination;
    long weight;

    public KruskalEdge(int source, int destination, long weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(KruskalEdge e) {
        if (weight < e.weight) {
            return -1;
        } else if (weight > e.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class Kruskal {

    Map<String, Integer> vertexIndexMap;

    public Kruskal() {
        vertexIndexMap = new HashMap<>();
    }

    /*
     * utility method to map integer indices to string , here in the graph we
     * use integer source and destination
     */
    public int findvertexIndex(String vertex) {
        if (vertexIndexMap.containsKey(vertex)) {
            return vertexIndexMap.get(vertex);
        }
        vertexIndexMap.put(vertex, vertexIndexMap.size());
        int mapSize = vertexIndexMap.size() - 1;
        return mapSize;

    }

    /*
     * this method gets a key from a map given a value
     */
    public Object getKeyFromValue(Object value) {
        for (Object o : vertexIndexMap.keySet()) {
            if (vertexIndexMap.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    /*
     * Code and algorithm used from data structures book
     *
     */
    public ArrayList<KruskalEdge> kruskalMST(ArrayList<KruskalEdge> edges, int numVertices) {
        DisjSets ds = new DisjSets(numVertices);
        PriorityQueue<KruskalEdge> pq = new PriorityQueue<>(edges);
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        while (mst.size() != numVertices - 1) {
            KruskalEdge e = pq.remove();
            int uset = ds.find(e.source);// union set will take the start vertex
            int vset = ds.find(e.destination);// takes the end vertex
            if (uset != vset) {
                mst.add(e);
                ds.union(uset, vset);

            }
        }
        return mst;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Kruskal k = new Kruskal();
        String filename = args[0];
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<KruskalEdge> edges = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] b = line.split(",");

            for (int i = 1; i < b.length; i += 2) {
                KruskalEdge e = new KruskalEdge(k.findvertexIndex(b[0]), k.findvertexIndex(b[i]), Integer.parseInt(b[i + 1]));
                edges.add(e);
            }
        }
        br.close();
        ArrayList<KruskalEdge> mst = k.kruskalMST(edges, k.vertexIndexMap.size());
        int totalWeight = 0;
        for (int i = 0; i < mst.size(); i++) {
            KruskalEdge e = mst.get(i);
            System.out.println(k.getKeyFromValue(e.source) + " " + k.getKeyFromValue(e.destination) + " " + e.weight);
            totalWeight += e.weight;
        }
        System.out.println(totalWeight);
    }

    /*
     * Disjoint set class from data structures book
     */
    public static class DisjSets {
        /**
         * Construct the disjoint sets object.
         *
         * @param numElements
         *            the initial number of disjoint sets.
         */

        private int[] s;

        public DisjSets(int numElements) {
            s = new int[numElements];
            for (int i = 0; i < s.length; i++)
                s[i] = -1;
        }

        /**
         * Union two disjoint sets using the height heuristic. For simplicity,
         * we assume root1 and root2 are distinct and represent set names.
         *
         * @param root1
         *            the root of set 1.
         * @param root2
         *            the root of set 2.
         */
        public void union(int root1, int root2) {
            if (s[root2] < s[root1]) // root2 is deeper
                s[root1] = root2; // Make root2 new root
            else {
                if (s[root1] == s[root2])
                    s[root1]--; // Update height if same
                s[root2] = root1; // Make root1 new root
            }
        }

        /**
         * Perform a find with path compression. Error checks omitted again for
         * simplicity.
         *
         * @param x
         *            the element being searched for.
         * @return the set containing x.
         */
        public int find(int x) {
            if (s[x] < 0)
                return x;
            else
                return s[x] = find(s[x]);
        }

    }

}
