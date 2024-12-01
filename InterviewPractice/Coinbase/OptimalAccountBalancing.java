// Time Complexity:  O(2^n)
// Space Complexity: O(n)
//
// where n is unique non-zero debts per person

// **************************************** Backtrack ****************************************

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minTransfers(int[][] transactions) {
        // Step 1: Calculate the net balance for each person
        int[] balance = new int[12];
        for (int[] t : transactions) {
            balance[t[0]] -= t[2];
            balance[t[1]] += t[2];
        }

        // Step 2: Filter out zero balances and collect debts/credits
        List<Integer> debts = new ArrayList<>();
        for (int b : balance) {
            if (b != 0) {
                debts.add(b);
            }
        }

        // Step 3: Backtracking to find the minimum number of transfers
        return settleDebts(debts, 0);
    }

    private int settleDebts(List<Integer> debts, int start) {
        // Skip settled debts
        while (start < debts.size() && debts.get(start) == 0) {
            start++;
        }

        // If all debts are settled
        if (start == debts.size()) {
            return 0;
        }

        int minTransfers = Integer.MAX_VALUE;

        // Try to settle the current debt with others
        for (int i = start + 1; i < debts.size(); i++) {
            if (debts.get(i) * debts.get(start) < 0) {
                // Transfer debt
                debts.set(i, debts.get(i) + debts.get(start));
                minTransfers = Math.min(minTransfers, 1 + settleDebts(debts, start + 1));
                // Backtrack
                debts.set(i, debts.get(i) - debts.get(start));
            }
        }

        return minTransfers;
    }
}
