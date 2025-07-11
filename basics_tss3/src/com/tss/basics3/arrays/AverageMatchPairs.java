package com.tss.basics3.arrays;

import java.util.Scanner;

public class AverageMatchPairs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of elements you want to enter in array");
        int size = scanner.nextInt();

        int array[] = new int[size];
        System.out.println("Enter elements");

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        mergeSort(array, 0, array.length - 1);

        System.out.println("Sorted array:");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        System.out.println(hasPair(array));
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static boolean hasPair(int[] array) {
        if (array.length < 2) {
            return false;
        }

        int totalSum = 0;
        for (int i = 0; i < array.length; i++) {
            totalSum += array[i];
        }

        double avg = (double) totalSum / array.length;

        int left = 0, right = array.length - 1;
        while (left < right) {
            double currentSum = array[left] + array[right];

            if (currentSum == avg) {
                return true; 
            } else if (currentSum < avg) {
                left++; 
            } else {
                right--; 
            }
        }

        return false; 
    }
}
