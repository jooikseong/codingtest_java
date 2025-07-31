import java.util.ArrayDeque;
import java.util.HashMap;

class J10 {

    public static void main(String[] args) {
//        System.out.println(solution2("[](){}"));
        System.out.println(solution2("}]()[{"));
//        System.out.println(solution2("[)(]"));
//        System.out.println(solution2("}}}"));
    }


    public static int solution(String s) {
        // ❶ 괄호 정보를 저장함. 코드를 간결하게 할 수 있음
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int n = s.length(); // 원본 문자열의 길이 저장
        s += s; // 원본 문자열 뒤에 원본 문자열을 이어 붙여서 2번 나오도록 만들어줌

        int answer = 0;

        // ❷ 확인할 문자열의 시작 인덱스를 0 부터 n 까지 이동
        A:for (int i = 0; i < n; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();

            // ❸ i(시작 위치)부터 원본 문자열의 길이인 n개까지 올바른 괄호 문자열인지 확인
            for (int j = i; j < i + n; j++) {
                char c = s.charAt(j);
                // HashMap 안에 해당 key 가 없다면 열리는 괄호임
                if (!map.containsKey(c)) {
                    stack.push(c);
                }
                else {
                    // ❹ 짝이 맞지 않으면 내부 for문은 종료하고 for문 A로 이동
                    if(stack.isEmpty() || !stack.pop().equals(map.get(c)))
                        continue A;
                }
            }

            // ❺ 3에서 continue 되지 않았고, 스택이 비어있으면 올바른 괄호 문자열임
            if (stack.isEmpty())
                answer++;
        }

        return answer;
    }

    public static int solution2(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        int n = s.length();
        s += s;
        System.out.println(s);
        System.out.println("@@@@@" + n);
        int answer = 0;

        A:for(int i = 0; i < n; i++){
            ArrayDeque<Character> stack = new ArrayDeque<>();
            for(int j = i; j < i + n; j++) {
                char c = s.charAt(j);
                System.out.println("현재 문자: " + c);
                System.out.println("현재 스택: " + stack);
                if(map.containsValue(c)){
                    stack.push(c);
                    System.out.println("Push: " + c);
                }
                else {
//                    if(stack.isEmpty() || !stack.pop().equals(map.get(c))){
//                        System.out.println("스택 비어있음 -> 불일치");
//                        continue A;
//                    }
                    if (stack.isEmpty()) {
                        System.out.println("스택 비어있음");
                        continue A;
                    }

                    Character expected = map.get(c);
                    Character top = stack.pop();
                    System.out.println("pop 탓나");
                    if (!top.equals(expected)) {
                        System.out.println("스택 불일치");
                        continue A;
                    }
                }
            }
            if(stack.isEmpty()){
                System.out.println("올바른 괄호열!" + i);
                answer++;
            }
        }
        return answer;
    }
}