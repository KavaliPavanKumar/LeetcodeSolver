class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        boolean[] isValid = new boolean[n];
        
        // Precompute isValid array
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            isValid[i] = (colors[i] != colors[next]);
        }
        
        // Count the number of valid groups
        int count = 0;
        int validPairsInWindow = 0;
        
        // Initialize the first window
        for (int i = 0; i < k - 1; i++) {
            if (isValid[i]) {
                validPairsInWindow++;
            }
        }
        
        // Check if the first window is valid
        if (validPairsInWindow == k - 1) {
            count++;
        }
        
        // Slide the window
        for (int i = 1; i < n; i++) {
            // Remove the leftmost pair of the previous window
            if (isValid[i - 1]) {
                validPairsInWindow--;
            }
            
            // Add the new rightmost pair of the current window
            int right = (i + k - 2) % n;
            if (isValid[right]) {
                validPairsInWindow++;
            }
            
            // Check if the current window is valid
            if (validPairsInWindow == k - 1) {
                count++;
            }
        }
        
        return count;
    }
}