// https://leetcode.com/discuss/interview-question/1647092/coinbase-virtual-on-site-interview-questions

// Time Complexity:  O(V*E)
// Space Complexity: O(V+E)

// ************************* Bellman-ford Log method *************************

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








// // ************************* Multiplication overhead *************************

// import java.util.*;

// public class CurrencyExchange {
//     public static void main(String[] args) {
//         // Sample input data (you can replace this with actual input reading from stdin)
//         String[] data = {
//             "USD,EUR,0.85;USD,GBP,0.75;EUR,GBP,0.88;GBP,JPY,150.0"
//         };

//         // Parsing input data
//         List<Exchange> exchanges = new ArrayList<>();
//         for (String d : data[0].split(";")) {
//             String[] parts = d.split(",");
//             String src = parts[0];
//             String des = parts[1];
//             double weight = Double.parseDouble(parts[2]);
//             exchanges.add(new Exchange(src, des, weight));
//         }

//         // Test the function
//         System.out.println(maxAmountofExchange(exchanges, "USD", "JPY"));
//     }

//     // Exchange rate class to hold the data
//     static class Exchange {
//         String src, des;
//         double weight;

//         public Exchange(String src, String des, double weight) {
//             this.src = src;
//             this.des = des;
//             this.weight = weight;
//         }
//     }

//     // Compute the exchange rate
//     public static double maxAmountofExchange(List<Exchange> exchanges, String fromCurrency, String toCurrency) {
//         // Build the graph
//         Map<String, List<Pair>> graph = new HashMap<>();
//         for (Exchange exchange : exchanges) {
//             graph.putIfAbsent(exchange.src, new ArrayList<>());
//             graph.putIfAbsent(exchange.des, new ArrayList<>());
//             graph.get(exchange.src).add(new Pair(exchange.des, exchange.weight));
//             graph.get(exchange.des).add(new Pair(exchange.src, 1.0 / exchange.weight));
//         }

//         // DFS recursive
//         double rate = -1.0;
//         Set<String> visited = new HashSet<>();

//         // DFS Recursive function
//         rate = dfs(graph, fromCurrency, toCurrency, 1.0, visited, rate);
        
//         return rate;
//     }

//     // DFS Recursive function
//     public static double dfs(Map<String, List<Pair>> graph, String src, String des, double exch, Set<String> visited, double rate) {
//         if (!graph.containsKey(src) || !graph.containsKey(des)) {
//             return rate;
//         }
//         if (src.equals(des)) {
//             rate = Math.max(rate, exch);
//             return rate;
//         }
//         visited.add(src);
//         for (Pair neighbor : graph.get(src)) {
//             if (!visited.contains(neighbor.currency)) {
//                 rate = dfs(graph, neighbor.currency, des, exch * neighbor.weight, visited, rate);
//             }
//         }
//         visited.remove(src);
//         return rate;
//     }

//     // Helper class to represent a currency pair
//     static class Pair {
//         String currency;
//         double weight;

//         public Pair(String currency, double weight) {
//             this.currency = currency;
//             this.weight = weight;
//         }
//     }
// }























// public class CurrencyConverter {
//     public static void main(String[] args)throws Exception{
//         /*
//         Test 1: 
//         USD,CAD,1.3;USD,GBP,0.71;USD,JPY,109;GBP,JPY,155
//         USD
//         JPY
        
//         Output: 110.05
        
//         Test 2: 
//         USD,GBP,0.7;USD,JPY,109;GBP,JPY,155;CAD,CNY,5.27;CAD,KRW,921
//         USD
//         CNY
        
//         Output: -1.0
//         */ 
        
//         InputStreamReader reader =  new InputStreamReader(System.in, StandardCharsets.UTF_8);
//         BufferedReader in = new BufferedReader(reader);
        
//         Map<String, Map<String, Double>> graph = new HashMap<>();
//         Set<String> visited = new HashSet<>();
//         Set<Double> results = new HashSet<>();
        
//         String[] input = new String[3];
//         String line;
//         int count = 0;
//         //Reading input from the buffer
//         while((line = in.readLine()) !=null){
//             input[count++] = line;
//         }
        
//         String[] currencies = input[0].split(";");
//         String currencyFrom = input[1];
//         String currencyTo = input[2];
        
//         //Build the graph
//         for(String currency: currencies){
//             String[] splitCurr = currency.split(",");
//             String from = splitCurr[0];
//             String to = splitCurr[1];
//             Double value = Double.parseDouble(splitCurr[2]);
            
//             Map<String, Double> currNeighbors = graph.getOrDefault(from, new HashMap<>());
//             currNeighbors.put(to, value);
//             graph.put(from, currNeighbors);
//         }

//         System.out.println(graph);
//         backtrack(currencyFrom, currencyTo, graph, 1.0, visited, results);
        
//         // Find the maximum conversion rate
//         double maxRate = results.stream().max(Double::compare).orElse(-1.0);
//         System.out.println(maxRate);
//     }
    

//     public static void backtrack(String currentNode, String endNode, Map<String, Map<String, Double>> graph, double temp, Set<String> visited, Set<Double> results) {
//         if (currentNode.equals(endNode)) {
//             results.add(temp);
//             return;
//         }

//         Map<String, Double> neighbors = graph.get(currentNode);
//         if (neighbors != null) {
//             for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
//                 String neighNode = entry.getKey();
//                 double conversionRate = entry.getValue();

//                 if (!visited.contains(neighNode)) {
//                     visited.add(neighNode);
//                     backtrack(neighNode, endNode, graph, temp * conversionRate, visited, results);
//                     visited.remove(neighNode);
//                 }
//             }
//         }
//     }
// }
