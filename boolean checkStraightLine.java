public class Solution boolean checkStraightLine  {
    public boolean checkStraightLine(int[][] coordinates) {
        // A line can always be formed by 2 points
        if (coordinates.length <= 2) {
            return true;
        }
        
        // Get the baseline delta X and delta Y from the first two points
        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        // Check if all other points maintain the same ratio/slope
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            
            // Cross-multiplication to avoid division by zero
            if (dy * (x - x0) != dx * (y - y0)) {
                return false;
            }
        }
        
        return true;
    }
}
