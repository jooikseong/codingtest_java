import java.util.*;

// 문제 16 기능 개발 - 큐
public class J16_C {
    /*
    문제 설명
    프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

    또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

    먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

    제한 사항
    작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
    작업 진도는 100 미만의 자연수입니다.
    작업 속도는 100 이하의 자연수입니다.
    배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
            입출력 예
    progresses	speeds	return
            [93, 30, 55]	[1, 30, 5]	[2, 1]
            [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
    입출력 예 설명
    입출력 예 #1
    첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
    두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
    세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.

            따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.

    입출력 예 #2
    모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.

    따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.

    ※ 공지 - 2020년 7월 14일 테스트케이스가 추가되었습니다.
    */

    public static void main(String[] args) {
        int[] progresses = new int[]{93, 30, 55};
        int[] speeds     = new int[]{ 1, 30,  5};
        System.out.println(Arrays.toString(solution5(progresses, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new ArrayDeque<>();

        int n = progresses.length;
        // ❶ 각 작업의 배포 가능일 계산
        int[] daysLeft = new int[n];
        for (int i = 0; i < n; i++) {
            daysLeft[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }

        int count = 0; // ❷ 배포될 작접의 수 카운트
        int maxDay = daysLeft[0]; // ❸ 현재 배포될 작업 중 가장 늦게 배포될 작업의 가능일

        for (int i = 0; i < n; i++) {
            if (daysLeft[i] <= maxDay) { // ❹ 배포 가능일이 가장 늦은 배포일보다 빠르면
                count++;
            }
            else { // ❺ 배포 예정일이 기준 배포일보다 느리면
                answer.add(count);
                count = 1;
                maxDay = daysLeft[i];
            }
        }

        answer.add(count); // ❻ 마지막으로 카운트된 작업들을 함께 배포
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution2(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new ArrayDeque<>();

        int n = progresses.length;
        int[] daysLeft = new int[n];
        for (int i = 0; i < n; i++) {
            daysLeft[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        int count = 0;
        int maxDay = daysLeft[0];

        for (int i = 0; i < n; i++) {
            System.out.println(daysLeft[i]);
            if(daysLeft[i] <= maxDay) {
                count++;
            }
            else {
                answer.add(count);
                count = 1;
                maxDay = daysLeft[i];
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution3(int[] progresses, int[] speeds) {
        // 1. 각 작업이 며칠 걸리는지 계산해서 리스트에 담는다.
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            // (100 - 현재진도) / 속도 를 계산 후 올림 처리
            int remaining = 100 - progresses[i];
            int day = (int) Math.ceil((double) remaining / speeds[i]);
            days.add(day);
        }

        List<Integer> answerList = new ArrayList<>();

        // 2. 첫 번째 작업의 소요 일수를 '기준일'로 잡는다.
        int targetDay = days.get(0);
        // 3. '현재 배포될 기능 수'를 1로 시작한다.
        int count = 1;

        // 4. 두 번째 작업부터 하나씩 확인한다.
        for (int i = 1; i < days.size(); i++) {
            if (days.get(i) <= targetDay) {
                // 이번 작업이 '기준일'보다 빨리 끝나면? -> 기능 수 +1
                count++;
            } else {
                // 이번 작업이 '기준일'보다 오래 걸리면?
                // a. 지금까지의 '배포 기능 수'를 정답 리스트에 저장
                answerList.add(count);
                // b. 이 작업을 새로운 '기준일'로 바꾼다.
                targetDay = days.get(i);
                // c. '현재 배포될 기능 수'를 1로 리셋한다.
                count = 1;
            }
        }

        // 5. 마지막으로 남은 '배포 기능 수'를 정답 리스트에 추가한다.
        answerList.add(count);

        // List를 int[] 배열로 변환하여 반환
        return answerList.stream().mapToInt(i -> i).toArray();
    }

    public static int[] solution4(int[] progresses, int[] speeds) {

        // 1. 각 작업이 며칠 걸리는지 계산해서 배열(또는 리스트)에 담는다.
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            int day = (int) Math.ceil(remaining / speeds[i]);
            System.out.println(day);
            days.add(day);
        }

        List<Integer> answerList = new ArrayList<>();
        // 2. 첫 번째 작업의 소요 일수를 '기준일'로 잡는다.
        int targetDay = days.get(0);
        // 3. '현재 배포될 기능 수'를 1로 시작한다.
        int count = 1;

        // 4. 두 번째 작업부터 하나씩 확인한다:
        for (int i = 1; i < days.size(); i++)  {
            if(days.get(i) <= targetDay) {
                //    - 만약 이번 작업이 '기준일'보다 빨리 끝나면? -> '현재 배포될 기능 수'를 +1 한다.
                count++;
            } else {
                //    - 만약 이번 작업이 '기준일'보다 오래 걸리면? ->
                //         a. 지금까지의 '배포 기능 수'를 정답 리스트에 저장한다.
                answerList.add(count);
                //         b. 이 작업을 새로운 '기준일'로 바꾼다.
                targetDay = days.get(i);
                //         c. '현재 배포될 기능 수'를 1로 리셋한다.
                count = 1;
            }
        }
        // 5. 마지막으로 남은 '배포 기능 수'를 정답 리스트에 추가한다.
        answerList.add(count);
        System.out.println(answerList);
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] solution5(int[] progresses, int[] speeds) {

        // 1. 각 작업이 며칠 걸리는지 계산해서 배열(또는 리스트)에 담는다.
        List<Integer> days = new ArrayList<>();
        for(int i = 0; i < progresses.length; i++) {
            int ramaining = 100 - progresses[i];
            int day = (int) Math.ceil(ramaining / speeds[i]);
            days.add(day);
        }

        List<Integer> answerList = new ArrayList<>();
        // 2. 첫 번째 작업의 소요 일수를 '기준일'로 잡는다.
        int targetDay = days.get(0);

        // 3. '현재 배포될 기능 수'를 1로 시작한다.
        int count = 1;

        // 4. 두 번째 작업부터 하나씩 확인한다:
        for( int i = 1; i<days.size(); i++) {
            if(days.get(i) <= targetDay) {
                //    - 만약 이번 작업이 '기준일'보다 빨리 끝나면? -> '현재 배포될 기능 수'를 +1 한다.
                count++;
            } else {
                //    - 만약 이번 작업이 '기준일'보다 오래 걸리면? ->
                //         a. 지금까지의 '배포 기능 수'를 정답 리스트에 저장한다.
                answerList.add(count);
                //         b. 이 작업을 새로운 '기준일'로 바꾼다.
                targetDay = days.get(i);
                //         c. '현재 배포될 기능 수'를 1로 리셋한다.
                count = 1;
            }
        }

        // 5. 마지막으로 남은 '배포 기능 수'를 정답 리스트에 추가한다.
        answerList.add(count);

        return answerList.stream().mapToInt(i -> i).toArray();
    }


    // 1. 각 작업이 며칠 걸리는지 계산해서 배열(또는 리스트)에 담는다.
    // 2. 첫 번째 작업의 소요 일수를 '기준일'로 잡는다.
    // 3. '현재 배포될 기능 수'를 1로 시작한다.
    // 4. 두 번째 작업부터 하나씩 확인한다:
    //    - 만약 이번 작업이 '기준일'보다 빨리 끝나면? -> '현재 배포될 기능 수'를 +1 한다.
    //    - 만약 이번 작업이 '기준일'보다 오래 걸리면? ->
    //         a. 지금까지의 '배포 기능 수'를 정답 리스트에 저장한다.
    //         b. 이 작업을 새로운 '기준일'로 바꾼다.
    //         c. '현재 배포될 기능 수'를 1로 리셋한다.
    // 5. 마지막으로 남은 '배포 기능 수'를 정답 리스트에 추가한다.
}
