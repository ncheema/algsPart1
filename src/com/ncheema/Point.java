package com.ncheema;

/**
 * Created by navjotcheema on 11/25/14.
 * An immutable data type Point that represents a point in the plane
 */
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.x == this.x && that.y == this.y)   //degenerate line segment
            return Double.NEGATIVE_INFINITY;
        else if (that.x == this.x )   //vertical line segment
            return Double.POSITIVE_INFINITY;
        else if (that.y == this.y)   //horizontal line segment
            return 0.0;
        return (double)(that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y == that.y)
            return this.x - that.x;
        return this.y - that.y;
    }

    private class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            double result = slopeTo(p1) - slopeTo(p2);
            if (result < 0.0) return -1;
            if (result > 0.0) return 1;
            return (int) result;
        }
    }
    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}