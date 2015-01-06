package com.ncheema;

/**
 * Created by navjotcheema on 1/5/15.
 */
public class Board {

    /**
     * construct a blocks from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks)  {
        this.blocks = new int [blocks.length][blocks.length];
        for (int row = 0 ; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++ )
                this.blocks[row][col] = blocks[row][col];
        }
    }
    // blocks dimension N
    public int dimension() {
        return blocks.length;

    }

    /**
     * The number of blocks in the wrong position,
     * @return   number(int) of blocks out of place
     */
    public int hamming() {
        int counter = -1;   //to not include the last/empty block
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++)
                if (blocks[row][col] != (row * blocks.length)+1)
                    counter++;
        }
        return  counter;

    }

    /**
     * Calculate Manhattan distances between blocks and goal
     * @return sum of Manhattan distances
     */
    public int manhattan() {
    
    }
    public boolean isGoal() {}               // is this blocks the goal blocks?
    public Board twin()      {}              // a boadr that is obtained by exchanging two adjacent blocks in the same row
    public boolean equals(Object y){}        // does this blocks equal y?
    public Iterable<Board> neighbors() {}     // all neighboring boards
    public String toString() {}               // string representation of this blocks (in the output format specified below)

    private  int [] [] blocks;
    private int size;


    //  public static void main(String[] args) // unit tests (not graded)
}
