import java.util.Arrays;
import java.util.Collections;

// 문제57 정수 내림차순으로 배치하기 - 정렬
public class J53 {
    /*
    문제 설명
    함수 solution은 정수 n을 매개변수로 입력받습니다. n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴해주세요. 예를들어 n이 118372면 873211을 리턴하면 됩니다.

    제한 조건
    n은 1이상 8000000000 이하인 자연수입니다.
    입출력 예
    n	return
    118372	873211
    * */

    public long solution(long n) {
        // ❶ 정수 n을 문자열로 변환하고 각 자릿수를 배열로 저장합니다.
        String[] digits = String.valueOf(n).split("");

        // ❷ 내림차순으로 정렬합니다.
        Arrays.sort(digits, Collections.reverseOrder());

        // ❸ 정렬된 숫자를 다시 하나의 문자열로 합칩니다.
        StringBuilder sb = new StringBuilder();
        for (String digit : digits)
            sb.append(digit);

        // ❹ 문자열을 long형으로 변환하여 반환합니다.
        return Long.parseLong(sb.toString());
    }

}