/**
 * Time Complexity: O(C) 
 * - Where C is the total number of characters across all words in the input array.
 * - Initializing the maps takes O(C) time.
 * - Comparing adjacent words takes O(C) time because each character is examined a constant number of times.
 * - Kahn's algorithm processes each unique character and edge at most once. Since the alphabet size 
 *   is fixed (V <= 26) and edges are bounded (E <= 26^2), the BFS phase runs in O(V + E) = O(1) constant time.
 * 
 * Space Complexity: O(1) or O(U)
 * - Where U is the number of unique characters (at most 26 for English lowercase letters).
 * - The adjacency list and in-degree maps store at most 26 keys.
 * - The queue and string builder store at most 26 elements.
 * - Therefore, extra space does not scale with input size and remains asymptotically constant.
 */

import java.util.*;

public class Solution {
    
    public String alienOrder(String[] words) {
        // Step 1: Initialize the graph structures
        Map<Character, Set<Character>> adjList = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize all unique characters with 0 in-degree
        for (String word : words) {
            for (char c : word.toCharArray()) {
                inDegree.put(c, 0);
                adjList.putIfAbsent(c, new HashSet<>());
            }
        }
        
        // Step 2: Build the directed graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            
            // Edge Case: Check if word2 is a prefix of word1 (invalid sort rule)
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            
            // Find the first differing character to establish an edge
            int minLen = Math.min(w1.length(), w2.length());
            for (int j = 0; j < minLen; j++) {
                char parent = w1.charAt(j);
                char child = w2.charAt(j);
                
                if (parent != child) {
                    // Avoid duplicate edges
                    if (!adjList.get(parent).contains(child)) {
                        adjList.get(parent).add(child);
                        inDegree.put(child, inDegree.get(child) + 1);
                    }
                    break; // Order information found for this pair, exit loop
                }
            }
        }
        
        // Step 3: Add all characters with an in-degree of 0 to the BFS queue
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        // Step 4: Process the queue using Kahn's Algorithm
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            sb.append(current);
            
            for (char neighbor : adjList.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Step 5: If the string length matches the unique character count, return order
        return sb.length() == inDegree.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Standard valid dictionary
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Test Case 1 Output: " + solution.alienOrder(words1)); 
        // Expected Output: "wertf"

        // Test Case 2: Invalid order due to cycle (z -> x -> z)
        String[] words2 = {"z", "x", "z"};
        System.out.println("Test Case 2 Output: " + solution.alienOrder(words2)); 
        // Expected Output: ""

        // Test Case 3: Invalid order due to prefix rule
        String[] words3 = {"abc", "ab"};
        System.out.println("Test Case 3 Output: " + solution.alienOrder(words3)); 
        // Expected Output: ""
    }
}
