package com.service;

import java.util.Arrays;

public class Utils {
    static double getMedian(int[] values) {
        // sort array
        Arrays.sort(values);
        double median;
        // get count of scores
        int totalElements = values.length;
        // check if total number of scores is even
        if (totalElements % 2 == 0) {
            int sumOfMiddleElements = values[totalElements / 2] +
                    values[totalElements / 2 - 1];
            // calculate average of middle elements
            median = ((double) sumOfMiddleElements) / 2;
        } else {
            // get the middle element
            median = (double) values[values.length / 2];
        }
        return median;
    }

    static int getLargestNum(int[] arr){
        if (arr.length ==0){
            return -1;
        }
        int max = arr[0];
        for (int temp: arr){
            if (temp > max) {max = temp;}
        }
        return max;
    }
}
