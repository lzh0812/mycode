package com.lzh.util;

import java.util.Arrays;
import java.util.Scanner;

public class SortUtil {
    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] target = new int[n];
        for(int i = 0;i<n;i++){
            int x = sc.nextInt();
            target[i] = x;
        }
        target = insertSort(target);
        for(int k : target){
            System.out.println(k);
        }
    }

    /*--------------------------插入排序--------------插入排序-----------------插入排序--------*/
    // 插入排序的基本思想：每步将一个待排序元素，按其排序码大小插入到前面已经排好序的一组元素中，直到元素全部插入为止。
    // 在这里，我们介绍三种具体的插入排序算法：直接插入排序，希尔排序与折半插入排序。

    /**
     * 1、直接插入排序 　　 直接插入排序的思想：当插入第i(i>=1)个元素时，<br>
     * 前面的V[0],…,V[i-1]等i-1个 元素已经有序。<br>
     * 这时，将第i个元素与前i-1个元素V[i-1]，…，V[0]依次比较，<br>
     * 找到插入位置即将V[i]插入，同时原来位置上的元素向后顺移。<br>
     * 在这里，插入位置的查找是顺序查找。直接插入排序是一种稳定的排序算法，其实现如下：<br>
     * Title: 插入排序中的直接插入排序，依赖于初始序列 Description: 在有序序列中不断插入新的记录以达到扩大有序区到整个数组的目的 时间复杂度：最好情形O(n)，<br>
     * 平均情形O(n^2)，最差情形O(n^2) 空间复杂度：O(1) 稳 定 性：稳定 内部排序(在排序过程中数据元素完全在内存)
     */
    public static int[] insertSort(int[] target) {

        if (target != null && target.length != 1) { // 待排序数组不为空且长度大于1
            for (int i = 1; i < target.length; i++) { // 不断扩大有序序列，直到扩展到整个数组
                for (int j = i; j > 0; j--) { // 向有序序列中插入新的元素
                    if (target[j] < target[j - 1]) { // 交换
                        int temp = target[j];
                        target[j] = target[j - 1];
                        target[j - 1] = temp;
                    }
                }
            }
        }
        return target;
    }

    /**
     * 希尔排序的思想：设待排序序列共n个元素，首先取一个整数gap<n作为间隔，将全部元素分为间隔为gap的gap个子序列并对每一个子序列进行直接插入排序。<br>
     * 然后，缩小间隔gap，重复上述操作，直至gap缩小为1，此时所有元素位于同一个序列且有序。由于刚开始时，gap较大，每个子序列元素较少，排序速度较快；<br>
     * 待到排序后期，gap变小，每个子序列元素较多，但大部分元素基本有序，所以排序速度仍较快。一般地，gap取 （gap/3 + 1）。希尔排序是一种不稳定的排序方法，其实现如下： Title: 插入排序中的希尔排序，依赖于初始序列 Description: 分别对间隔为gap的gap个子序列进行直接插入排序，不断缩小gap,直至为 1
     * 刚开始时，gap较大，每个子序列元素较少，排序速度较快； 待到排序后期，gap变小，每个子序列元素较多，但大部分元素基本有序，所以排序速度仍较快。 时间复杂度：O(n) ~ O(n^2) 空间复杂度：O(1) 稳 定 性：不稳定 内部排序(在排序过程中数据元素完全在内存)
     * 
     * @author rico
     * @created 2017年5月20日 上午10:40:00
     */
    public static void shellSort(int[] target) {
        if (target != null && target.length != 1) {
            int gap = target.length;
            while (gap > 1) { // gap为int型，自动取整
                gap = gap / 3 + 1;
                for (int i = gap; i < target.length; i++) {
                    int j = i - gap;
                    while (j >= 0) {
                        if (target[j + gap] < target[j]) {
                            swap(target, j, j + gap);
                            j -= gap;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void swap(int[] target, int i, int j) {
        int temp = target[i];
        target[i] = target[j];
        target[j] = temp;
    }

    /**
     * 折半插入排序的思想：当插入第i(i>=1)个元素时，前面的V[0],…,V[i-1]等i-1个 元素已经有序。<br>
     * 这时，折半搜索第i个元素在前i-1个元素V[i-1]，…，V[0]中的插入位置，然后直接将V[i]插入，同时原来位置上的元素向后顺移。<br>
     * 与直接插入排序不同的是，折半插入排序比直接插入排序明显减少了关键字之间的比较次数，但是移动次数是没有改变。<br>
     * 所以，折半插入排序和插入排序的时间复杂度相同都是O（N^2），但其减少了比较次数，所以该算法仍然比直接插入排序好。<br>
     * 折半插入排序是一种稳定的排序算法，其实现如下： Title: 插入排序中的折半插入排序，依赖于初始序列 Description: 折半搜索出插入位置，并直接插入;与直接插入搜索的区别是，后者的搜索要快于顺序搜索 时间复杂度：折半插入排序比直接插入排序明显减少了关键字之间的比较次数，但是移动次数是没有改变。所以，
     * 折半插入排序和插入排序的时间复杂度相同都是O（N^2），在减少了比较次数方面它确实相当优秀，所以该算法仍然比直接插入排序好。 空间复杂度：O(1) 稳 定 性：稳定 内部排序(在排序过程中数据元素完全在内存)
     * 
     */
    public static int[] binaryInsertSort(int[] target) {
        if (target != null && target.length > 1) {
            for (int i = 1; i < target.length; i++) {
                int left = 0;
                int right = i - 1;
                int mid;
                int temp = target[i];
                if (temp < target[right]) { // 当前值小于有序序列的最大值时，开始查找插入位置
                    while (left <= right) {
                        mid = (left + right) / 2;
                        if (target[mid] < temp) {
                            left = mid + 1; // 缩小插入区间
                        } else if (target[mid] > temp) {
                            right = mid - 1; // 缩小插入区间
                        } else { // 待插入值与有序序列中的target[mid]相等，保证稳定性的处理
                            left = left + 1;
                        }
                    }

                    // left及其后面的数据顺序向后移动，并在left位置插入
                    for (int j = i; j > left; j--) {
                        target[j] = target[j - 1];
                    }
                    target[left] = temp;
                }
            }
        }
        return target;
    }

    /*-----------------------选择排序------------------------------------*/
    // 选择排序的基本思想：每一趟 (例如第i趟，i = 0,1,…)在后面第n-i个待排序元素中选出最小元素作为有序序列的第i个元素，
    // 直到第n-1趟结束后，所有元素有序。在这里，我们介绍两种具体的选择排序算法：直接选择排序与堆排序。

    /**
     * 1、直接选择排序 　　 直接选择排序的思想：第一次从R[0]~R[n-1]中选取最小值，与R[0]交换，第二次从R1~R[n-1]中选取最小值，与R1交换，….，第i次从R[i-1]~R[n-1]中选取最小值，<br>
     * 与R[i-1]交换，…..，第n-1次从R[n-2]~R[n-1]中选取最小值，与R[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。<br>
     * 直接选择排序是一种不稳定的排序算法，其实现如下： 
     * Title: 选择排序中的直接选择排序，依赖于初始序列 Description: 每一趟 (例如第i趟，i = 0,1,...)在后面第n-i个待排序元素中选出最小元素作为有序序列的第i个元素 时间复杂度：最好情形O(n^2)，平均情形O(n^2)，最差情形O(n^2) 空间复杂度：O(1) 稳
     * 定 性：不稳定 内部排序(在排序过程中数据元素完全在内存)
     * 
     */
    public static int[] selectSort(int[] target) {
        if (target != null && target.length != 1) {
            for (int i = 0; i < target.length; i++) {
                int min_index = i;
                for (int j = i + 1; j < target.length; j++) {
                    if (target[min_index] > target[j]) {
                        min_index = j;
                    }
                }
                if (target[min_index] != target[i]) { // 导致不稳定的因素：交换
                    int min = target[min_index];
                    target[min_index] = target[i];
                    target[i] = min;
                }
            }
        }
        return target;
    }

    /**
     * 2、堆排序 　　堆排序的核心是堆调整算法。首先根据初始输入数据，利用堆调整算法shiftDown()形成初始堆；然后，将堆顶元素与堆尾元素交换，缩小堆的范围并重新调整为堆，如此往复。<br>
     * 堆排序是一种不稳定的排序算法，其实现如下： Title: 堆排序(选择排序)，升序排序(最大堆)，依赖于初始序列 Description: 现将给定序列调整为最大堆，然后每次将堆顶元素与堆尾元素交换并缩小堆的范围，直到将堆缩小至1 时间复杂度：O(nlgn) 空间复杂度：O(1) 稳 定 性：不稳定 内部排序(在排序过程中数据元素完全在内存)
     * 
     * @author rico
     * @created 2017年5月25日 上午9:48:06
     */
    public static int[] heapSort(int[] target) {
        if (target != null && target.length > 1) {

            // 调整为最大堆
            int pos = (target.length - 2) / 2;
            while (pos >= 0) {
                shiftDown(target, pos, target.length - 1);
                pos--;
            }

            // 堆排序
            for (int i = target.length - 1; i > 0; i--) {
                int temp = target[i];
                target[i] = target[0];
                target[0] = temp;
                shiftDown(target, 0, i - 1);
            }
            return target;
        }
        return target;
    }

    /**
     * @description 自上而下调整为最大堆
     * @author rico
     * @created 2017年5月25日 上午9:45:40
     * @param target
     * @param start
     * @param end
     */
    private static void shiftDown(int[] target, int start, int end) {
        int i = start;
        int j = 2 * start + 1;
        int temp = target[i];
        while (j <= end) { // 迭代条件
            if (j < end && target[j + 1] > target[j]) { // 找出较大子女
                j = j + 1;
            }
            if (target[j] <= temp) { // 父亲大于子女
                break;
            } else {
                target[i] = target[j];
                i = j;
                j = 2 * j + 1;
            }
        }
        target[i] = temp;
    }
    
    /*-----------------------交换排序------------------------------------*/
    //交换排序的基本思想：根据序列中两个元素的比较结果来对换这两个记录在序列中的位置
    //也就是说，将键值较大的记录向序列的尾部移动，键值较小的记录向序列的前部移动。
    
    /** 
     * 1、冒泡排序
  　     　*   冒泡排序的思想：根据序列中两个元素的比较结果来对换这两个记录在序列中的位置，将键值较大的记录向序列的尾部移动，键值较小的记录向序列的前部移动。因此，每一趟都将较小的元素移到前面，较大的元素自然就逐渐沉到最后面了，也就是说，最大的元素最后才能确定，这就是冒泡。冒泡排序是一种稳定的排序算法，其实现如下：
     *  
     * Title: 交换排序中的冒泡排序 ，一般情形下指的是优化后的冒泡排序，最多进行n-1次比较，依赖于初始序列  
     * Description:因为越大的元素会经由交换慢慢"浮"到数列的顶端(最后位置)，最大的数最后才确定下来，所以称为冒泡排序
     * 时间复杂度：最好情形O(n)，平均情形O(n^2)，最差情形O(n^2) 
     * 空间复杂度：O(1) 
     * 稳 定 性：稳定
     * 内部排序(在排序过程中数据元素完全在内存)
     * 
       * @description 朴素冒泡排序(共进行n-1次比较)
       * @author rico         
       */
      public static int[] bubbleSort(int[] target) {
          int n = target.length;
          if (target != null && n != 1) {
              // 最多需要进行n-1躺，每一趟将比较小的元素移到前面，比较大的元素自然就逐渐沉到最后面了，这就是冒泡
              for (int i = 0; i < n-1; i++) {      
                  for (int j = n-1; j > i; j--) {
                      if(target[j] < target[j-1]){
                          int temp = target[j];
                          target[j] = target[j-1];
                          target[j-1] = temp;
                      }
                  }
                  System.out.println(Arrays.toString(target));
              }
          }
          return target;
      }

      /**     
       * @description 优化冒泡排序
       * @author rico         
       */
      public static int[] optimizeBubbleSort(int[] target) {
          int n = target.length;
          if (target != null && n != 1) {
              // 最多需要进行n-1躺，每一趟将比较小的元素移到前面，比较大的元素自然就逐渐沉到最后面了，这就是冒泡
              for (int i = 0; i < n-1; i++) {      
                  boolean exchange = false;
                  for (int j = n-1; j > i; j--) {
                      if(target[j] < target[j-1]){
                          int temp = target[j];
                          target[j] = target[j-1];
                          target[j-1] = temp;
                          exchange = true;
                      }
                  }
                  System.out.println(Arrays.toString(target));
                  if (!exchange){    // 若 i 到 n-1 这部分元素已经有序，则直接返回
                      return target;
                  }
              }
          }
          return target;
      }
      
    /**
     * 2、快速排序
     * 快速排序的思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小(划分过程)，
     * 然后再按此方法对这两部分数据分别进行快速排序(快速排序过程)，整个排序过程可以递归进行，以此达到整个数据变成有序序列。快速排序是一种不稳定的排序算法。
     * Title: 交换排序中的快速排序，目前应用最为广泛的排序算法，是一个递归算法，依赖于初始序列  
     * Description:快速排序包括两个过程：划分 和 快排
     * "划分"是指将原序列按基准元素划分两个子序列
     * "快排"是指分别对子序列进行快排
     * 
     * 就平均计算时间而言，快速排序是所有内部排序方法中最好的一个
     * 
     * 对大规模数据排序时，快排是快的；对小规模数据排序时，快排是慢的，甚至慢于简单选择排序等简单排序方法
     * 
     * 快速排序依赖于原始序列，因此其时间复杂度从O(nlgn)到O(n^2)不等
     * 时间复杂度：最好情形O(nlgn)，平均情形O(nlgn)，最差情形O(n^2)
     * 
     * 递归所消耗的栈空间
     * 空间复杂度：O(lgn)
     * 
     * 可选任一元素作为基准元素
     * 稳 定 性：不稳定
     * 
     * 
     * 内部排序(在排序过程中数据元素完全在内存)
     * 
     * @author rico
     * @created 2017年5月20日 上午10:40:00
     */
        /**     
         * @description 快排算法(递归算法)：在递去过程中就把问题解决了
         * @author rico       
         * @created 2017年5月20日 下午5:12:06     
         * @param target
         * @param left
         * @param right
         * @return     
         */
        public static int[] quickSort(int[] target, int left, int right) {

            if(right > left){     // 递归终止条件
                int base_index = partition(target,left, right);  // 原序列划分后基准元素的位置
                quickSort(target, left, base_index-1);    // 对第一个子序列快速排序，不包含基准元素！
                quickSort(target, base_index+1, right);   // 对第二个子序列快速排序，不包含基准元素！
                return target;
            }
            return target;
        }


        /**     
         * @description 序列划分，以第一个元素为基准元素
         * @author rico       
         * @created 2017年5月20日 下午5:10:54     
         * @param target  序列
         * @param left 序列左端
         * @param right  序列右端
         * @return     
         */
        public static int partition(int[] target, int left, int right){

            int base = target[left];   // 基准元素的值
            int base_index = left;    // 基准元素最终应该在的位置

            for (int i = left+1; i <= right; i++) {  // 从基准元素的下一个元素开始
                if(target[i] < base){       //  若其小于基准元素
                    base_index++;           // 若其小于基准元素,则基准元素最终位置后移；否则不用移动
                    if(base_index != i){    // 相等情况意味着i之前的元素都小于base,只需要换一次即可，不需要次次都换
                        int temp = target[base_index];
                        target[base_index] = target[i];
                        target[i] = temp;
                    }
                }
            }

            // 将基准元素就位
            target[left] = target[base_index];   
            target[base_index] = base;

            System.out.println(Arrays.toString(target));

            return base_index;  //返回划分后基准元素的位置
        }
        
        /*-----------------------归并排序------------------------------------*/
        //归并排序包含两个过程：”归”和”并”。其中，”归”是指将原序列分成半子序列，分别对子序列进行递归排序；”并”是指将排好序的各子序列合并成原序列。
        //归并排序算法是一个典型的递归算法，因此也是概念上最为简单的排序算法。与快速排序算法相比，归并排序算法不依赖于初始序列，并且是一种稳定的排序算法，但需要与原序列一样大小的辅助存储空间。
        /**
         * Title: 归并排序 ，概念上最为简单的排序算法，是一个递归算法 Description:归并排序包括两个过程：归 和 并
         * "归"是指将原序列分成半子序列，分别对子序列进行递归排序 "并"是指将排好序的各子序列合并成原序列
         * 
         * 归并排序的主要问题是：需要一个与原待排序数组一样大的辅助数组空间
         * 
         * 归并排序不依赖于原始序列，因此其最好情形、平均情形和最差情形时间复杂度都一样 时间复杂度：最好情形O(n)，平均情形O(n^2)，最差情形O(n^2)
         * 空间复杂度：O(n) 稳 定 性：稳定 内部排序(在排序过程中数据元素完全在内存)
         * 
         * @author rico
         * @created 2017年5月20日 上午10:40:00
         */

        /**
         * @description 归并排序算法(递归算法)：递去分解，归来合并
         * @author rico
         * @created 2017年5月20日 下午4:04:52
         * @param target
         *            待排序序列
         * @param left
         *            待排序序列起始位置
         * @param right
         *            待排序序列终止位置
         * @return
         */

        public static void mergeSort(int[] target) {
            int[] copy = Arrays.copyOf(target, target.length);    // 空间复杂度O(n)
            mergeSort(target, copy, 0, target.length - 1);
        }

        public static void mergeSort(int[] target, int[] copy, int left, int right) {
            if (right > left) { // 递归终止条件
                int mid = (left + right) / 2;
                mergeSort(target, copy, left, mid); // 归并排序第一个子序列
                mergeSort(target, copy, mid + 1, right); // 归并排序第二个子序列
                merge(target, copy, left, mid, right); // 合并子序列成原序列
            }
        }

        /**
         * @description 两路归并算法
         * @author rico
         * @created 2017年5月20日 下午3:59:16
         * @param target
         *            用于存储归并结果
         * @param left
         *            第一个有序表的第一个元素所在位置
         * @param mid
         *            第一个有序表的最后一个元素所在位置
         * @param right
         *            第二个有序表的最后一个元素所在位置
         * @return
         */
        public static void merge(int[] target, int[] copy, int left, int mid,
                int right) {

            // s1,s2是检查指针，index 是存放指针
            int s1 = left;
            int s2 = mid + 1;
            int index = left;

            // 两个表都未检查完，两两比较
            while (s1 <= mid && s2 <= right) {
                if (copy[s1] <= copy[s2]) { // 稳定性
                    target[index++] = copy[s1++];
                } else {
                    target[index++] = copy[s2++];
                }
            }

            // 若第一个表未检查完，复制
            while (s1 <= mid) {
                target[index++] = copy[s1++];
            }

            // 若第二个表未检查完，复制
            while (s2 <= right) {
                target[index++] = copy[s2++];
            }

            // 更新辅助数组 copy
            for (int i = left; i <= right; i++) {
                copy[i] = target[i];
            }
        }

        /*-----------------------分配排序(基数排序)------------------------------------*/
        //分配排序的基本思想：用空间换时间。在整个排序过程中，无须比较关键字，而是通过用额外的空间来”分配”和”收集”来实现排序，它们的时间复杂度可达到线性阶：O(n)。
        //其中，基数排序包括两个过程：首先，将目标序列各元素分配到各个桶中(分配过程)；然后，将各个桶中的元素按先进先出的顺序再放回去(收集过程)，如此往复，一共需要进行d趟，d为元素的位数。
        /**        
         * Title: 分配排序中的基数排序，不依赖于初始序列  
         * Description: 不是在对元素进行比较的基础上进行排序，而是采用 "分配 + 收集" 的办法 
         * 
         *              首先，将目标序列各元素分配到各个桶中；
         *              其次，将各个桶中的元素按先进先出的顺序再放回去
         *              如此往复...             
         * 
         *              时间复杂度：O(d*(r+n))或者 O(dn),d 的大小一般会受到 n的影响
         *              空间复杂度：O(rd + n)或者 O(n)
         *              稳    定   性：稳定
         *              内部排序(在排序过程中数据元素完全在内存)
         * @author rico       
         * @created 2017年5月20日 上午10:40:00    
         */   

        /**     
         * @description 分配 + 收集
         * @author rico       
         * @created 2017年5月21日 下午9:25:52     
         * @param target 待排序数组
         * @param r 基数
         * @param d 元素的位数
         * @param n 待排序元素个数
         * @return     
         */
        public static int[] radixSort(int[] target, int r, int d, int n){
            if (target != null && target.length != 1 ) {

                int[][] bucket = new int[r][n];  // 一共有基数r个桶，每个桶最多放n个元素
                int digit;  // 获取元素对应位上的数字，即装入那个桶
                int divisor = 1;   // 定义每一轮的除数，1, 10, 100, ...
                int[] count = new int[r];   // 统计每个桶中实际存放元素的个数

                for (int i = 0; i < d; i++) {  // d 位的元素，需要经过分配、收集d次即可完成排序

                    // 分配
                    for (int ele : target) {   
                        digit = (ele/divisor) % 10;  // 获取元素对应位上的数字(巧妙！！！)
                        bucket[digit][count[digit]++] = ele; // 将元素放入对应桶，桶中元素数目加1
                    }

                    // 收集
                    int index = 0;  // 目标数组的下标
                    for (int j = 0; j < r; j++) {
                        int k = 0;    // 用于按照先进先出顺序获取桶中元素
                        while(k < count[j]){
                            target[index++] = bucket[j][k++];  // 按照先进先出依次取出桶中的元素
                        }
                        count[j] = 0;  // 计数器归零
                    }
                    divisor *= 10;  //用于获取元素对应位数字
                }
            }
            return target;
        }
}
