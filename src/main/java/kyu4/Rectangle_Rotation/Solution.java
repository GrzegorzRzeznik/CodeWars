package kyu4.Rectangle_Rotation;
/*
Task
A rectangle with sides equal to even integers a and b is drawn on the Cartesian plane. Its center (the intersection point of its diagonals) coincides with the point (0, 0), but the sides of the rectangle are not parallel to the axes; instead, they are forming 45 degree angles with the axes.

How many points with integer coordinates are located inside the given rectangle (including on its sides)?

Example
For a = 6 and b = 4, the output should be 23

The following picture illustrates the example, and the 23 points are marked green.
https://files.gitter.im/myjinxin2015/raYf/blob
Input/Output
[input] integer a

A positive even integer.

Constraints: 2 ≤ a ≤ 10000.

[input] integer b

A positive even integer.

Constraints: 2 ≤ b ≤ 10000.

[output] an integer

The number of inner points with integer coordinates.
 */

class Solution {
    static int rectangleRotation(final int a, final int b) {
        double x = (a / Math.sqrt(2)) / 2;
        double y = (b / Math.sqrt(2)) / 2;
        double rect1 = (2 * Math.floor(x) + 1) * (2 * Math.floor(y) + 1);
        double rect2 = (2 * Math.floor(x) + (x % 1 < 0.5 ? 0 : 2)) * (2 * Math.floor(y) + (y % 1 < 0.5 ? 0 : 2));
        return (int)(rect1 + rect2);
    }
}