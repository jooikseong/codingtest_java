import java.util.Arrays;
import java.util.Stack;

class J12 {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 2, 3}; // 배열 생성
        int[] result = solution2(prices);
        System.out.println(Arrays.toString(result)); // 결과 출력
//        System.out.println(new int[]{1, 2, 3, 2, 3});
    }

    public static int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n]; // ❶ 가격이 떨어지지 않은 기간을 저장할 배열

        // 스택(stack)을 사용해 이전 가격과 현재 가격 비교
        Stack<Integer> stack = new Stack<>(); // ❷ 스택 생성
        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // ❸ 가격이 떨어졌으므로 이전 가격의 기간 계산
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }

        // ❹ 스택에 남아 있는 가격들은 가격이 떨어지지 않은 경우
        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = n - 1 - j;
        }

        return answer;
    }

    public static int[] solution2(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 첫 번째 인덱스를 스택에 넣음

        for (int i = 0; i < n; i++) {
            // 현재 가격이 스택 top 가격보다 작으면
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int j = stack.pop(); // 떨어진 시점 확정
                answer[j] = i - j;   // 걸린 시간 기록
            }
            stack.push(i); // 현재 인덱스를 스택에 넣음
        }

        while (!stack.isEmpty()){
            int j = stack.pop();
            answer[j] = n - 1 - j;
        }

        return answer;
    }

}