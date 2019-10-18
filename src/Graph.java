import java.util.*; 
  
class Graph 
{ 
    private int V;   // number of all vertices
    static int count;
  
    private static int[][] M;//This array will use for representations of adjacency list
  
    //A linked list is a linear data structure, in which the elements are not stored at contiguous memory locations.
    //a linked list consists of nodes where each node contains a data field and a reference(link) to the next node in the list.
    private LinkedList<Integer> adj[]; 
    
    static ArrayList<Integer> makerList = new ArrayList<>(); //this list will use for keeping vertices that maker has
    ArrayList<Integer> breakerList = new ArrayList<>();//this list will use for keeping vertices that breaker has
    static ArrayList<Integer> dfsList = new ArrayList<>();//this list will use for depth first search algorithm
  
    //HashSet cannot contain duplicate values so we can used the set of the list for deleting duplicate values.
    Set<Integer> hs = new HashSet<>();
    Set<Integer> hs1 = new HashSet<>();
    
    
    Graph(int v) //constructer of Graph.java
    { 
    	M = new int[v][v];
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
    void addEdge(int v,int u, int w) //this function is used to add edge into the graph
    { 
        adj[v].add(u);  // Add w to v's list. 
        
        this.M[v][u] = w;
        
        if(w==1) {
            makerList.add(u);//u is added to the maker list
           	hs.addAll(makerList);//duplicate values are cleared
           	makerList.clear();//all elements of maker list are deleted
           	makerList.addAll(hs);//element of hashset is added to the maker list
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
   
    void DFSUtil(int v,boolean visited[]) 
    { 
        visited[v] = true; // Mark the current node as visited and print it 
        //System.out.print(v+" "); 
  
        // Recur for all the vertices adjacent to this vertex 
        //Iterator is a way to traverse as well as access the data from the collection
        //and access the data element of collection without bothering the user about specific implementation of that collection it.
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
        // Mark all the vertices as not visited(set as false by default in java) 
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
