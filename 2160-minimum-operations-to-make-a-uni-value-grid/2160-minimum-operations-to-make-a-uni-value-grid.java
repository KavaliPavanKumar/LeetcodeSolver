import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> elements = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                elements.add(grid[i][j]);
            }
        }
        
        Collections.sort(elements);
        
        // Check if all elements can reach the same value mod x
        int mod = elements.get(0) % x;
        for (int num : elements) {
            if (num % x != mod) {
                return -1;
            }
        }
        
        int median = elements.get(elements.size() / 2);
        int operations = 0;
        
        for (int num : elements) {
            operations += Math.abs(num - median) / x;
        }
        
        // Check if the list has even length, then the median could be the next one as well
        if (elements.size() % 2 == 0) {
            int median2 = elements.get(elements.size() / 2 - 1);
            int operations2 = 0;
            for (int num : elements) {
                operations2 += Math.abs(num - median2) / x;
            }
            operations = Math.min(operations, operations2);
        }
        
        return operations;
    }
}