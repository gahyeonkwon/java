package com.example.enums;


import javax.swing.*;

import static com.example.enums.DirectionA.EAST;


enum DirectionA { EAST, SOUTH, WEST, NORTH }
public class EnumTest {
    public static void main(String[] args) {
        DirectionA d1 = EAST;
        DirectionA d2 = DirectionA.valueOf("WEST");
        DirectionA d3 = DirectionA.valueOf(DirectionA.class, "EAST");

        System.out.println(" d1 = " + d1);
        System.out.println(" d2 = " + d2);
        System.out.println(" d3 = " + d3);

        System.out.println("d1==d2 ? " + (d1 == d2));
        System.out.println("d1==d3 ? " + (d1 == d3));
        System.out.println("d1.equals(d3) ? " + (d1.equals(d3)));

        System.out.println("d1.compareTo(d3) ? " + (d1.compareTo(d3)));
        System.out.println("d1.compareTo(d2) ? " + (d1.compareTo(d2)));


        switch (d1) {
            case EAST :
                System.out.println("The direction is EAST"); break;
            case SOUTH:
                System.out.println("The direction is SOUTH"); break;
            case WEST:
                System.out.println("The direction is WEST"); break;
            case NORTH:
                System.out.println("The direction is NORTH"); break;
            default:
                System.out.println("invalid direction "); break;
        }

        DirectionA [] dArr = DirectionA.values();

        for(DirectionA d : dArr) {
            System.out.printf("%s=%d%n", d.name(), d.ordinal());
        }


    }
}
