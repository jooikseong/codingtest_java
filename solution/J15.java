import java.util.ArrayDeque;

// 문제 15 요셰푸스 문제 모름.
public class J15 {

    public static void main(String[] args) {
        System.out.println(solution2(5, 2));
    }

    // 이 부분을 변경해서 실행해보세요.
    private static int solution(int N, int K) {
        // ❶ 1부터 N까지의 번호를 deque에 추가
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }

        System.out.println(deque);

        // ❷ deque에 하나의 요소가 남을 때까지 반복
        while (deque.size() > 1) {
            // ❸ K번째 요소를 찾기 위해 앞에서부터 제거하고 뒤에 추가
            for (int i = 0; i < K - 1; i++) {
                deque.addLast(deque.pollFirst());
            }
            deque.pollFirst(); // ❹ K번째 요소 제거
        }
        return deque.pollFirst(); // ❺ 마지막으로 남은 요소 반환
    }

    private static int solution2(int N, int K) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            deque.addLast(i);
        }
        while (deque.size() > 1){
            for(int i =0; i<K -1; i++) {
                deque.addLast(deque.pollFirst());

            }
            deque.pollFirst();
        }
        System.out.println(deque);
        return deque.pollFirst();
    }

}