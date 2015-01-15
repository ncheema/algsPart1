package com.ncheema;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by navjotcheema on 1/5/15.
 */
public class Solver {
    MinPQ<Node> minPQ;
    MinPQ<Node> twinMinPQ;
    private Stack<Board> boardStack;
    private Node parent;
    private Node answerNode;
    private boolean isSolvable = false;
    private boolean twinIsSolvable = false;
    //MinPQ requires non netural order of comparison
    Comparator<Node> boardComparator = new BoardComparator();



    private class Node {
        private Board board;
        private Node parent;
        private int priority;
        private int moves;

        /**
         * Sets board, its parent and priority
         * @param b
         * @param parent
         */
        public Node(Board b, Node parent, int moves) {
            this.board = b;
            this.parent = parent;
            this.moves = moves;
            this.priority = this.board.manhattan() + moves;
        }


    }

    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial)   {
        answerNode = null;
        minPQ = new MinPQ<Node>(boardComparator);
        twinMinPQ = new MinPQ<Node>(boardComparator);
        parent = new Node(initial,null,0);
        minPQ.insert(parent);  //add the initial board
        twinMinPQ.insert(new Node(initial.twin(),null,0));
        while (!isSolvable && !twinIsSolvable ) {
            Node currentNode = minPQ.delMin();
            Node twinCurrentNode = twinMinPQ.delMin();
            //check if current board is correct answer
            if (currentNode.board.isGoal()) {
                isSolvable = true;
                answerNode = currentNode;
            }
            //check if twin board is the correct
            else if (twinCurrentNode.board.isGoal()) {
                twinIsSolvable = true;
            }
            //enqueue the minPQ and TwinMinPQ with thier respective neighbors
            else {
                //enqueue minPQ
                for (Board b : currentNode.board.neighbors())
                    minPQ.insert(new Node(b, currentNode, currentNode.moves + 1));
                //enqueue TwinMinPQ
                for (Board b : twinCurrentNode.board.neighbors())
                    twinMinPQ.insert(new Node(b,null,twinCurrentNode.moves + 1));
            }


        }

    }

    /**
     * is the initial board solvable?
     * @return true if board is solvable, false otherwise
     */
    public boolean isSolvable(){
        return isSolvable;
    }

    /**
     *
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (isSolvable()) {
            return answerNode.moves;

        }
        return -1;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        LinkedList<Board> boardList = new LinkedList<Board>();
        Node temp = answerNode;
        while(temp != null){
            boardList.addFirst(temp.board);
            temp = temp.parent;
        }
        return boardList;

    }
    private class BoardComparator implements Comparator<Node> {
        public int compare (Node n1, Node n2) {
            return n1.priority - n2.priority;
        }
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        int [] [] blocks  = {{0,1,3}, {4,2,5}, {7,8,6}};
        int [][] blocks1 = {{8,1,3}, {4,0,2}, {7,6,5}};
        Board b = new Board(blocks);
        Solver solver = new Solver(b);
        StdOut.println(solver.isSolvable() ? "yes" : "no");
        StdOut.println("Minimum moves required: "+solver.moves());
        for (Board sol : solver.solution())
            StdOut.println(sol+"\n");
    }

}