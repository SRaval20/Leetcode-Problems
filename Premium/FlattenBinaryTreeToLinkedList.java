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
