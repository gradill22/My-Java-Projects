package Homework4;

/*
Implement the RegularPolygon class. Write a test program that creates three RegularPolygon objects, created using the
no-arg constructor, using RegularPolygon(6, 4), and using RegularPolygon(10, 4, 5.6, 7.8). For each object, display its
perimeter and area.
*/

public class Q6 {
    public static void main(String[] args) {
        RegularPolygon rp1 = new RegularPolygon();
        System.out.println(rp1);
        RegularPolygon rp2 = new RegularPolygon(6, 4);
        System.out.println(rp2);
        RegularPolygon rp3 = new RegularPolygon(10, 4, 5.6, 7.8);
        System.out.println(rp3);
    }
}