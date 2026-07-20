class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        
        // Start from the end of the string
        int i = s.length() - 1;
        
        // Step 1: Skip any trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // Step 2: Count the characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }
}