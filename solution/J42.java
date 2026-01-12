import java.util.ArrayList;

// 문제46 전력망을 둘로 나누기 - 그래프
public class J42 {

    /*
    문제 설명
    n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

    송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

    제한사항
    n은 2 이상 100 이하인 자연수입니다.
    wires는 길이가 n-1인 정수형 2차원 배열입니다.
    wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
    1 ≤ v1 < v2 ≤ n 입니다.
    전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
    입출력 예
    n	wires	result
    9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]	3
    4	[[1,2],[2,3],[3,4]]	0
    7	[[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]	1
    입출력 예 설명
    입출력 예 #1

    다음 그림은 주어진 입력을 해결하는 방법 중 하나를 나타낸 것입니다.
    ex1.png
    4번과 7번을 연결하는 전선을 끊으면 두 전력망은 각 6개와 3개의 송전탑을 가지며, 이보다 더 비슷한 개수로 전력망을 나눌 수 없습니다.
    또 다른 방법으로는 3번과 4번을 연결하는 전선을 끊어도 최선의 정답을 도출할 수 있습니다.
    입출력 예 #2

    다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
    ex2.png
    2번과 3번을 연결하는 전선을 끊으면 두 전력망이 모두 2개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
    입출력 예 #3

    다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
    ex3.png
    3번과 7번을 연결하는 전선을 끊으면 두 전력망이 각각 4개와 3개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
    * */

    private static boolean[] visited;
    private static ArrayList<Integer>[] adjList;
    private static int N, answer;

    public static int solution(int n, int[][] wires) {
        N = n;
        answer = n - 1;

        // ❶ 전선의 연결 정보를 저장할 인접 리스트 초기화
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // ❷ 전선의 연결 정보를 인접 리스트에 저장
        for (int[] wire : wires) {
            adjList[wire[0]].add(wire[1]);
            adjList[wire[1]].add(wire[0]);
        }

        visited = new boolean[n + 1];

        // ❸ 깊이 우선 탐색 시작
        dfs(1);

        return answer;
    }

    private static int dfs(int now) {
        visited[now] = true;

        // ❹ 자식 노드의 수를 저장하고 반환할 변수 선언
        int sum = 0;
        // ❺ 연결된 모든 전선을 확인
        for (int next : adjList[now]) {
            if (!visited[next]) {
                // ❻ (전체 노드 - 자식 트리의 노드 수) - (자식 트리의 노드 수) 의 절대값이 가장 작은 값을 구함
                int cnt = dfs(next); // 자식 트리가 가진 노드의 수
                answer = Math.min(answer, Math.abs(N - cnt * 2));
                // ❼ 자식 노드의 수를 더함
                sum += cnt;
            }
        }

        // ❽ 전체 자식 노드의 수에 1(현재 now 노드)을 더해서 반환
        return sum + 1;
    }

}