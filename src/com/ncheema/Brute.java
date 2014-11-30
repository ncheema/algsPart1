package com.ncheema;

/**
 * Created by navjotcheema on 11/25/14.
 * examines 4 points at a time and checks whether they
 * all lie on the same line segment, printing out any such line segments to standard
 * output and drawing them using standard drawing.
 * To check whether the 4 points p, q, r, and s are collinear, check
 * whether the slopes between p and q, between p and r, and between p and s are all equal.
 */
public class Brute {
    public static void main(String[] args) {
        //rescale the cord. system
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        In input = new In(args[0]); //read input file name from std input

        int totalPairArguments = input.readInt();

        Point[] points = new Point[totalPairArguments];
        for (int i = 0; i < totalPairArguments; i++) {
            points[i] = new point(input.readInt(), input.readInt());
        }
        

    }
}
