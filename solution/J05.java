import java.util.Arrays;

public class J05 {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(solution(new int[][]{{1,4},{3,2},{1,4}})));
//        System.out.println(Arrays.toString(solution(new int[][]{{5,},{3,2},{1,4}})));
//    }

    public static void main(String[] args) {

        // 예제 1
        int[][] arr1_1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2_1 = {{3, 3}, {3, 3}};
        int[][] result1 = solution(arr1_1, arr2_1);
        System.out.println("예제 1 결과:");
        print(result1); // [[15, 15], [15, 15], [15, 15]]

//        // 예제 2
//        int[][] arr1_2 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
//        int[][] arr2_2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
//        int[][] result2 = solution(arr1_2, arr2_2);
//        System.out.println("예제 2 결과:");
//        print(result2); // [[22, 22, 11], [36, 28, 18], [29, 20, 14]]
    }

    // 행렬 출력 메서드
    public static void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {
        // ❶ 행렬 arr1과 arr2의 행과 열의 수
        int r1 = arr1.length; // 3
        int c1 = arr1[0].length; // 2
        int r2 = arr2.length; // 2
        int c2 = arr2[0].length; // 2

        //System.out.println(r1 + " " + c1 + " " + r2 + " " + c2);

        // ❷ 결과를 저장할 2차원 배열 초기화
        int[][] answer = new int[r1][c2];

        // ❸ 첫 번째 행렬 arr1의 각 행과 두 번째 행렬 arr2의 각 열에 대해
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                // ❹ 두 행렬의 데이터를 곱해 결과 리스트에 더해줌
                for (int k = 0; k < c1; k++) {
                    //System.out.println(arr1[i][k] + " " + arr2[k][j]);
                    //System.out.println(i + " " + j + " " + i + " " + k+ " " + k+ " " + j);
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }

}
