class Solution {
    public long maximumTripletValue(int[] nums) {
        long maxValue = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    long currentValue = (long)(nums[i] - nums[j]) * nums[k];
                    if (currentValue > maxValue) {
                        maxValue = currentValue;
                    }
                }
            }
        }
        return maxValue > 0 ? maxValue : 0;
    }
}