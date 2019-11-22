package io.jayden.thinkdast;

public class SelectionSort {

    public static void sort(int[] array) {
        for(int i = 0; i <array.length; i++) {
            int j = indexLowest(array, i);
            swapElements(array, i, j);
        }
    }

    private static void swapElements(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int indexLowest(int[] array, int startIndex) {
        int lowestIndex = startIndex;
        for (int i = startIndex; i < array.length; i++) {
            if(array[i] < array[lowestIndex]) {
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

}
