class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int i = 0;
        
        // 1. Read and ignore leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        
        // If we reached the end of the string after whitespaces
        if (i == n) {
            return 0;
        }
        
        // 2. Check for signedness
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }
        
        // 3. Read digits and build the number
        // We use a 'double' here to easily hold large values before clamping
        double result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            result = (result * 10) + digit;
            i++;
        }
        
        // Apply the sign
        result = result * sign;
        
        // 4. Handle 32-bit signed integer rounding / clamping
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        return (int) result;
    }
}