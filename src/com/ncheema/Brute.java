package com.ncheema;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by navjotcheema on 11/25/14.
 * Examines 4 points at a time and checks whether they all lie on the same line segment.
 * Printing out any such line segments to standard output and drawing them using standard
 * drawing. To check whether the 4 points p, q, r, and s are collinear, check whether the
 * slopes between p and q, between p and r, and between p and s are all equal.
 */
public class Brute {
    public static void main(String[] args) {
        setBoard();


        In input = new In(args[0]); //read input file name from std input
        int totalPairArguments = input.readInt();

        Point[] points = new Point[totalPairArguments];
        for (int i = 0; i < totalPairArguments; i++) {
            points[i] = new Point(input.readInt(), input.readInt());
            points[i].draw();   //draw point on the graph
        }

        //natural order sorting; Implemented comparable in Point.java
        Arrays.sort(points);

         // iterate through all 4-tuples and check if the 4 points are collinear
         //draw the line segment; using the first and last point
        for (int p = 0; p < points.length-3; p++) {
            for(int q = p+1; q < points.length-2; q++) {
                double slopePQ = points[p].slopeTo(points[q]);
                    for(int r = q+1; r < points.length-1; r++) {
                        double slopePR = points[p].slopeTo(points[r]);
                        if (slopePR == slopePQ) {
                            for (int s = r+1; s < points.length; s++) {
                                double slopePS = points[p].slopeTo(points[s]);
                                if (slopePS == slopePQ) {
                                    points[p].drawTo(points[s]);    //draw line segment
                                    StdOut.println(points[p] + " -> " + points[q] + " -> " +
                                            points[r] + " -> " + points[s]);
                                }
                            }
                        }
                    }

            }

        }
        // display to screen all at once
        StdDraw.show(0);
        // reset the pen radius
        StdDraw.setPenRadius();
    }
    /**
     *  setup the drawing board by setting xScale
     *  yScale, Pen radius and color
     */
    private static void setBoard() {
        //rescale the cord. system
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        StdDraw.setPenColor(StdDraw.MAGENTA);
    }
}
