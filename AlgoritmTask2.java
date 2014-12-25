package ua.i.pustovalov.taskTwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AlgoritmTask2 {
    public static void main(String[] args) {

    }
}

class User {
    public int srcVert; // Index of first vertex
    public int destVert; // Index end of vertex
    public int distance; // Distance from start to finish

    public User(int sv, int dv, int d) // Constructor
    {
	srcVert = sv;
	destVert = dv;
	distance = d;
    }
}

// The class to market the vertex
class Vertex {
    public String label; // Marker of name
    public boolean isInTree;

    // -------------------------------------------------------------
    public Vertex(String lab) // Constructor
    {
	label = lab;
	isInTree = false;
    }

    // -------------------------------------------------------------
} // End of Vertex

class PriorityQ {
    // The array is sorted by descending from cell 0 to size-1
    private final int SIZE = 20;
    private User[] queArray;
    private int size;

    // -------------------------------------------------------------
    public PriorityQ() // Constructor
    {
	queArray = new User[SIZE];
	size = 0;
    }

    public void insert(User item) // Insert element in the sort order
    {
	int j;
	for (j = 0; j < size; j++)
	    // Search for a place to insert
	    if (item.distance >= queArray[j].distance)
		break;
	for (int k = size - 1; k >= j; k--)
	    // Move items up
	    queArray[k + 1] = queArray[k];
	queArray[j] = item; // Insert element
	size++;
    }

    public User removeMin() // Retrieve the smallest element
    {
	return queArray[--size];
    }

    public void removeN(int n) // Remove the element in position N
    {
	for (int j = n; j < size - 1; j++)
	    // Move items down
	    queArray[j] = queArray[j + 1];
	size--;
    }

    public User peekMin() // Read the smallest element
    {
	return queArray[size - 1];
    }

    public int size() // Get the number of elements
    {
	return size;
    }

    public boolean isEmpty() // True, if the queue is empty
    {
	return (size == 0);
    }

    public User peekN(int n) // Read item in the position N
    {
	return queArray[n];
    }

    public int find(int findDex) // Search for an item with a given
    {// Value destVert
	for (int j = 0; j < size; j++)
	    if (queArray[j].destVert == findDex)
		return j;
	return -1;
    }
}
