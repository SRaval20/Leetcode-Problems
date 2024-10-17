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
