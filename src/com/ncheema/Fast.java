package com.ncheema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by navjotcheema on 11/30/14.
 */
public class Fast {
    public static void main(String[] args) {
        setBoard();


        In input = new In(args[0]); //read input file name from std input
        int numArguments = input.readInt();

        Point[] points = new Point[numArguments];
        for (int i = 0; i < numArguments; i++) {
            points[i] = new Point(input.readInt(), input.readInt());
            points[i].draw();   //draw point on the graph
        }

        //natural order sorting; Implemented comparable in Point.java
        Arrays.sort(points);

        //draw & print the line segment;
        for (int p = 0; p < points.length-1; p++) {
            Arrays.sort(points, points[p].SLOPE_ORDER);
            List<Point> listP = new ArrayList<Point>();
            listP.add(points[p]);   //starting point of line segment
            double slope = points[p].slopeTo(points[p+1]);

            for (int i = p+1; i< points.length; i++) {
                if (slope == points[p].slopeTo(points[i])) {
                    listP.add(points[i]);
                }
                else if (listP.size() <4) {
                    listP.clear();
                    slope = points[p].slopeTo(points[i]);
                }
            }
            if (listP.size() > 3) {
                Collections.sort(listP);
                for (int j = 0; j < listP.size(); j++) {
                    StdOut.print(listP.get(j));
                    if (j + 1 != listP.size())
                        StdOut.print(" -> ");
                }
                StdOut.println();
                listP.get(0).drawTo(listP.get(listP.size() - 1));
                listP.clear();
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


