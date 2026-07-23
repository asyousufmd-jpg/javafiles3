class Solution {
    public boolean detectCapitalUse(String word) {
        int count = 0;
        int n = word.length();
        
        // Count the total number of capital letters
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                count++;
            }
        }
        
        // Case 1: All capital letters (e.g., "USA") -> count == n
        // Case 2: No capital letters (e.g., "leetcode") -> count == 0
        // Case 3: Only the first letter is capital (e.g., "Google") -> count == 1 && first letter is uppercase
        if (count == n || count == 0) {
            return true;
        }
        
        return count == 1 && Character.isUpperCase(word.charAt(0));
    }
}