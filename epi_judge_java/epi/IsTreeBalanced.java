package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return util(tree) != Integer.MAX_VALUE;
    }

    //if left not complete but right is complete than true
    //if left complete than right complete than true;
    //if left complete but right not complete true
    // left not and right not false;
    private static int util(BinaryTreeNode<Integer> node) {
        if (node == null)
            return 0;
        int left = util(node.left);
        if (left == Integer.MAX_VALUE) return left;
        int right = util(node.right);
        if (right == Integer.MAX_VALUE) return right;
        boolean check = Math.abs(left - right) <= 1;
        return (check) ? Math.max(left, right) + 1 : Integer.MAX_VALUE;
    }

//    private static class Pair {
//        Integer key;
////        Boolean value;
//
//        public Pair(Integer key, Boolean value) {
//            this.key = key;
//            this.value = value;
//        }
//    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
