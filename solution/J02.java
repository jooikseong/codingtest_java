import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class J02 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution4(new int[]{4, 2, 2, 1, 3, 4})));
        System.out.println(Arrays.toString(solution4(new int[]{2, 1, 1, 3, 2, 5, 4})));
    }

    // 이 부분을 변경해서 실행해보세요.
    private static int[] solution(int[] arr) {
        Integer[] result = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new); // ❶ 중복값 제거
        Arrays.sort(result, Collections.reverseOrder());                                  // ❷ 내림차순 정렬
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();  // int형 배열로 변경 후 반환
    }

    private static int[] solution2(int[] arr) {
        // ❶ 중복값 제거, ❷ 내림차순 정렬
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int num : arr) {
            set.add(num);
        }

        // int형 배열에 담아서 반환
        int[] result = new int[set.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = set.pollFirst();
        }
        return result;
    }

    private static int[] solution3(int[] arr) {
        Integer[] result = Arrays.stream(arr)
                .boxed()
                .distinct()
                .toArray(Integer[]::new);

        Arrays.sort(result, Collections.reverseOrder());

        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }

    private static int[] solution4(int[] arr) {
        Integer[] result = Arrays.stream(arr)
                .boxed()
                .distinct()
                .toArray(Integer[]::new);

        Arrays.sort(result, Collections.reverseOrder());
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }




}