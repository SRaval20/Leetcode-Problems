// Time Complexity:  O(V*E)
// Space Complexity: O(V+E)

public class Main {

    // Edge class to represent a directed edge in the graph
    static class Edge {
        String src, dest;
        double weight;

        Edge(String src, String dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static double findBestExchangeRate(String start, String end, List<Edge> edges) {
        // Step 1: Create a set of all currencies
        Set<String> currencies = new HashSet<>();
        for (Edge edge : edges) {
            currencies.add(edge.src);
            currencies.add(edge.dest);
        }

        // Step 2: Initialize distances map
        Map<String, Double> distances = new HashMap<>();
        for (String currency : currencies) {
            distances.put(currency, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);

        // Step 3: Apply Bellman-Ford algorithm
        int V = currencies.size(); // Number of currencies
        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : edges) {
                String u = edge.src;
                String v = edge.dest;
                double weight = edge.weight;

                if (distances.get(u) != Double.MAX_VALUE && distances.get(u) + weight < distances.get(v)) {
                    distances.put(v, distances.get(u) + weight);
                }
            }
        }

        // Step 4: If end currency is unreachable, return -1
        if (distances.get(end) == Double.MAX_VALUE) {
            return -1;
        }

        // Step 5: Convert the result back from -log to the actual exchange rate
        return Math.exp(-distances.get(end));
    }

    public static void main(String[] args) {
        // Real-world exchange rates in normal form
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("BTC", "USD", 50000));  // BTC to USD with exchange rate 50000
        edges.add(new Edge("USD", "EUR", 0.9));   // USD to EUR with exchange rate 0.9
        edges.add(new Edge("EUR", "BTC", 0.00002)); // EUR to BTC with exchange rate 0.00002

        // Convert normal exchange rates to graph edges using -log(exchangeRate)
        for (Edge edge : edges) {
            edge.weight = -Math.log(edge.weight);
        }

        String start = "BTC";
        String end = "EUR";

        double bestRate = findBestExchangeRate(start, end, edges);

        if (bestRate == -1) {
            System.out.println("No exchange path found between " + start + " and " + end);
        } else {
            System.out.printf("Best exchange rate from %s to %s: %.6f%n", start, end, bestRate);
        }
    }
}
