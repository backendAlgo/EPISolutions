package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
    @EpiTest(testDataFile = "substring_match.tsv")

    // Returns the index of the first character of the substring if found, -1
    // otherwise.
    public static int rabinKarpMySolution(String word, String pattern) {
        final int BASE = 31;
        final int MOD = (int) 1e9 + 7;
        int W = word.length();
        int P = pattern.length();
        long[] pPow = new long[Math.max(W, P)];
        pPow[0] = 1;
        for (int i = 1; i < pPow.length; i++) {
            pPow[i] = pPow[i - 1] * BASE % MOD;
        }
        long[] hashes = new long[W + 1];
        for (int i = 0; i < W; i++) {
            hashes[i + 1] = (hashes[i] + (word.charAt(i) - 'A' + 1) * pPow[i]) % MOD;
        }
        long hashToFind = 0;
        for (int i = 0; i < P; i++) {
            hashToFind = (hashToFind + (pattern.charAt(i) - 'A' + 1) * pPow[i]) % MOD;
        }
        for (int i = 0; i + P - 1 < W; i++) {
            long currHash = (hashes[i + P] + MOD - hashes[i]) % MOD;
            if (currHash == (hashToFind * pPow[i]) % MOD)
                return i;
        }
        return -1;
    }

    public static int rabinKarp(String t, String s) {
        if (s.length() > t.length()) {
            return -1; // s is not a substring of t.
        }
        final int BASE = 26;
        int tHash = 0, sHash = 0; // Hash codes for the substring of t and s.
        int powerS = 1; // BASEAlsl.
        for (int i = 0; i < s.length(); i++) {
            powerS = i > 0 ? powerS * BASE : 1;
            tHash = tHash * BASE + t.charAt(i);
            sHash = sHash * BASE + s.charAt(i);
        }
        for (int i = s.length(); i < t.length(); i++) {
// Checks the two substrings are actually equal or not, to protect
// against hash collision.
            if (tHash == sHash && t.startsWith(s, i - s.length())) {
                return i - s.length(); // Found a match.
            }
// Uses rolling hash to compute the new hash code.
            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }
// Tries to match s and t . substring (t.length()
        if (tHash == sHash && t.startsWith(s, t.length() - s.length())) {
            return t.length() - s.length();
        }
        return -1; // s is not a substring of t.
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
