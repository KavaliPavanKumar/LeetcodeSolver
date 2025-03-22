import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Step 1: Build the adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // Step 2: Find all connected components
        boolean[] visited = new boolean[n];
        int completeCount = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adjList, visited, component);
                // Step 3: Check if the component is complete
                if (isComplete(component, adjList)) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }

    private void dfs(int node, List<List<Integer>> adjList, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjList, visited, component);
            }
        }
    }

    private boolean isComplete(List<Integer> component, List<List<Integer>> adjList) {
        int size = component.size();
        // A complete graph with `size` vertices should have `size * (size - 1) / 2` edges
        int expectedEdges = size * (size - 1) / 2;
        int actualEdges = 0;
        for (int node : component) {
            actualEdges += adjList.get(node).size();
        }
        // Since each edge is counted twice in the adjacency list, we divide by 2
        actualEdges /= 2;
        return actualEdges == expectedEdges;
    }
}