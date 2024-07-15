package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public class Core {
	// Define class variables
	private int numberOfExpandedNodes = 0;
	private double executionTime = 0;
	private List<Node> OpenList;
	private List<Node> ClosedList;
	private List<Node> PathToSolution;

	// Initialize variables on construct
	public Core() {
		this.numberOfExpandedNodes = 0;
		this.OpenList = new ArrayList<Node>();
		this.ClosedList = new ArrayList<Node>();
		this.PathToSolution = new ArrayList<Node>();
	}
	
	// Get number of expanded nodes
	public int getNumberOfExpandedNodes() {
		return this.numberOfExpandedNodes;
	}
	
	// Get size of open list
	public int getOpenListSize() {
		return this.OpenList.size();
	}
	
	// Get size of close list
	public int getCloseListSize() {
		return this.ClosedList.size();
	}
	
	// Get algorithm execution time
	public double getExecutionTime() {
		return this.executionTime;
	}
	
	// Algorithm core
	public List<Node> BreadthFirstSearch(Node root, int[] goalPuzzle) {
		
		// Reset execution time variable
		this.executionTime = 0;
		
		// Start timer
		long start_time = System.nanoTime();
		
		// Check if root puzzle is equal to goal puzzle
		if(root.IsSamePuzzle(goalPuzzle)) {
			System.out.println("Goal found");
				
			// Trace path to root node
			PathTrace(this.PathToSolution, root);
		} else { // If root puzzle isn't the goal puzzle
			
			// goalFound will be used to break while loop
			boolean goalFound = false;
			
			// Push root puzzle to open list
			this.OpenList.add(root);
			
			// Reset number of expanded nodes
			this.numberOfExpandedNodes = 0;
			
			// If there's any puzzle in the open list and the puzzle is not found
			while(this.OpenList.size() > 0 && !goalFound) {
				
				// Set currentNode as first element from open list
				Node currentNode = this.OpenList.get(0);

				// Push currentNode to closed list then remove from open list
				this.ClosedList.add(currentNode);
				this.OpenList.remove(0);
				
				// Expand possible moves from current node
				currentNode.ExpandMove();
				
				// Increment number of expanded nodes
				this.numberOfExpandedNodes += currentNode.children.size();
				
				// For each child of current node - check goal puzzle
				for(int i=0; i < currentNode.children.size(); i++) {
					// Get currentChild from iterator 
					Node currentChild = currentNode.children.get(i);
					
					// If currentChild has not been analyzed yet
					if(!Contains(this.OpenList, currentChild) && !Contains(this.ClosedList, currentChild)) {
						// Check if currentChild is the goal puzzle
						if(currentChild.IsSamePuzzle(goalPuzzle)) {
							System.out.println("Goal found!");
							
							// Set goalFound to true
							goalFound = true;
							
							// Trace path to root node
							PathTrace(this.PathToSolution, currentChild);
						}
						
						// Push currentChild to open list
						this.OpenList.add(currentChild);
					}
				}
				
			}	
			
		}
		
		// Stop timer and set executionTime as milliseconds (ms)
		long end_time = System.nanoTime();
		this.executionTime = (end_time - start_time) / 1e6;
		
		// Return path to solution
		return this.PathToSolution;
	}
	
	// Trace needed path to solution
	public void PathTrace(List<Node> path, Node n) {
		System.out.println("Tracing path...");
		Node current = n;
		path.add(current);
		
		//   Iterates through the parent nodes from goal node (puzzle) until it 
		// finds the node where its parent is equal to null
		while(current.parent != null) {
			current = current.parent;
			
			// Push parent node to path list
			path.add(current);
		}
		
	}
	
	// Check if puzzle is in the list
	public static boolean Contains(List<Node> list, Node c) {
		for(int i=0; i < list.size(); i++) {
			
			if(list.get(i).IsSamePuzzle(c.puzzle)) {
				return true;
			}
		}
		
		return false;
	}
}
