class Solution {
    public int mostWordsFound(String[] sentences) {
        int maxWords = 0;
        
        for (String sentence : sentences) {
            // Since there are no leading/trailing spaces and words are separated by a single space,
            // the number of words is exactly equal to (number of spaces + 1).
            String[] words = sentence.split(" ");
            if (words.length > maxWords) {
                maxWords = words.length;
            }
        }
        
        return maxWords;
    }
}