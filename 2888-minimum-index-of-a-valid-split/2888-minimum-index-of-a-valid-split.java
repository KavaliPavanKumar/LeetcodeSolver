class Solution {
    public int minimumIndex(List<Integer> nums) {
        // Step 1: Find the dominant element in nums
        int dominant = findDominant(nums);
        int totalCount = 0;
        for (int num : nums) {
            if (num == dominant) {
                totalCount++;
            }
        }
        
        int leftCount = 0;
        int n = nums.size();
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == dominant) {
                leftCount++;
            }
            int leftSize = i + 1;
            int rightSize = n - leftSize;
            int rightCount = totalCount - leftCount;
            
            if (leftCount * 2 > leftSize && rightCount * 2 > rightSize) {
                return i;
            }
        }
        return -1;
    }
    
    private int findDominant(List<Integer> nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int dominant = -1;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                dominant = entry.getKey();
            }
        }
        return dominant;
    }
}