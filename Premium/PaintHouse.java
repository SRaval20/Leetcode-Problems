256. Paint House
Problem

There are n houses in a row.

Each house can be painted with one of 3 colors:

Red
Blue
Green

The cost of painting each house is given by:

costs[i][j]

where

i = house index
j = color
0 = Red
1 = Blue
2 = Green
Constraint

Adjacent houses cannot have the same color.

Return the minimum total cost to paint all houses.

Example
Input:

costs =
[
 [17,2,17],
 [16,16,5],
 [14,3,19]
]

Output:
10

Explanation:

House 0 -> Blue (2)

House 1 -> Green (5)

House 2 -> Blue (3)

Total = 2 + 5 + 3 = 10



// Space Optimized
// 
// TC: O(n)
// SC: O(1)

class Solution {

    public int minCost(int[][] costs) {

        int red = costs[0][0];
        int blue = costs[0][1];
        int green = costs[0][2];

        for (int i = 1; i < costs.length; i++) {

            int newRed =
                    costs[i][0] + Math.min(blue, green);

            int newBlue =
                    costs[i][1] + Math.min(red, green);

            int newGreen =
                    costs[i][2] + Math.min(red, blue);

            red = newRed;
            blue = newBlue;
            green = newGreen;
        }

        return Math.min(red, Math.min(blue, green));
    }
}





// DP Table

// TC: O(n)
// SC: O(n)

class Solution {

    public int minCost(int[][] costs) {

        int n = costs.length;

        int[][] dp = new int[n][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < n; i++) {

            dp[i][0] = costs[i][0] +
                    Math.min(dp[i - 1][1], dp[i - 1][2]);

            dp[i][1] = costs[i][1] +
                    Math.min(dp[i - 1][0], dp[i - 1][2]);

            dp[i][2] = costs[i][2] +
                    Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.min(
                dp[n - 1][0],
                Math.min(dp[n - 1][1], dp[n - 1][2]));
    }
}
