import java.util.*;

public class GraphImplementation implements Graph
{
	int vertices;
	int matrix[][];

	//constructor
	public GraphImplementation(int vertices)
	{
		//returns a graph with vertices
		this.vertices = vertices;
		matrix = new int[vertices][vertices];
	}

	//this function addes a directed edge between src to tar
	public void addEdge(int src, int tar) throws Exception
	{
		//throws exception if src or tar is greater than length of matrix or less than 0
        if (src < 0 || tar < 0 || src >= matrix[0].length || tar >= matrix[0].length) 
        {
            throw new Exception("Out of Bounds");
        }

       	//sets element at src,tar to 1
		matrix[src][tar] = 1;

	}

	public List<Integer> neighbors(int vertex) throws Exception
	{
		//throws exception if vertex is greater than or equal to vertices
		if (vertex >= vertices)
		{
			throw new Exception("Out of Bounds");
		}

		//returns a list of vertex IDs
		List<Integer> neighList = new ArrayList<Integer>();
		for (int i = 0; i < vertices; i++)
		{
			if (matrix[vertex][i] != 0)
			{
				neighList.add(i);
			}
		}
		return neighList;
	}

	//
	public List<Integer> topologicalSort()
	{
		//prints one ordering of vertices 

		//arraylist to store the vertices
		List<Integer> scheduleList = new ArrayList<Integer>();
		int[] sum = new int[vertices];

		for (int i = 0; i < vertices; i++){
			for (int j = 0; j < vertices; j++)
			{
				sum[i] += matrix[j][i];
			}
		}	

		for (int i = 0; i < vertices; i++)
		{
			int next = findZero(sum);
			//prints not possible is findZero(sum)=-1
			if(next == -1)
			{
				System.out.println("It is not possible to order the graph.");
				return scheduleList;
			}
			//adds to scheduleList
			scheduleList.add(next);
			sum[next] = -1;

			for(int j = 0; j < vertices; j++)
			{
				sum[j] -= matrix[next][j];
			}
		}
		return scheduleList;
	}

	//function to find 0 in the arraylist
	private int findZero(int[] result)
	{
		//returns index of element that is 0
		for (int i = 0; i < matrix.length; i++){

			if (result[i] == 0){
				return i;
			} 
		}
		//returns -1 if not there
		return -1;
	}
}