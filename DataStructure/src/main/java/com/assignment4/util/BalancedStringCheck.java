package com.assignment4.util;

import java.util.Stack;

public class BalancedStringCheck {

    /** Function to check if the input string has balanced braces
     * @param input string to verify
     * @return boolean based on input check if the string is balanced
     *            Time Complexity  Space Complexity
     * Best case  O(1)              O(1)      //if the input string is already unbalanced example ")"
     * Worst Case O(n)              O(n)
     */

    public static boolean isBalanced(String input) throws Exception {
        //if String is not empty or blank
        if(!input.isBlank()) {
            // Stack to keep track of opening braces
            Stack<Character> stack = new Stack<>();

            // Traverse each character in the input string
            for (char ch : input.toCharArray()) {
                // If the character is an opening brace, push it to the stack
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                }
                // If the character is a closing brace, check the top of the stack
                else if (ch == ')' || ch == '}' || ch == ']') {
                    // If the stack is empty or the top of the stack doesn't match the closing brace, it's unbalanced
                    if (stack.isEmpty()) {
                        return false; // Unbalanced due to unmatched closing brace
                    }

                    // Pop the top opening brace from the stack
                    char top = stack.pop();
                    // Check if the popped opening brace matches the closing brace
                    if (!isMatchingPair(top, ch)) {
                        return false; // Unbalanced due to mismatched braces
                    }
                }
            }
            // After processing, if the stack is not empty, there are unmatched opening braces
            return stack.isEmpty(); // Return true if stack is empty (balanced), false if not
        }else{//throw exeption if string is empty
            throw new Exception("String is Empty");
        }
    }
    // Helper function to check if the pair of opening and closing braces match
    private static boolean isMatchingPair(char open, char close) {
        // Return true if the opening and closing braces match correctly
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
}
