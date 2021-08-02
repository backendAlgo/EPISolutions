package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        if (tree == null)
            return true;
        return util(tree.left, tree.right);
    }

    private static boolean util(BinaryTreeNode<Integer> left,
                                BinaryTreeNode<Integer> right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null)
            return false;
        if (!left.data.equals(right.data))
            return false;
        boolean leftVal = util(left.left, right.right);
        if (!leftVal)
            return false;
        return util(left.right, right.left);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
