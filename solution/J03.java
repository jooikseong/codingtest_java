import java.util.Arrays;
import java.util.HashSet;

public class J03 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution2(new int[]{2, 1, 3, 4, 1})));
        System.out.println(Arrays.toString(solution2(new int[]{5, 0, 2, 7})));
    }

    public static int[] solution(int[] numbers) {
        HashSet<Integer> set = new HashSet<>(); // ❶ 중복 값 제거를 위한 해쉬셋 생성
        // ❷ 두 수를 선택하는 모든 경우의 수를 반복문으로 구함
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // ❸ 두 수를 더한 결과를 새로운 배열에 추가
                set.add(numbers[i] + numbers[j]);
            }
        }
        // ❹ 해쉬셋의 값을 오름차순 정렬하고 int[] 형태의 배열로 변환하여 반환
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i< numbers.length; i++){
            for(int j= i+1; j<numbers.length; j++){
                set.add(numbers[i] + numbers[j]);
            }
        }
        return set.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}