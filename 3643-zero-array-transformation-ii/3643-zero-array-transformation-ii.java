class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        // Check if the array is already a zero array
        boolean isZeroArray = true;
        for (int num : nums) {
            if (num != 0) {
                isZeroArray = false;
                break;
            }
        }
        if (isZeroArray) {
            return 0;
        }

        // Binary search to find the minimum k
        int left = 0, right = queries.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canBecomeZeroArray(nums, queries, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Check if the array can become a zero array after processing all queries
        if (left <= queries.length && canBecomeZeroArray(nums, queries, left)) {
            return left;
        } else {
            return -1;
        }
    }

    private boolean canBecomeZeroArray(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] decrements = new int[n + 1]; // Difference array to track decrements

        // Apply the first k queries
        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            decrements[l] += val;
            if (r + 1 < n) {
                decrements[r + 1] -= val;
            }
        }

        // Compute the prefix sum to get the total decrements for each element
        int currentDecrement = 0;
        for (int i = 0; i < n; i++) {
            currentDecrement += decrements[i];
            if (nums[i] - currentDecrement > 0) {
                return false;
            }
        }

        return true;
    }
}