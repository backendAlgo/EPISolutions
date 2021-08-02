package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
    @EpiTest(testDataFile = "levenshtein_distance.tsv")

    public static int levenshteinDistance(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[0][j] = dp[0][j - 1] + 1;
                } else if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + 1;
                } else {
                    int min = dp[i - 1][j - 1] + (A.charAt(i - 1) == B.charAt(j - 1) ? 0 : 1);
                    min = Math.min(min, dp[i - 1][j] + 1);
                    min = Math.min(min, dp[i][j - 1] + 1);
                    dp[i][j] = min;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
