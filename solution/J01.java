import java.util.Arrays;

public class J01 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{1, -5, 2, 4, 3})));
        System.out.println(Arrays.toString(bubbleSort(new int[]{2, 1, 1, 3, 2, 5, 4})));
        System.out.println(Arrays.toString(bubbleSort(new int[]{6, 1, 7})));
    }

    // 이 부분을 변경해서 실행해보세요.
    private static int[] solution(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    private static int[] bubbleSort(int[] org) {
        int[] arr = org.clone();
        int n = arr.length;
        for (int i = 0; i < n; i++) {  // 1,2,3,4
            for (int j = 0; j < n - 1; j++) { // 1,2,3
                if (arr[j] > arr[j + 1]) { // 1 > -5
                    int temp = arr[j];    // 1 = 1
                    arr[j] = arr[j + 1];  // 1 = -5   =  -5
                    arr[j + 1] = temp;  //    = -5
                }
            }
        }
        return arr;
    }
}