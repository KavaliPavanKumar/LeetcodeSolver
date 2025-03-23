import java.util.*;

class Solution {
    public int countPaths(int n, int[][] roads) {
        // Build the graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
            graph.get(road[1]).add(new int[]{road[0], road[2]});
        }

        // Initialize distance and ways arrays
        long[] dist = new long[n]; // Use long to avoid overflow
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        int[] ways = new int[n];
        ways[0] = 1;

        // Priority queue to process nodes in order of increasing distance
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0}); // {node, distance}

        int mod = 1_000_000_007;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];

            // Skip if this is an outdated entry
            if (d > dist[u]) continue;

            // Explore neighbors
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                long time = neighbor[1];

                // If a shorter path is found
                if (dist[u] + time < dist[v]) {
                    dist[v] = dist[u] + time;
                    ways[v] = ways[u]; // Reset the number of ways
                    pq.offer(new long[]{v, dist[v]});
                }
                // If another path with the same distance is found
                else if (dist[u] + time == dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % mod; // Add the number of ways
                }
            }
        }

        return ways[n - 1];
    }
}