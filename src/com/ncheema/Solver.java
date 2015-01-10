package com.ncheema;

import java.util.Comparator;

/**
 * Created by navjotcheema on 1/5/15.
 */
public class Solver {
    MinPQ<Board> minPQ;
    Comparator<Node> boardComparator = new BoardComparator();

    private class BoardComparator implements Comparator<Node> {
        
    }

    private class Node {
        Board board;
        Node parent;

        //constructor for root
        public Node(Board b) {
            this.board = b;
            this.parent = null; //root node
        }
        //constructor for child nodes
        public Node(Board b, Node parent) {
            this.board = b;
            this.parent = parent;
        }


    }

    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial)   {
        minPQ = new MinPQ<Board>();
        minPQ.insert(initial);  //add the initial board

        minPQ.insert(initial.neighbors());

    }

    /**
     * is the initial board solvable?
     * @return true if board is solvable, false otherwise
     */
    public boolean isSolvable(){

    }

    /**
     *
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (isSolvable()) {

        }
        return -1;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args) {} // solve a slider puzzle (given below)

}