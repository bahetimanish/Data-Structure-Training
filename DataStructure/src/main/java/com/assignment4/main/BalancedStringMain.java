package com.assignment4.main;

import static com.assignment4.util.BalancedStringCheck.isBalanced;

public class BalancedStringMain {

    public static void main(String[] args) throws Exception {
        String test1 = "([(){}({{}})])";
        String test2 = "([()]}}";

        System.out.println("Test 1: " + (isBalanced(test1) ? "Balanced" : "Not Balanced"));
        System.out.println("Test 2: " + (isBalanced(test2) ? "Balanced" : "Not Balanced"));
    }
}
