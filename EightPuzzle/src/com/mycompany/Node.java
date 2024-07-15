package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public List<Node> children = new ArrayList<Node>();
	public Node parent = null;
	public int[] puzzle = new int[9];
	public int x = 0;
	public int col = 3;
	
	public Node(int[] p) {
		SetPuzzle(p);
	}
	
	public void SetPuzzle(int[] p) {
		for(int i=0; i < this.puzzle.length; i++) {
			this.puzzle[i] = p[i];
			
			if(this.puzzle[i] == 0) {
				this.x = i;
			}
		}
	}
	
	public void ExpandMove() {		
		MoveToRight(puzzle, this.x);
		MoveToLeft(puzzle, this.x);
		MoveToUp(puzzle, this.x);
		MoveToDown(puzzle, this.x);
	}
	
	public void MoveToRight(int[] p, int i) {
		if(i % this.col < this.col - 1) {
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			int temp = hypotheticalPuzzle[i + 1];
			hypotheticalPuzzle[i + 1] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			Node child = new Node(hypotheticalPuzzle);
			children.add(child);
			child.parent = this;
		}
	}
	
	public void MoveToLeft(int[] p, int i) {
		if(i % this.col > 0) {
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			int temp = hypotheticalPuzzle[i - 1];
			hypotheticalPuzzle[i - 1] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			Node child = new Node(hypotheticalPuzzle);
			children.add(child);
			child.parent = this;
		}
	}
	
	public void MoveToUp(int[] p, int i) {
		if(i - this.col >= 0) {
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			int temp = hypotheticalPuzzle[i - 3];
			hypotheticalPuzzle[i - 3] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			Node child = new Node(hypotheticalPuzzle);
			children.add(child);
			child.parent = this;
		}
	}
	
	public void MoveToDown(int[] p, int i) {
		if(i + this.col < this.puzzle.length) {
			int[] hypotheticalPuzzle = new int[9];
			CopyPuzzle(hypotheticalPuzzle, p);
			
			int temp = hypotheticalPuzzle[i + 3];
			hypotheticalPuzzle[i + 3] = hypotheticalPuzzle[i];
			hypotheticalPuzzle[i] = temp;
			
			Node child = new Node(hypotheticalPuzzle);
			children.add(child);
			child.parent = this;
		}
	}
	
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
	
	public boolean IsSamePuzzle(int[] p) {

		for(int i=0; i < p.length; i++) {
			if(this.puzzle[i] != p[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public void CopyPuzzle(int[] a, int[] b) {
		for(int i = 0; i < b.length; i++) {
			a[i] = b[i];
		}
	}
}
