// Java program to print DFS traversal from a given given graph 
import java.util.*; 
  
// This class represents a directed graph using adjacency list 
// representation 
class Graph 
{ 
    private int V;   // No. of vertices 
    static int count;
  
    private static int[][] M;
    // Array  of lists for Adjacency List Representation 
    private LinkedList<Integer> adj[]; 
    static ArrayList<Integer> makerList = new ArrayList<>(); 
    ArrayList<Integer> breakerList = new ArrayList<>();
    static ArrayList<Integer> dfsList = new ArrayList<>();
    Set<Integer> hs = new HashSet<>();
    Set<Integer> hs1 = new HashSet<>();
    
    // Constructor 
    Graph(int v) 
    { 
    	M = new int[v][v];
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
    //Function to add an edge into the graph 
    void addEdge(int v,int u, int w) 
    { 
        adj[v].add(u);  // Add w to v's list. 
        
        this.M[v][u] = w;
        //this.M[destination][source] = w;
        
        
        if(w==1) {
            // Add v to u's list. 
        		 
            makerList.add(u);
           	hs.addAll(makerList);
           	makerList.clear();
           	makerList.addAll(hs);
        }
        if(w==(-1)) {
        	breakerList.add(u);
        	hs1.addAll(breakerList);
        	breakerList.clear();
        	breakerList.addAll(hs1);
        }
    } 
    int getEdge(int v, int u) {
    	return M[v][u];
    }
  
    // A function used by DFS 
    void DFSUtil(int v,boolean visited[]) 
    { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
        //System.out.print(v+" "); 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> i = adj[v].listIterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visited[n] && getEdge(v,n)==1) {
            	count++;
                DFSUtil(n, visited); }
        } 
    } 
  
    // The function to do DFS traversal. It uses recursive DFSUtil() 
    void DFS(int v) 
    { 
        // Mark all the vertices as not visited(set as 
        // false by default in java) 
        boolean visited[] = new boolean[V]; 
  
        // Call the recursive helper function to print DFS traversal 
        DFSUtil(v, visited); 
    } 
  
    public static void main(String args[]) 
    { 
        Graph g = new Graph(6); 
  
        g.addEdge(0, 1,1); 
        g.addEdge(2, 0,1); 
        g.addEdge(1, 2,1); 
        g.addEdge(2, 0,-1); 
        g.addEdge(2, 3,-1); 
        g.addEdge(3, 4,1); 
        g.addEdge(4, 5,1);
        g.addEdge(5, 3,1);
        g.addEdge(5, 4,-1);
  
        System.out.println("Following is Depth First Traversal "+ 
                           "(starting from vertex 0)"); 
        if(makerList.size()==6) {
        	g.DFS(0);
        	if(count>=5) {
        		System.out.println("Maker wins");
        	}
        	else {
        		System.out.println("Breaker wins");
        	}
        }
        else {
        	System.out.println("Breaker wins");
        }
  
         
    } 
} 