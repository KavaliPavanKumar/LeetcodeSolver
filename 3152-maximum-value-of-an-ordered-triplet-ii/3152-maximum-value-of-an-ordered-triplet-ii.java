class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long maxValue = 0;
        
        // Store maxRight[k] for all k > j
        int[] maxRight = new int[n];
        maxRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], nums[i]);
        }

        int maxLeft = nums[0]; // Maximum nums[i] for i < j

        for (int j = 1; j < n - 1; j++) {
            if (maxLeft > nums[j]) { // Ensure (nums[i] - nums[j]) is positive
                maxValue = Math.max(maxValue, (long)(maxLeft - nums[j]) * maxRight[j + 1]);
            }
            maxLeft = Math.max(maxLeft, nums[j]); // Update maxLeft
        }

        return maxValue;
    }
}
