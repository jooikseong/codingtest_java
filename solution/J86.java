import java.util.ArrayDeque;
import java.util.Arrays;

// 문제89 110 옮기기
class J86 {

    /*
    문제 설명
    0과 1로 이루어진 어떤 문자열 x에 대해서, 당신은 다음과 같은 행동을 통해 x를 최대한 사전 순으로 앞에 오도록 만들고자 합니다.

    x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입합니다.
    예를 들어, x = "11100" 일 때, 여기서 중앙에 있는 "110"을 뽑으면 x = "10" 이 됩니다. 뽑았던 "110"을 x의 맨 앞에 다시 삽입하면 x = "11010" 이 됩니다.

    변형시킬 문자열 x가 여러 개 들어있는 문자열 배열 s가 주어졌을 때, 각 문자열에 대해서 위의 행동으로 변형해서 만들 수 있는 문자열 중 사전 순으로 가장 앞에 오는 문자열을 배열에 담아 return 하도록 solution 함수를 완성해주세요.

    제한사항
    1 ≤ s의 길이 ≤ 1,000,000
    1 ≤ s의 각 원소 길이 ≤ 1,000,000
    1 ≤ s의 모든 원소의 길이의 합 ≤ 1,000,000
    입출력 예
    s	result
    ["1110","100111100","0111111010"]	["1101","100110110","0110110111"]
    입출력 예 설명
    입출력 예 #1

    다음 그림은 "1110"을 "1101"로 만드는 과정을 나타낸 것입니다.
    110_ex1.png

    "1101"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "1101"을 담아야 합니다.

    다음 그림은 "100111100"을 "100110110"으로 만드는 과정을 나타낸 것입니다.

    110_ex2.png

    "100110110"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "100110110"을 담아야 합니다.
    그림에 나온 방식 말고도 다른 방법으로 "100110110"을 만들 수 있습니다.

    다음 그림은 "0111111010"을 "0110110111"로 만드는 과정을 나타낸 것입니다.

    110_ex3.png

    "0110110111"보다 사전 순으로 더 앞에 오는 문자열을 만들 수 없으므로, 배열에 "0110110111"을 담아야 합니다.
    그림에 나온 방식 말고도 다른 방법으로 "0110110111"을 만들 수 있습니다.
    * */

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"1110","100111100","0111111010"})));
        System.out.println(Arrays.toString(solution(new String[]{"1011110","01110","101101111010"})));
        System.out.println(Arrays.toString(solution(new String[]{"1100111011101001"})));
    }

    public static String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            StringBuilder start = new StringBuilder();
            ArrayDeque<Character> stack = new ArrayDeque<>();
            char[] end = s[i].toCharArray();

            for (char c : end) {
                stack.push(c);
                if (stack.size() >= 3) {
                    char s3 = stack.pop();
                    char s2 = stack.pop();
                    char s1 = stack.pop();
                    if (("" + s1 + s2 + s3).equals("110")) {
                        start.append("110");
                    }
                    else {
                        stack.push(s1);
                        stack.push(s2);
                        stack.push(s3);
                    }
                }
            }

            StringBuilder ans = new StringBuilder();
            while (!stack.isEmpty()) {
                ans.append(stack.pollLast());
            }

            if (ans.indexOf("11") >= 0) {
                ans.insert(ans.indexOf("11"), start);
            }
            else if (ans.lastIndexOf("0") >= 0) {
                ans.insert(ans.lastIndexOf("0") + 1, start);
            }
            else {
                ans.insert(0, start);
            }

            answer[i] = ans.toString();
        }

        return answer;
    }

}