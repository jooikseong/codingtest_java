import java.util.Arrays;

// 문제52 외벽 점검 - 백트래킹
public class J48 {

    /*
    문제 설명
    레스토랑을 운영하고 있는 "스카피"는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다. 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.

    레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다. 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는 지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다. 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다. 친구들이 1시간 동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다. 편의 상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다. 또, 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.

    외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist가 매개변수로 주어질 때, 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.

    제한사항
    n은 1 이상 200 이하인 자연수입니다.
    weak의 길이는 1 이상 15 이하입니다.
    서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
    취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
    weak의 원소는 0 이상 n - 1 이하인 정수입니다.
    dist의 길이는 1 이상 8 이하입니다.
    dist의 원소는 1 이상 100 이하인 자연수입니다.
    친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.
    입출력 예
    n	weak	dist	result
    12	[1, 5, 6, 10]	[1, 2, 3, 4]	2
    12	[1, 3, 4, 9, 10]	[3, 5, 7]	1
    입출력 예에 대한 설명
    입출력 예 #1

    원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.

    외벽점검-1.jpg

    친구들을 투입하는 예시 중 하나는 다음과 같습니다.

    4m를 이동할 수 있는 친구는 10m 지점에서 출발해 시계방향으로 돌아 1m 위치에 있는 취약 지점에서 외벽 점검을 마칩니다.
    2m를 이동할 수 있는 친구는 4.5m 지점에서 출발해 6.5m 지점에서 외벽 점검을 마칩니다.
    그 외에 여러 방법들이 있지만, 두 명보다 적은 친구를 투입하는 방법은 없습니다. 따라서 친구를 최소 두 명 투입해야 합니다.

    입출력 예 #2

    원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.

    외벽점검-2.jpg

    7m를 이동할 수 있는 친구가 4m 지점에서 출발해 반시계 방향으로 점검을 돌면 모든 취약 지점을 점검할 수 있습니다. 따라서 친구를 최소 한 명 투입하면 됩니다.
    * */

    private static int length, answer;
    private static int[] Weak;
    private static boolean[] used;

    // ❶ dist 배열의 친구들로 모든 외벽이 점검 가능한지 확인
    private static boolean check(int[] dist) {
        // ❷ 점검을 시작하는 외벽을 0 부터 length 까지 전부 확인함
        for (int i = 0; i < length; i++) {
            int idx = i;
            // ❸ 각 친구가 점검 가능한 외벽을 모두 점검하며 진행
            for (int distance : dist) {
                int position = Weak[idx++] + distance;
                while (idx < Weak.length && Weak[idx] <= position) {
                    idx++;
                }
            }
            // ❹ 모든 외벽을 점검 가능하면 true 반환
            if (idx - i >= length)
                return true;
        }
        // ❺ 모든 외벽을 점검할 수 없으면 false 반환
        return false;
    }

    // ❻ n개의 숫자를 나열하는 모든 경우의 수를 구함
    private static void backtrack(int n, int[] dist, int[] org) {
        if (n == org.length) {
            // ❼ 모든 외벽이 점검 가능하면 답 저장
            if (check(dist))
                answer = n;
            return;
        }

        // ❽ 한 번 사용한 친구는 다시 사용하지 않도록 used 배열을 활용하여 백트래킹
        for (int i = 0; i < org.length; i++) {
            if (!used[i]) {
                used[i] = true;
                dist[n] = org[i];
                backtrack(n + 1, dist, org);
                used[i] = false;
            }
        }
    }

    public static int solution(int n, int[] weak, int[] dist) {
        // ❾ 주어진 weak 지점들을 선형으로 만들어 줌
        length = weak.length;
        Weak = new int[length * 2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < length; j++) {
                Weak[j + (i * length)] = weak[j] + (i * n);
            }
        }

        // ➓ 오름차순으로 정렬
        Arrays.sort(dist);
        answer = -1; // 답을 -1 로 초기화
        used = new boolean[dist.length]; // used 배열 생성

        // ⓫ 가장 점검 범위가 큰 친구부터 1명 씩 늘려가며 답을 탐색
        for (int i = 1; i <= dist.length; i++) {
            int[] org = new int[i];
            System.arraycopy(dist, dist.length - i, org, 0, i);
            backtrack(0, new int[i], org);
            if (answer > 0) // 답을 찾았으면 종료해야 함
                break;
        }

        return answer;
    }

}