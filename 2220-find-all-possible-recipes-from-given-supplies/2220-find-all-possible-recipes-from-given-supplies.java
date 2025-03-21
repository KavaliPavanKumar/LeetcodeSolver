import java.util.*;

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> available = new HashSet<>(Arrays.asList(supplies));
        
        for (String recipe : recipes) {
            indegree.put(recipe, 0); // Initialize indegree for all recipes
        }
        
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            for (String ingredient : ingredients.get(i)) {
                if (!available.contains(ingredient)) { 
                    graph.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
                    indegree.put(recipe, indegree.getOrDefault(recipe, 0) + 1);
                }
            }
        }
        
        Queue<String> queue = new LinkedList<>();
        for (String recipe : recipes) {
            if (indegree.get(recipe) == 0) {
                queue.offer(recipe);
                available.add(recipe);
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            result.add(recipe);
            
            if (graph.containsKey(recipe)) {
                for (String nextRecipe : graph.get(recipe)) {
                    indegree.put(nextRecipe, indegree.get(nextRecipe) - 1);
                    if (indegree.get(nextRecipe) == 0) {
                        queue.offer(nextRecipe);
                        available.add(nextRecipe);
                    }
                }
            }
        }
        
        return result;
    }
}
