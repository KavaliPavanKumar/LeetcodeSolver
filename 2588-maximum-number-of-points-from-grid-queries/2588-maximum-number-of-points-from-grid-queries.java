

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int k = queries.length;
        int[] answer = new int[k];
        
        // Create an array of queries along with their original indices for sorting
        int[][] queryWithIndex = new int[k][2];
        for (int i = 0; i < k; i++) {
            queryWithIndex[i][0] = queries[i];
            queryWithIndex[i][1] = i;
        }
        
        // Sort the queries based on their values
        Arrays.sort(queryWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        
        int m = grid.length;
        int n = grid[0].length;
        
        // Directions for moving in the grid
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Min-heap to process cells in order of increasing value
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];
        
        // Start with the top-left cell
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        int count = 0;
        
        for (int[] query : queryWithIndex) {
            int q = query[0];
            int index = query[1];
            
            // Process all cells in the heap that are smaller than the current query
            while (!minHeap.isEmpty() && minHeap.peek()[0] < q) {
                int[] cell = minHeap.poll();
                int val = cell[0];
                int i = cell[1];
                int j = cell[2];
                count++;
                
                // Explore adjacent cells
                for (int[] dir : directions) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !visited[newI][newJ]) {
                        visited[newI][newJ] = true;
                        minHeap.offer(new int[]{grid[newI][newJ], newI, newJ});
                    }
                }
            }
            
            answer[index] = count;
        }
        
        return answer;
    }
}