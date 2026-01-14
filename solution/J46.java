
// 문제50 N-Queen - 백트래킹
public class J46 {

    /*
    문제 설명
    가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다. 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶습니다.

    예를 들어서 n이 4인경우 다음과 같이 퀸을 배치하면 n개의 퀸은 서로를 한번에 공격 할 수 없습니다.

    Imgur
    Imgur

    체스판의 가로 세로의 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수를 return하는 solution함수를 완성해주세요.

    제한사항
    퀸(Queen)은 가로, 세로, 대각선으로 이동할 수 있습니다.
    n은 12이하의 자연수 입니다.
    입출력 예
    n	result
    4	2
    입출력 예 설명
    입출력 예 #1
    문제의 예시와 같습니다.
    * */
    private static int N;
    private static boolean[] width;
    private static boolean[] diagonal1;
    private static boolean[] diagonal2;

    // ❶ 퀸이 서로 공격할 수 없는 위치에 놓이는 경우의 수를 구하는 함수
    private static int getAns(int y) {
        int ans = 0;
        // ❷ 모든 행에 대해서 퀸의 위치가 결장되었을 경우
        if (y == N) {
            // ❸ 해결 가능한 경우의 수를 1 증가시킴
            ans++;
        }
        else {
            // ❹ 현재 행에서 퀸이 놓일 수 있는 모든 위치를 시도
            for (int i = 0; i < N; i++) {
                // ❺ 해당 위치에 이미 퀸이 있는 경우, 대각선상에 퀸이 있는 경우 스킵
                if (width[i] || diagonal1[i + y] || diagonal2[i - y + N])
                    continue;

                // ❻ 해당 위치에 퀸을 놓음
                width[i] = diagonal1[i + y] = diagonal2[i - y + N] = true;
                // ❼ 다음 행으로 이동하여 재귀적으로 해결 가능한 경우의 수 찾기
                ans += getAns(y + 1);
                // ❽ 해당 위치에 놓인 퀸을 제거함
                width[i] = diagonal1[i + y] = diagonal2[i - y + N] = false;
            }
        }

        return ans;
    }

    public int solution(int n) {
        N = n;
        width = new boolean[n];
        diagonal1 = new boolean[n * 2];
        diagonal2 = new boolean[n * 2];

        int answer = getAns(0);
        return answer;
    }

}