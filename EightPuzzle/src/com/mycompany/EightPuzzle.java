package com.mycompany;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EightPuzzle {
	public static void main(String[] args) {
		
		int[] puzzle = {};
//				1, 2, 4,
//				3, 0, 5,
//				7, 6, 8
//		};
		
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
		
		puzzle 		= Arrays.stream(str_puzzle.split(" ")).mapToInt(Integer::parseInt).toArray();
		goalPuzzle  = Arrays.stream(str_goal_puzzle.split(" ")).mapToInt(Integer::parseInt).toArray();
		
		System.out.println("\nRunning...");
		
		Node root = new Node(puzzle);
		Core core = new Core();
		
		long start_time = System.nanoTime();
		
		List<Node> solution = core.BreadthFirstSearch(root, goalPuzzle);
		
		long end_time = System.nanoTime();
		double difference = (end_time - start_time) / 1e6;
		
		System.out.println("\nNumber of states needed to solve (expanded nodes): " + core.getNumberOfExpandedNodes());
		System.out.println("Time to solve (ms): " + difference);
		System.out.println("Solution steps: " + solution.size());
		System.out.println("Open list: " + core.getOpenListSize());
		System.out.println("Closed list: " + core.getCloseListSize());
		
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
