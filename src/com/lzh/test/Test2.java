package com.lzh.test;

public class Test2 {

    public static void print(int[] arr, String text) {
        StringBuffer sb = new StringBuffer();
        for (int i : arr) {
            sb.append(i).append(", ");
        }
        System.out.println(text + ": " + sb.substring(0, sb.length() - 2));
    }

    /**
     * 冒泡排序冒泡排序（Bubble Sort）是一种简单的排序算法。<br>
     * 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。<br>
     * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。<br>
     * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。 <br>
     * 步骤：<br>
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。<br>
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。<br>
     * 3、针对所有的元素重复以上的步骤，除了最后一个。<br>
     * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。<br>
     * 
     * @param arr 待排序数组
     * @return 排序后的数组
     */
    public static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        int i, j, temp;
        // 每次扫描需要比较的次数，
        for (i = 0; i < length - 1; i++) {
            for (j = length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] bubbleSort2(int[] arr) {
        int length = arr.length;
        boolean ifChange = true;
        int temp;
        while (ifChange) {
            ifChange = false;
            for (int i = 0; i < length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    ifChange = true;
                }
            }
            length--;
        }
        return arr;
    }
    
    public static int[] sortQuick(int[] arr){
        return quickSort(arr, 0, arr.length-1);
    }

    public static int[] quickSort(int[] array, int left, int right) {
        if (left < right) {
            // 使用子表第一个元素，作为基准
            int pivot = array[left]; //32
            int low = left; //0
            int high = right; //13
            // 循环，直到low = hight
            while (low < high) {
                // 从右边向左边遍历，当遇到比基准数小的时候，结束本次遍历
                while (low < high && array[high] >= pivot) {
                    high--;
                }
                // 
                array[low] = array[high];
                while (low < high && array[low] <= pivot) {
                    low++;
                }
                array[high] = array[low];
            }
            // 把基准放在合适的位置，它左边的数都比它小，它右边的数都比它大
            array[low] = pivot;
            quickSort(array, left, low - 1); //low是当前基准数的位置
            quickSort(array, low + 1, right);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 32, 1, 4, 99, 88, 23, 123, 3, 44, 11, 66, 22, 12, 122 };
        print(bubbleSort(arr), "冒泡排序");
        print(bubbleSort2(arr), "改进冒泡");
        print(sortQuick(arr),"快速排序");
    }
}
