package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public class Node {

	// Define class variables
	public List<Node> children = new ArrayList<Node>(); // List of children nodes
	public Node parent = null; 							// Parent node
	public int[] puzzle = new int[9];					// Puzzle represented by array of integers
	public int x = 0;									// Position of zero in puzzle array
	public int col = 3;									// Number of puzzle columns
	public double f;									// Cost of the path from the start to this node
	public double g;									// Heuristic, cost estimate to goal
	public double h;									// Evaluation function f(n) = g(n) + h(n)
	
	// Require integer array to instantiate class
	public Node(int[] p) {
		// Set puzzle in this.puzzle
		SetPuzzle(p);
	}
	
	public void SetPuzzle(int[] p) {
		// Copy position by position to puzzle array
		for(int i=0; i < this.puzzle.length; i++) {
			this.puzzle[i] = p[i];
			
			// When found 0, set position to x variable
			if(this.puzzle[i] == 0) {
				this.x = i;
			}
		}
	}
	
	// Manhattan distance
	 public int CalculateHeuristic(int[] goalPuzzle) {
        int totalDistance = 0;

        // For each position, calculate the Manhattan distance to the target position
        for (int i = 0; i < puzzle.length; i++) {
            int currentVal = puzzle[i];


            if (currentVal == 0) continue;

            // Found target position in goal puzzle
            int goalIndex = 0;
            for (int j = 0; j < goalPuzzle.length; j++) {
                if (goalPuzzle[j] == currentVal) {
                    goalIndex = j;
                    break;
                }
           }

            // Coordinates of current position and target position
            int currentRow = i / 3;
            int currentCol = i % 3;
            int goalRow = goalIndex / 3;
            int goalCol = goalIndex % 3;

            // Calculate distance to Manhattan and accumulate
            totalDistance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
        }

       return totalDistance;
	 }
	
	// Create node children expanding moves
	public void ExpandMove() {		
		// Try to move to all directions passing puzzle and 0 position to each function
		MoveToRight(puzzle, this.x);
		MoveToLeft(puzzle, this.x);
		MoveToUp(puzzle, this.x);
		MoveToDown(puzzle, this.x);
	}
	
	// Try to move 0 to right
	public void MoveToRight(int[] p, int i) {
		// Check if is possible to move to right
		if(i % this.col < this.col - 1) {
			// Define a hypothetical puzzle from the current node
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			//    Swaps values ​​of the position whose value contains zero 
			// with the position immediately to the right
			int temp = hypotheticalPuzzle[i + 1];
			hypotheticalPuzzle[i + 1] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			// Instantiate child puzzle as Node
			Node child = new Node(hypotheticalPuzzle);
			
			// Push child node to children list
			children.add(child);
			
			// Set child parent as current node
			child.parent = this;
		}
	}
	
	// Try to move 0 to left
	public void MoveToLeft(int[] p, int i) {
		// Check if is possible to move to left
		if(i % this.col > 0) {
			// Define a hypothetical puzzle from the current node
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			//    Swaps values ​​of the position whose value contains zero 
			// with the position immediately to the left
			int temp = hypotheticalPuzzle[i - 1];
			hypotheticalPuzzle[i - 1] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			// Instantiate child puzzle as Node
			Node child = new Node(hypotheticalPuzzle);
			
			// Push child node to children list
			children.add(child);
			
			// Set child parent as current node
			child.parent = this;
		}
	}
	
	// Try to move 0 to up
	public void MoveToUp(int[] p, int i) {
		// Check if is possible to move to up
		if(i - this.col >= 0) {
			// Define a hypothetical puzzle from the current node
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			//    Swaps values ​​of the position whose value contains zero 
			// with the position immediately to the up
			int temp = hypotheticalPuzzle[i - 3];
			hypotheticalPuzzle[i - 3] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			// Instantiate child puzzle as Node
			Node child = new Node(hypotheticalPuzzle);
			
			// Push child node to children list
			children.add(child);
			
			// Set child parent as current node
			child.parent = this;
		}
	}
	
	// Try to move 0 to down
	public void MoveToDown(int[] p, int i) {
		// Check if is possible to move to down
		if(i + this.col < this.puzzle.length) {
			// Define a hypothetical puzzle from the current node
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			//    Swaps values ​​of the position whose value contains zero 
			// with the position immediately to the down
			int temp = hypotheticalPuzzle[i + 3];
			hypotheticalPuzzle[i + 3] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			// Instantiate child puzzle as Node
			Node child = new Node(hypotheticalPuzzle);
			
			// Push child node to children list
			children.add(child);
			
			// Set child parent as current node
			child.parent = this;
		}
	}
	
	// Print puzzle by going through each position in the array
	public void PrintPuzzle() {
		System.out.println();
		int m = 0;
		
		for(int i=0; i < this.col; i++) {
			for(int j=0; j < this.col; j++) {
				System.out.print(this.puzzle[m] + " ");
				m++;
			}
			System.out.println();
		}
	}
	
	// Check if puzzle is equal to node puzzle
	public boolean IsSamePuzzle(int[] p) {
		for(int i=0; i < p.length; i++) {
			if(this.puzzle[i] != p[i]) {
				// If found different value for same position
				return false;
			}
		}
		
		return true;
	}
	
	// Copy each array position to the same position in the other array
	public void CopyPuzzle(int[] a, int[] b) {
		for(int i = 0; i < b.length; i++) {
			a[i] = b[i];
		}
	}
}
