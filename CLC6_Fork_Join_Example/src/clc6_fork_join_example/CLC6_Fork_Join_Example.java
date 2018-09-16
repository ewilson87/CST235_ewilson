package clc6_fork_join_example;

import java.text.DecimalFormat;
import java.util.Random;

public class CLC6_Fork_Join_Example {

    public static void main(String[] args) {

        //used to round final answer to 2 decimal points
        DecimalFormat df2 = new DecimalFormat(".##");

        //gets an approximation for the percent faster parallel sort is compared to sort.
        double fasterTimePercent = ((sortMethod() / parallelSortMethod()) * 100);

        System.out.println("Parallel Sort method was " + df2.format(fasterTimePercent) + "% faster than sort time due to utilizing fork/join");
    }

    static public double sortMethod() {
        //delcare an int array of 100 million
        int[] intArray = new int[100000000];

        //instantiate a new random
        Random random = new Random();

        //loop for 100 million random numbers
        for (int i = 0; i < 100000000; i++) {
            intArray[i] = random.nextInt();
        }

        double startTime = System.currentTimeMillis();

        java.util.Arrays.sort(intArray);

        double endTime = System.currentTimeMillis();

        System.out.println("Time to run sort: " + (long) (endTime - startTime) + " milliseconds");

        return (endTime - startTime);
    }

    static public double parallelSortMethod() {
        //delcare an int array of 100 million
        int[] intArray = new int[100000000];

        //instantiate a new random
        Random random = new Random();

        //loop for 100 million random numbers
        for (int i = 0; i < 100000000; i++) {
            intArray[i] = random.nextInt();
        }

        double startTime = System.currentTimeMillis();

        java.util.Arrays.parallelSort(intArray);

        double endTime = System.currentTimeMillis();

        System.out.println("Time to run parallel sort: " + (long) (endTime - startTime) + " milliseconds");

        return (endTime - startTime);
    }
}
