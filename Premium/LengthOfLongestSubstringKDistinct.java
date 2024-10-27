/*
386 · Longest Substring with At Most K Distinct Characters
Algorithms
Medium
Accepted Rate
32%


Description
Solution76
Notes
Discuss99+
Leaderboard
Record

Description
Given a string S, find the length of the longest substring T that contains at most k distinct characters.

Only $39.9 for the "Twitter Comment System Project Practice" within a limited time of 7 days!

WeChat Notes Twitter for more information（WeChat ID jiuzhangfeifei）


Example
Example 1:

Input: S = "eceba" and k = 3
Output: 4
Explanation: T = "eceb"
Example 2:

Input: S = "WORLD" and k = 4
Output: 4
Explanation: T = "WORL" or "ORLD"
Challenge
O(n) time
*/


// Time Complexity:  O(n)
// Space Complexity: O(1)

class Solution {
  public static int lengthOfLongestSubstringKDistinct(String s, int k) {
    Map<Character, Integer> freqMap = new HashMap<>();
    int count = 0;
    int maxLen = 0;
    int i=0;
    for(int j=0; j<s.length(); j++) {
      char chJ = s.charAt(j);
      freqMap.put(chJ, freqMap.getOrDefault(chJ, 0) + 1);
      if(freqMap.get(chJ) == 1) 
        count++;

      while(count > k) {
        char chI = s.charAt(i);
        freqMap.put(chI, freqMap.get(chI) - 1);
        if(freqMap.get(chI) == 0)
            count--;
        i++;
      }

      maxLen = Math.max(maxLen, j-i+1);
    }
    return maxLen;
  }
}
