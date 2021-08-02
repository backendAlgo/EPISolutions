package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
    @EpiTest(testDataFile = "parity.tsv")
//    using java method
//    public static short parity(long x) {
//        return (short) (Long.bitCount(x) % 2);
//    }
//    O(N) solution
//    public static short parity(long x) {
//        short result = 0;
//        while (x != 0) {
//            result ^= (x & 1);
//            x >>>= 1;
//        }
//        return result;
//    }
//    O(k) solution by removing last bit. k is number of 1s in word
    public static short parity(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Parity.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
