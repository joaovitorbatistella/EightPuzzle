package com.mycompany;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EightPuzzle {
	public static void main(String[] args) {
		
		// Define an empty array of integers that represent initial puzzle
		int[] puzzle = {};
//				1, 2, 4,
//				3, 0, 5,
//				7, 6, 8
//		};
		
		// Define an empty array of integers that represent goal puzzle
		int[] goalPuzzle = {};
//				1, 2, 3,
//				4, 5, 6,
//				7, 0, 8
//		};
		
		Scanner read = new Scanner(System.in);
		
		System.out.println(
			"Enter a sequence of integer numbers separated by blank space that represent the initial puzzle: "
		);
		
		String str_puzzle = read.nextLine();
		
		System.out.println(
				"\nEnter a sequence of integer numbers separated by blank space that represent the goal puzzle: "
			);
			
		String str_goal_puzzle = read.nextLine();
		
		read.close();
		
		// Splitting input values, mapping, parsing to integer and assignment to arrays
		puzzle 		= Arrays.stream(str_puzzle.split(" ")).mapToInt(Integer::parseInt).toArray();
		goalPuzzle  = Arrays.stream(str_goal_puzzle.split(" ")).mapToInt(Integer::parseInt).toArray();
		
		System.out.println("\nRunning...");
		
		// Instantiate root node using initial puzzle
		Node root = new Node(puzzle);
		
		// Instantiate core that contains BFS algorithm
		Core core = new Core();
				
		// Execute Breadth First Search algorithm to solve puzzle
		List<Node> solution = core.BreadthFirstSearch(root, goalPuzzle);
		
		// Print algorithm performance results
		System.out.println("\nNumber of states needed to solve (expanded nodes): " + core.getNumberOfExpandedNodes());
		System.out.println("Time to solve (ms): " + core.getExecutionTime());
		System.out.println("Solution steps: " + solution.size());
		System.out.println("Open list: " + core.getOpenListSize());
		System.out.println("Closed list: " + core.getCloseListSize());
		
		// Print path to solve
		System.out.println("\nPath:");
		
		solution = solution.reversed();
		
		if(solution.size() > 0) {
			for(int i=0; i < solution.size(); i++) {
				solution.get(i).PrintPuzzle();
			}
		} else {
			System.out.println("No path to Solution");
		}
	}

}
