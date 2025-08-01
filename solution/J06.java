import java.util.Arrays;
import java.util.HashMap;

public class J06 {

    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] result = solution2(N, stages);
        System.out.println(Arrays.toString(result));

        N = 4;
        int[] stages2 = {4,4,4,4,4};
        result = solution2(N, stages2);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(int N, int[] stages) {
        // ❶ 스테이지별 도전자 수를 구함
        int[] challenger = new int[N + 2];
        for (int i = 0; i < stages.length; i++) {
            challenger[stages[i]] += 1;
        }

        System.out.println(Arrays.toString(challenger));

        // ❷ 스테이지별 실패한 사용자 수 계산
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        // ❸ 각 스테이지를 순회하며, 실패율 계산
        for (int i = 1; i <= N; i++) {
            if (challenger[i] == 0) { // ❹ 도전한 사람이 없는 경우, 실패율은 0
                fails.put(i, 0.);
            }
            else {
                fails.put(i, challenger[i] / total); // ❺ 실패율 구함
                total -= challenger[i]; // ❻ 다음 스테이지 실패율을 구하기 위해 현재 스테이지의 인원을 뺌
            }
        }

        // ❼ 실패율이 높은 스테이지부터 내림차순으로 정렬
        return fails.entrySet().stream().sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())).mapToInt(HashMap.Entry::getKey).toArray();
    }

    public static int[] solution2(int N, int[] stages) {
        int[] challenger = new int[N +2];
        for (int i = 0; i < stages.length; i++) {
            challenger[stages[i]] += 1;
        }

        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for(int i=1; i<=N; i++){
            if(challenger[i] == 0){
                fails.put(i, 0.);
            } else {
                fails.put(i, challenger[i] / total);
                total -= challenger[i];
            }
        }
        return fails.entrySet()
                .stream()
                .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
                .mapToInt(HashMap.Entry::getKey)
                .toArray();
    }
}