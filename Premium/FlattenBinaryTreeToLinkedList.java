/*
453 · Flatten Binary Tree to Linked List
Algorithms
Easy
Accepted Rate
46%


Description
Solution69
Notes
Discuss99+
Leaderboard
Record

Description
Flatten a binary tree to a fake "linked list" in pre-order traversal.

Here we use the right pointer in TreeNode as the next pointer in ListNode.

Only $39.9 for the "Twitter Comment System Project Practice" within a limited time of 7 days!

WeChat Notes Twitter for more information（WeChat ID jiuzhangfeifei）



Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.

Example
Example 1:

Input:{1,2,5,3,4,#,6}
Output：{1,#,2,#,3,#,4,#,5,#,6}
Explanation：
     1
    / \
   2   5
  / \   \
 3   4   6

1
\
 2
  \
   3
    \
     4
      \
       5
        \
         6
Example 2:

Input:{1}
Output:{1}
Explanation：
         1
         1
Challenge
Do it in-place without any extra memory.
*/


// Time Complexity:  O(n)
// Space Complexity: O(h)

class Solution {
  public void flatten(TreeNode root) {
    dfs(root);
  }
  private TreeNode dfs(TreeNode node) {
    if(node == null)
      return null;
    TreeNode leftTail = dfs(node.left);
    TreeNode rightTail = dfs(node.right);
    if(leftTail != null) {
      leftTail.right = node.right;
      node.right = node.left;
      node.left = null;
    }
    TreeNode last = rightTail!=null ? rightTail : leftTail!=null ? leftTail : node;
    return last;
  }
}
