package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Core {
	// Define class variables
	private int numberOfExpandedNodes = 0;
	private double executionTime = 0;
	private List<Node> PathToSolution;

	// Initialize variables on construct
	public Core() {
		this.numberOfExpandedNodes = 0;
		this.PathToSolution = new ArrayList<Node>();
	}
	
	// Get number of expanded nodes
	public int getNumberOfExpandedNodes() {
		return this.numberOfExpandedNodes;
	}
		
	// Get algorithm execution time
	public double getExecutionTime() {
		return this.executionTime;
	}
	
	// Comparator to order nodes by f(n) value
	class NodeComparator implements Comparator<Node> {
	    public int compare(Node n1, Node n2) {
	        return Double.compare(n1.f, n2.f);
	    }
	}

	// Algorithm core
	public List<Node> AStarSearch(Node root, int[] goalPuzzle) {
	    // Reset execution time variable
	    this.executionTime = 0;

	    // Start timer
	    long start_time = System.nanoTime();

	    // Priority Queue to hold nodes to be explored, ordered by f(n)
	    PriorityQueue<Node> openList = new PriorityQueue<>(new NodeComparator());
	    
	    // List to hold explored nodes
	    List<Node> closedList = new ArrayList<>();
	    
	    this.numberOfExpandedNodes = 0;

	    // Set the initial g and h values
	    root.g = 0;
	    root.h = root.CalculateHeuristic(goalPuzzle); // Assumes a method to calculate h(n)
	    root.f = root.g + root.h;

	    // Add root node to open list
	    openList.add(root);

	    // If open list is not empty
	    while (!openList.isEmpty()) {
	        // Get node with the smallest f(n)
	        Node currentNode = openList.poll();
	        
	        // Check if we reached the goal
	        if (currentNode.IsSamePuzzle(goalPuzzle)) {
	            System.out.println("Goal found!");
	            PathTrace(this.PathToSolution, currentNode);
	            break;
	        }

	        // Add current node to closed list
	        closedList.add(currentNode);
	        
	        // Expand possible moves from current node
	        currentNode.ExpandMove();
	        
	        this.numberOfExpandedNodes += currentNode.children.size();

	        // For each child of current node
	        for (Node child : currentNode.children) {
                child.g = currentNode.g + 1;
                child.h = child.CalculateHeuristic(goalPuzzle);
                child.f = child.g + child.h;

                boolean inOpenList = false;
                for (Node openNode : openList) {
                    if (child.IsSamePuzzle(openNode.puzzle)) {
                        inOpenList = true;
                        if (child.g < openNode.g) {
                            openList.remove(openNode);
                            openList.add(child);
                        }
                        break;
                    }
                }

                if (!inOpenList) {
                    boolean inClosedList = false;
                    for (Node closedNode : closedList) {
                        if (child.IsSamePuzzle(closedNode.puzzle)) {
                            inClosedList = true;
                            if (child.g < closedNode.g) {
                                closedList.remove(closedNode);
                                openList.add(child);
                            }
                            break;
                        }
                    }
                    if (!inClosedList) {
                        openList.add(child);
                    }
                }
            }
	    }

	    // Stop timer and set executionTime as milliseconds (ms)
	    long end_time = System.nanoTime();
	    this.executionTime = (end_time - start_time) / 1e6;
	    
	    System.out.println();
	    System.out.println("Open list: " + openList.size());
	    System.out.println("Closed list: " + closedList.size());

	    // Return path to solution
	    return this.PathToSolution;
	}
	
	// Algorithm core
	public List<Node> BreadthFirstSearch(Node root, int[] goalPuzzle) {
		
		List<Node> OpenList = new ArrayList<Node>();
		List<Node> ClosedList = new ArrayList<Node>();
		
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
			OpenList.add(root);
			
			// Reset number of expanded nodes
			this.numberOfExpandedNodes = 0;
			
			// If there's any puzzle in the open list and the puzzle is not found
			while(OpenList.size() > 0 && !goalFound) {
				
				// Set currentNode as first element from open list
				Node currentNode = OpenList.get(0);

				// Push currentNode to closed list then remove from open list
				ClosedList.add(currentNode);
				OpenList.remove(0);
				
				// Expand possible moves from current node
				currentNode.ExpandMove();
				
				// Increment number of expanded nodes
				this.numberOfExpandedNodes += currentNode.children.size();
				
				// For each child of current node - check goal puzzle
				for(int i=0; i < currentNode.children.size(); i++) {
					// Get currentChild from iterator 
					Node currentChild = currentNode.children.get(i);
					
					// If currentChild has not been analyzed yet
					if(!Contains(OpenList, currentChild) && !Contains(ClosedList, currentChild)) {
						// Check if currentChild is the goal puzzle
						if(currentChild.IsSamePuzzle(goalPuzzle)) {
							System.out.println("Goal found!");
							
							// Set goalFound to true
							goalFound = true;
							
							// Trace path to root node
							PathTrace(this.PathToSolution, currentChild);
						}
						
						// Push currentChild to open list
						OpenList.add(currentChild);
					}
				}
				
			}	
			
		}
		
		// Stop timer and set executionTime as milliseconds (ms)
		long end_time = System.nanoTime();
		this.executionTime = (end_time - start_time) / 1e6;
		
		System.out.println();
		System.out.println("Open list: " + OpenList.size());
	    System.out.println("Closed list: " + ClosedList.size());
		
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
