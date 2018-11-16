package com.github.cm.demo;

public class SimpleSort {
    /**
     * 快速选择排序
     * 时间复杂度 O(N^2) 空间复杂度 O(1)
     * @param data
     * @return
     */
    public static int[] straightSelectSort(int[] data) {
        int temp,min;
        for (int i = 0; i < data.length - 1; i++) {
             min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[min] < data[j]) {
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
        return data;
    }

    /**
     * 直接插入排序
     * 最好时间复杂度 O(N) 最坏时间复杂度 O(N^2)     空间复杂度 O(1)
     * @param data
     * @return
     */
    public static int[] straightInsertSort(int[] data){
        int temp,j;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            for (j = i-1; j >= 0 && temp<data[j]; j--) {
                    data[j+1] = data[j];
            }
            data[j+1] = temp;
        }
        return data;
    }

    /**
     * 冒泡排序
     * 最好时间复杂度 O(N) 最坏时间复杂度 O(N^2)     空间复杂度 O(1)
     * @param data
     * @return
     */
    public static int[] bubbleSort(int[] data){
        int temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length-i-1; j++) {
                if(data[j]>data[j+1]){
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }

        return data;
    }

    /**
     * 快速排序
     * 最好时间复杂度 O(nlogN) 最坏时间复杂度 O(N^2)     空间复杂度 O(nlogN)
     * @param data
     * @return
     */
    public static int[] quickSort(int[] data){
        return recursive_quickSort(data,0,data.length-1);
    }

    private static int[] recursive_quickSort(int[] data,int p,int q){
        if(p<q){
            int i = partition_quickSort(data, p, q);
            recursive_quickSort(data,p,i-1);
            recursive_quickSort(data,i+1,q);
        }
        return data;
    }

    private static int partition_quickSort(int[] data,int p,int q){
        int bz = data[p];
        while (p<q){
            while(p<q && data[q]>=bz)--q;
            int temp1 = data[p];
            data[p] = data[q];
            data[q] = temp1;

            while (p<q && data[p] <= bz)++p;
            int temp2 = data[p];
            data[p] = data[q];
            data[q] = temp2;
        }
        return q;
    }

    /**
     * 希尔排序
     * 最好时间复杂度 O(N) 最坏时间复杂度 O(N^3/2)     空间复杂度 O(1)
     * @param data
     * @return
     */
    public static int[] shellSort(int[] data){
        int d = (int)Math.ceil(data.length / 2);
        while (d>=1){
            shell_insertSort(data,d);
            if(d/2>=1){
                d = (int)Math.ceil(d / 2);
            }else{
                d = d/2;
            }
        }
        return data;
    }

    private static void shell_insertSort(int[] data,int d){
        for (int i = d; i < data.length; i++) {
            if(data[i] < data[i-d]){
                int j = i-d;
                int key = data[i];
                while (j>=0 && data[j] > key){
                    data[j+d] = data[j];
                    j -=d;
                }
                data[j+d] = key;
            }
        }
    }


    /**
     *堆排序
     * 最好时间复杂度 O(N) 最坏时间复杂度 O(N^3/2)     空间复杂度 O(1)
     * @param data
     * @return
     */
    public static int[] heapSort(int[] data){
        if(data == null || data.length <= 1){
            return data;
        }
        buildMaxHeap(data);
        int heapSize = data.length;
        for(int i = heapSize-1; i > 0; i--){
            int temp = data[i];
            data[i] = data[0];
            data[0] = temp;
            heapAdjust(data, 0, i);
        }
        return data;
    }

    private static void buildMaxHeap(int[] A){
        for (int i = (A.length -1) / 2 ; i >= 0; --i){
            heapAdjust(A, i, A.length);
        }
    }

    private static void heapAdjust(int[] A, int s, int length){
        int temp  = A[s];
        int child = 2*s+1;
        while (child < length) {
            if(child+1 < length && A[child] < A[child+1]){
                child++;
            }
            if(A[s] < A[child]){
                A[s] = A[child];
                s = child;
                child = 2*s+1;
            } else{
                break;
            }
            A[s] = temp;
        }
    }

    public static int[] mergeSort(int[] data){
        merge(data,0,data.length-1);
        return data;
    }
    private static void merge(int[] data, int low, int high){
        int mid = (low + high) / 2;
        if(low<high){
            merge(data,low,mid);
            merge(data,mid+1,high);
            merge(data,low,mid,high);
        }
    }

    private static void merge(int[] A, int low, int mid, int high){
        int[] temp = new int[high-low+1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (A[i] < A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = A[i++];
        }
        while (j <= high) {
            temp[k++] = A[j++];
        }
        for (int t = 0; t < temp.length; t++) {
            A[t+low] = temp[t];
        }
    }



    public static void main(String[] args) {
        int[] data = {1, 3, 5, 3, 2, 7, 5, 1};
        //快速选择排序
        System.out.println("快速选择排序:");
        int[] result1 = straightSelectSort(data);
        for (int d : result1)
            System.out.print(d);


        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("直接插入排序:");
        //直接插入排序
        int[] result2 = straightInsertSort(data);
        for (int d : result2)
            System.out.print(d);


        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("冒泡排序:");
        //直接插入排序
        int[] result3 = bubbleSort(data);
        for (int d : result3)
            System.out.print(d);


        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("快读排序:");
        //直接插入排序
        int[] result4 = quickSort(data);
        for (int d : result4)
            System.out.print(d);


        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("希尔排序:");
        //直接插入排序
        int[] result5 = shellSort(data);
        for (int d : result5)
            System.out.print(d);


        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("堆排序:");
        //直接插入排序
        int[] result6 = heapSort(data);
        for (int d : result6)
            System.out.print(d);

        System.out.println("--------");
        data = new int[]{1, 3, 5, 3, 2, 7, 5, 1};
        System.out.println("归并排序:");
        //归并排序
        int[] result7 = mergeSort(data);
        for (int d : result7)
            System.out.print(d);


    }
}
