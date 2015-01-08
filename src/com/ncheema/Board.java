package com.ncheema;

import java.util.Stack;

/**
 * Created by navjotcheema on 1/5/15.
 */
public class Board {
    private  int [] [] blocks;
    private int size;

    /**
     * construct a blocks from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
     */
    public Board(int[][] blocks)  {
        this.blocks = new int [blocks.length][blocks.length];
        for (int row = 0 ; row < blocks.length; row++) {
            System.arraycopy(blocks[row],0,this.blocks[row],0,blocks.length);
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
                if (blocks[row][col] != (row * blocks.length)+col+1)
                    counter++;
        }
        return  counter;

    }

    /**
     * Calculate Manhattan distances between blocks and goal
     * @return sum of Manhattan distances
     */
    public int manhattan() {
        int total = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                int currentBlock = blocks[row][col];
                if (currentBlock != 0 && currentBlock != (row * blocks.length) + 1 ){
                    int rowShift = (currentBlock-1) / blocks.length;
                    int colShift = (currentBlock-1) % blocks.length;
                    total += rowShift+colShift;
                }
            }
        }
        return total;
    }

    /**
     * is this blocks the goal blocks?
     * @return true if blocks are valid, else return false
     */
    public boolean isGoal() {
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++)
                if (blocks[row][col] != 0 && blocks[row][col] != (row * blocks.length)+1)
                    return false;
        }
        return  true;
    }

    /**
     * a board that is obtained by exchanging two adjacent blocks in the same row
     * @return Board by exchanging any two adjacent blocks
     */
    public Board twin()      {
        int [] [] tempBlocks = new int[blocks.length][blocks.length];
        for (int row = 0 ; row < blocks.length; row++) {
            for(int col = 0; col < blocks.length; col++)
                tempBlocks[row][col] = this.blocks[row][col];
        }
        //find valid row, if the first row is not valid,
        //the we know the second row is valid since there is only one empty block
        int validRow = 0;
        if (tempBlocks[validRow][0] == 0 || tempBlocks[validRow][1] == 0 )
            validRow = 1;
        int tempValue = tempBlocks[validRow][0];
        tempBlocks[validRow][0] = tempBlocks[validRow][1];
        tempBlocks[validRow][1] = tempValue;
        return new Board(tempBlocks);
    }

    /**
     *  does this blocks equal y?
     * @param y the other board
     * @return true if this board is equal to y; false otherwise
     */
    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        for (int row = 0; row < this.blocks.length; row++) {
            for (int col = 0 ; col < this.blocks.length; col++) {
                if (this.blocks[row][col] != that.blocks[row][col])
                    return false;
            }
        }
        return true;
    }

    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<Board>();

        //first find the empty block index
        int indexRowEmptyBlock = -1;
        int indexColEmptyBlock = -1;
        int [] [] tempBlocks = new int [this.blocks.length][this.blocks.length];
        boolean foundEmptyBlock = false;
        for (int row = 0; row < this.blocks.length && !foundEmptyBlock; row++) {
            for (int col = 0; col < this.blocks.length; col++) {
                if (this.blocks[row][col] == 0) {
                    indexRowEmptyBlock = row;
                    indexColEmptyBlock = col;
                    foundEmptyBlock = true;
                    break;
                }

            }
        }
        //check for four cases and queue them if valid
        // check if the block above the empty block is valid
        if (indexRowEmptyBlock-1 >=0) {
            for (int row = 0 ; row < blocks.length; row++) {
                for (int col = 0; col < blocks.length; col++ )
                    tempBlocks[row][col] = this.blocks[row][col];
            }
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock] = tempBlocks[indexRowEmptyBlock-1][indexColEmptyBlock];
            tempBlocks[indexRowEmptyBlock-1][indexColEmptyBlock] = 0;
            stack.add(new Board(tempBlocks));
        }

        //check if the block below is valid
        if (indexRowEmptyBlock+1 < this.blocks.length) {
            for (int row = 0 ; row < blocks.length; row++) {
                for (int col = 0; col < blocks.length; col++ )
                    tempBlocks[row][col] = this.blocks[row][col];
            }
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock] = tempBlocks[indexRowEmptyBlock+1][indexColEmptyBlock];
            tempBlocks[indexRowEmptyBlock+1][indexColEmptyBlock] = 0;
            stack.add(new Board(tempBlocks));
        }

        //check if the left block is valid
        if (indexColEmptyBlock-1 >= 0) {
            for (int row = 0 ; row < blocks.length; row++) {
                for (int col = 0; col < blocks.length; col++ )
                    tempBlocks[row][col] = this.blocks[row][col];
            }
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock] = tempBlocks[indexRowEmptyBlock][indexColEmptyBlock-1];
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock-1] = 0;
            stack.add(new Board(tempBlocks));
        }
        //check if the right block is valid
        if (indexColEmptyBlock+1 < this.blocks.length) {
            for (int row = 0 ; row < blocks.length; row++) {
                for (int col = 0; col < blocks.length; col++ )
                    tempBlocks[row][col] = this.blocks[row][col];
            }
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock] = tempBlocks[indexRowEmptyBlock][indexColEmptyBlock+1];
            tempBlocks[indexRowEmptyBlock][indexColEmptyBlock+1] = 0;
            stack.add(new Board(tempBlocks));
        }
        return stack;
    }

    /**
     * string representation of this blocks (in the output format specified below)
     * @return String representsation of blocks in order
     */
    public String toString() {
        //using StringBuilder instead of  + concatention, since StringBuilder.append is faster
        StringBuilder sBuilder = new StringBuilder();
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                sBuilder.append(blocks[row][col]);
                sBuilder.append(" ");
            }
            sBuilder.append("\n");
        }
        return sBuilder.toString();
    }



    // unit tests (not graded)
    public static void main(String[] args) {
        int [][] blocks = {{1,2,0}, {4,5,6}, {7,8,3} };
        Board b = new Board(blocks);
        StdOut.print(b);
        StdOut.println("Dimensions: " + b.dimension());
        StdOut.println("Hamming Score: "+b.hamming());

    }
}
