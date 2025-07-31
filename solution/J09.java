import java.util.Stack;

public class J09 {

    public static void main(String[] args) {
        System.out.println(solution3(10));
        System.out.println(solution3(27));
        System.out.println(solution3(12345));
    }

    // 이 부분을 변경해서 실행해보세요.
    public static String solution(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            int remainder = decimal % 2;
            stack.push(remainder);
            decimal /= 2;
        }

        // String 의 + 연산은 시간복잡도 측면에서 성능이 좋지 않습니다.
        // 따라서 StringBuilder 를 사용했습니다.
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public static String solution2(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            int remainder = decimal % 2;
            System.out.println(remainder);
            stack.push(remainder);
            decimal /= 2;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static String solution3(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while(decimal > 0) {
            int remainder = decimal % 2;
            stack.push(remainder);
            decimal /= 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}