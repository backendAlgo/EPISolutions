package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class NumberOfScoreCombinations {
    @EpiTest(testDataFile = "number_of_score_combinations.tsv")

    public static int
    numCombinationsForFinalScore(int finalScore,
                                 List<Integer> individualPlayScores) {
        int n = individualPlayScores.size();
        int[][] dp = new int[n + 1][finalScore + 1];


        for (int i = 1; i <= n; i++) {
            int curr = individualPlayScores.get(i - 1);
            dp[i][0] = 1;

            for (int score = 1; score <= finalScore; score++) {
                if (score - curr >= 0) {
                    dp[i][score] += dp[i][score - curr];
                }
                dp[i][score] += dp[i - 1][score];
            }
        }
        return dp[n][finalScore];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
