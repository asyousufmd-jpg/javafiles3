public class Solution boolean isNumber {
    public boolean isNumber(String s) {
        // Trim leading and trailing whitespaces if necessary 
        // (LeetCode constraints state s consists only of letters, digits, '.', '+', '-')
        
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExponent = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                seenDigit = true;