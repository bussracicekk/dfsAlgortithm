import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Graph {
    //int vertex;
    
    private static int[][] M;
    private static boolean [] visited;
   // LinkedList<Integer> list[];
    ArrayList<Integer> makerList = new ArrayList<>(); 
    ArrayList<Integer> breakerList = new ArrayList<>(); 
    Set<Integer> hs = new HashSet<>();
    Set<Integer> hs1 = new HashSet<>();
    

    public Graph(int vertex) {
        
        M = new int[vertex][vertex];
        visited = new boolean[M.length];
        
        /*list = new LinkedList[vertex];
        for (int i = 0; i <vertex ; i++) {
            list[i] = new LinkedList<>();
        }*/
    }

    public void addEdge(int source, int destination, int w){

        //add forward edge
        //list[source].addFirst(destination);
       // list[destination].addFirst(source);
        
        this.M[source][destination] = w;
        this.M[destination][source] = w;
        
        
        if(w==1) {
            // Add v to u's list. 
        		 
            makerList.add(destination);
           	hs.addAll(makerList);
           	makerList.clear();
           	makerList.addAll(hs);
        }
        if(w==(-1)) {
        	breakerList.add(destination);
        	hs1.addAll(breakerList);
        	breakerList.clear();
        	breakerList.addAll(hs1);
        }
    }
    
    public void removeEdge(int source, int destination){         // remove the edge from u to v and the (duplicate) edge from v to u
       /* list[source].remove(destination);
        list[destination].remove(source);*/
        
    	this.M[source][destination] = 0;
        this.M[destination][source] = 0;
    }
    
    public int getEdge(int source, int destination){

        return M[source][destination];

    }
    
    public boolean isEdge(int source, int destination){            // return true or false depending on whether there is an edge from u to v

        if(M[source][destination] != 0){
        	
            return true;

        }else{

            return false;

        }

    }

    public void DFS(){
        //System.out.print("Depth First Traversal: ");
        //boolean[] visited = new boolean[vertex];
        Stack<Integer> stack = new Stack<Integer>();

        for(int startIndex=0; startIndex<M.length; startIndex++){
            if(visited[startIndex]==false) {
                stack.push(startIndex);
                visited[startIndex] = true;
                while (stack.isEmpty() == false) {
                    int nodeIndex = stack.pop();
                    System.out.print(nodeIndex + " ");
                    //LinkedList<Integer> nodeList = list[nodeIndex];
                    for (int i = 0; i < M.length; i++) {
                       // int dest = nodeList.get(i);
                        if (getEdge(startIndex,i)==1 && !visited[i]) {
                        	stack.push(i);
                        	visited[i] = true;
                        }
                        
                    }
                   
                    
                }
                
            }
            
        }
        
        System.out.println();
    }

   /* public void printGraph(){
        for (int i = 0; i <vertex ; i++) {
            LinkedList<Integer> nodeList = list[i];
            if(nodeList.isEmpty()==false) {
                System.out.print("source = " + i + " is connected to nodes: ");
                for (int j = 0; j < nodeList.size(); j++) {
                    System.out.print(" " + nodeList.get(j));
                }
            }
            System.out.println();
        }
    }*/

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 3,-1);
        graph.addEdge(0, 2,1);
        graph.addEdge(1, 2,-1);
        graph.addEdge(3, 1,1);
        graph.addEdge(2, 5,1);
        graph.addEdge(2, 0,1);
        graph.addEdge(4, 3,1);
        graph.addEdge(4, 1,-1);
        graph.addEdge(5, 4, 1);
        //graph.printGraph();
        if(graph.makerList.size()==6) {
        	
        	graph.DFS();
        	System.out.println("maker wins");
        }
        else {
        	System.out.println("Breaker wins");
        }
    }
}
