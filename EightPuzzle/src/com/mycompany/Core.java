package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public class Core {
	private int numberOfExpandedNodes = 0;
	private List<Node> OpenList;
	private List<Node> ClosedList;
	private List<Node> PathToSolution;

	public Core() {
		this.numberOfExpandedNodes = 0;
		this.OpenList = new ArrayList<Node>();
		this.ClosedList = new ArrayList<Node>();
		this.PathToSolution = new ArrayList<Node>();
	}
	
	public int getNumberOfExpandedNodes() {
		return this.numberOfExpandedNodes;
	}
	
	public int getOpenListSize() {
		return this.OpenList.size();
	}
	
	public int getCloseListSize() {
		return this.ClosedList.size();
	}
	
	public List<Node> BreadthFirstSearch(Node root, int[] goalPuzzle) {
		
		if(root.IsSamePuzzle(goalPuzzle)) {
			System.out.println("Goal found");
				
			// trace path to root node
			PathTrace(this.PathToSolution, root);
		} else {
			
			
			this.OpenList.add(root);
			boolean goalFound = false;
			this.numberOfExpandedNodes = 0;
			
			while(this.OpenList.size() > 0 && !goalFound) {
				Node currentNode = this.OpenList.get(0);

				this.ClosedList.add(currentNode);
				this.OpenList.remove(0);
				
				currentNode.ExpandMove();
				
				this.numberOfExpandedNodes += currentNode.children.size();
				
				for(int i=0; i < currentNode.children.size(); i++) {
					Node currentChild = currentNode.children.get(i);
					
					if(!Contains(this.OpenList, currentChild) && !Contains(this.ClosedList, currentChild)) {
						if(currentChild.IsSamePuzzle(goalPuzzle)) {
							System.out.println("Goal found!");
							goalFound = true;
							
							this.OpenList.add(currentChild);
							// trace path to root node
							PathTrace(this.PathToSolution, currentChild);
						}
						
						this.OpenList.add(currentChild);
					}
				}
				
			}	
			
		}
		
		
		return this.PathToSolution;
	}
	
	public void PathTrace(List<Node> path, Node n) {
		System.out.println("Tracing path...");
		Node current = n;
		path.add(current);
		
		while(current.parent != null) {
			current = current.parent;
			path.add(current);
		}
		
	}
	
	public static boolean Contains(List<Node> list, Node c) {
		for(int i=0; i < list.size(); i++) {
			
			if(list.get(i).IsSamePuzzle(c.puzzle)) {
				return true;
			}
		}
		
		return false;
	}
}
