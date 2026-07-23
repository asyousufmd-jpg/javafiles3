class Solution {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // Check if the character is an uppercase letter ('A' to 'Z')
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                // Converting uppercase to lowercase by adding 32 (or 'a' - 'A')
                chars[i] = (char) (chars[i] + 32);
            }
        }
        return new String(chars);
    }
}