public class findNumbers {
    public int findNumbers(int[] nums) {
        int evenDigitCount = 0;
        
        for (int num : nums) {
            int digitCount = 0;
            int temp = num;
            
            // Non-optimized: Count digits manually by dividing by 10
            while (temp > 0) {
                digitCount++;
                temp = temp / 10;
            }
            
            // Check if the total digit count is even
            if (digitCount % 2 == 0) {
                evenDigitCount++;
            }
        }
        
        return evenDigitCount;
    }
}