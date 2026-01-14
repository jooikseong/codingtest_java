import java.util.ArrayList;

// 문제59 가장 큰 수 - 정렬
public class J55 {

    /*
    문제 설명
    0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

    예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

    0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    numbers의 길이는 1 이상 100,000 이하입니다.
    numbers의 원소는 0 이상 1,000 이하입니다.
    정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
    입출력 예
    numbers	return
    [6, 10, 2]	"6210"
    [3, 30, 34, 5, 9]	"9534330"
    * */

    public String solution(int[] numbers) {
        // ❶ int형 정수 배열을 문자열로 바꾸어 list에 저장합니다.
        ArrayList<String> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(String.valueOf(number));
        }

        // ❷ 조합하여 비교하여 더 큰 수를 기준으로 내림차순 정렬합니다.
        list.sort((o1, o2) -> {
            int a = Integer.parseInt(o1 + o2);
            int b = Integer.parseInt(o2 + o1);
            return Integer.compare(b, a);
        });

        // ❸ 정렬된 수를 나열하여 문자열로 만듭니다.
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }

        // ❹ 문자열을 반환합니다. 맨앞에 "0" 이 있는 경우는 "0"만 반환합니다.
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }

}