package ua.i.pustovalov.taskTwo;

public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // List of vertices
    private int adjMat[][]; // The adjacency matrix
    private int nVerts; // Current number of vertices
    private int currentVert;
    private PriorityQ thePQ;
    private int nTree; // The number of vertices in the tree
    static int sum = 0;

    // -------------------------------------------------------------

    public Graph() // Constructor
    {
	vertexList = new Vertex[MAX_VERTS];
	// The adjacency matrix
	adjMat = new int[MAX_VERTS][MAX_VERTS];
	nVerts = 0;
	for (int j = 0; j < MAX_VERTS; j++)
	    // The adjacency matrix
	    for (int k = 0; k < MAX_VERTS; k++)
		// filled with zeros
		adjMat[j][k] = INFINITY;
	thePQ = new PriorityQ();
    }

    // -------------------------------------------------------------
    //add new Vertex
    public void addVertex(String lab) {
	vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    //Add new  Edge
    public void addEdge(int start, int end, int weight) {
	adjMat[start][end] = weight;
	adjMat[end][start] = weight;
    }

    // -------------------------------------------------------------
    public void displayVertex(int v) {
	System.out.print(vertexList[v].label);
    }

    // -------------------------------------------------------------
    public void mstw() // Construction of the minimal spanning tree
    {
	currentVert = 0; // Since cell 0
	while (nTree < nVerts - 1) // While not all vertices included in the
				   // tree
	{// Include currentVert tree
	    vertexList[currentVert].isInTree = true;
	    nTree++;
	    // Insert a priority queue of edges adjacent to currentVert
	    for (int j = 0; j < nVerts; j++) // For each vertex
	    {
		if (j == currentVert) // Skip if the current vertex

		    continue;
		if (vertexList[j].isInTree) // Skip if already in the tree
		    continue;
		int distance = adjMat[currentVert][j];
		if (distance == INFINITY) // Skip if no edges
		    continue;
		putInPQ(j, distance); // Put in the priority queue
	    }
	    if (thePQ.size() == 0) // The queue contains no vertices?
	    {
		System.out.println(" GRAPH NOT CONNECTED");
		return;
	    }
	    // Remove the ribs with a minimum distance from the queue
	    User theEdge = thePQ.removeMin();
	    int sourceVert = theEdge.srcVert;
	    currentVert = theEdge.destVert;
	    // Display the ribs from the initial to the current node
	    System.out.print(vertexList[sourceVert].label + "-");
	    System.out.print(vertexList[currentVert].label);
	    System.out.print(" ");
	    sum = sum + adjMat[sourceVert][currentVert];

	}
	// The minimum spanning tree is constructed
	for (int j = 0; j < nVerts; j++) {
	    // Unmarking vertices
	    vertexList[j].isInTree = false;
	}
	System.out.println(sum);
    }

    // -------------------------------------------------------------
    public void putInPQ(int newVert, int newDist) {
	// Is there another edge with the same final vertex?
	int queueIndex = thePQ.find(newVert); // Get the index
	if (queueIndex != -1) // If there is an edge,
	{ // get it
	    User tempEdge = thePQ.peekN(queueIndex);
	    int oldDist = tempEdge.distance;
	    if (oldDist > newDist) // If the new edge is shorter
	    {
		thePQ.removeN(queueIndex); // Remove the old edge
		User theEdge = new User(currentVert, newVert, newDist);
		thePQ.insert(theEdge); // Вставка нового ребра
	    }
	    // Otherwise, nothing is done; Leave the top of the
	} else // Edges with the same final vertex does not exist
	{ // Insert a new edge
	    User theEdge = new User(currentVert, newVert, newDist);
	    thePQ.insert(theEdge);
	}
    }
    // -------------------------------------------------------------
} // End of class Graph
// //////////////////////////////////////////////////////////////