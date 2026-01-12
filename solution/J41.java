import java.util.ArrayDeque;

// 문제46 경주로 건설 - 그래프
public class J41 {
    /*
    문제 설명
    kakao_road1.png

    건설회사의 설계사인 죠르디는 고객사로부터 자동차 경주로 건설에 필요한 견적을 의뢰받았습니다.
    제공된 경주로 설계 도면에 따르면 경주로 부지는 N x N 크기의 정사각형 격자 형태이며 각 격자는 1 x 1 크기입니다.
    설계 도면에는 각 격자의 칸은 0 또는 1 로 채워져 있으며, 0은 칸이 비어 있음을 1은 해당 칸이 벽으로 채워져 있음을 나타냅니다.
    경주로의 출발점은 (0, 0) 칸(좌측 상단)이며, 도착점은 (N-1, N-1) 칸(우측 하단)입니다. 죠르디는 출발점인 (0, 0) 칸에서 출발한 자동차가 도착점인 (N-1, N-1) 칸까지 무사히 도달할 수 있게 중간에 끊기지 않도록 경주로를 건설해야 합니다.
    경주로는 상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설할 수 있으며, 벽이 있는 칸에는 경주로를 건설할 수 없습니다.
    이때, 인접한 두 빈 칸을 상하 또는 좌우로 연결한 경주로를 직선 도로 라고 합니다.
    또한 두 직선 도로가 서로 직각으로 만나는 지점을 코너 라고 부릅니다.
    건설 비용을 계산해 보니 직선 도로 하나를 만들 때는 100원이 소요되며, 코너를 하나 만들 때는 500원이 추가로 듭니다.
    죠르디는 견적서 작성을 위해 경주로를 건설하는 데 필요한 최소 비용을 계산해야 합니다.

    예를 들어, 아래 그림은 직선 도로 6개와 코너 4개로 구성된 임의의 경주로 예시이며, 건설 비용은 6 x 100 + 4 x 500 = 2600원 입니다.

    kakao_road2.png

    또 다른 예로, 아래 그림은 직선 도로 4개와 코너 1개로 구성된 경주로이며, 건설 비용은 4 x 100 + 1 x 500 = 900원 입니다.

    kakao_road3.png

    도면의 상태(0은 비어 있음, 1은 벽)을 나타내는 2차원 배열 board가 매개변수로 주어질 때, 경주로를 건설하는데 필요한 최소 비용을 return 하도록 solution 함수를 완성해주세요.

    [제한사항]
    board는 2차원 정사각 배열로 배열의 크기는 3 이상 25 이하입니다.
    board 배열의 각 원소의 값은 0 또는 1 입니다.
    도면의 가장 왼쪽 상단 좌표는 (0, 0)이며, 가장 우측 하단 좌표는 (N-1, N-1) 입니다.
    원소의 값 0은 칸이 비어 있어 도로 연결이 가능함을 1은 칸이 벽으로 채워져 있어 도로 연결이 불가능함을 나타냅니다.
    board는 항상 출발점에서 도착점까지 경주로를 건설할 수 있는 형태로 주어집니다.
    출발점과 도착점 칸의 원소의 값은 항상 0으로 주어집니다.
    입출력 예
    board	result
    [[0,0,0],[0,0,0],[0,0,0]]	900
    [[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]]	3800
    [[0,0,1,0],[0,0,0,0],[0,1,0,1],[1,0,0,0]]	2100
    [[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]	3200
    입출력 예에 대한 설명
    입출력 예 #1

    본문의 예시와 같습니다.

    입출력 예 #2

    kakao_road4.png

    위와 같이 경주로를 건설하면 직선 도로 18개, 코너 4개로 총 3800원이 듭니다.

    입출력 예 #3

    kakao_road5.png

    위와 같이 경주로를 건설하면 직선 도로 6개, 코너 3개로 총 2100원이 듭니다.

    입출력 예 #4

    kakao_road6.png

    붉은색 경로와 같이 경주로를 건설하면 직선 도로 12개, 코너 4개로 총 3200원이 듭니다.
    만약, 파란색 경로와 같이 경주로를 건설한다면 직선 도로 10개, 코너 5개로 총 3500원이 들며, 더 많은 비용이 듭니다.
    * */

    private static class Node {
        int x, y, direction, cost;

        public Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
    }

    // 순서가 반드시 (0, -1), (-1, 0), (0, 1), (1, 0) 순서로 되어야합니다. 코너 계산에 필요함
    private static final int[] rx = {0, -1, 0, 1};
    private static final int[] ry = {-1, 0, 1, 0};

    private static int N;
    private static int[][][] visited;

    // ❶ 주어진 좌표가 보드의 범위 내에 있는지 확인
    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    // ❷ 주어진 좌표가 차단되었거나 이동할 수 없는지 확인
    private static boolean isBlocked(int[][] board, int x, int y) {
        return (x == 0 && y == 0) || !isValid(x, y) || board[x][y] == 1;
    }

    // ❸ 이전 방향과 현재 방향에 따라 비용 계산
    private static int calculateCost(int direction, int prevDirection, int cost) {
        if (prevDirection == -1 || (prevDirection - direction) % 2 == 0)
            return cost + 100;
        return cost + 600;
    }

    // ❹ 주어진 좌표와 방향이 아직 방문하지 않았거나 새 비용이 더 작은 경우
    private static boolean isShouldUpdate(int x, int y, int direction, int newCost) {
        return visited[x][y][direction] == 0 || visited[x][y][direction] > newCost;
    }

    public int solution(int[][] board) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, -1, 0));
        N = board.length;
        visited = new int[N][N][4];

        int answer = Integer.MAX_VALUE;

        // ❺ 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // ❻ 가능한 모든 방향에 대해 반복
            for (int i = 0; i < 4; i++) {
                int new_x = now.x + rx[i];
                int new_y = now.y + ry[i];

                // ❼ 이동할 수 없는 좌표는 건너뛰기
                if (isBlocked(board, new_x, new_y)) {
                    continue;
                }

                int new_cost = calculateCost(i, now.direction, now.cost);

                // ❽ 도착지에 도달한 경우 최소 비용 업데이트
                if (new_x == N - 1 && new_y == N - 1) {
                    answer = Math.min(answer, new_cost);
                }
                // ❾ 좌표와 방향이 아직 방문하지 않았거나 새 비용이 더 작은 경우 큐에 추가
                else if(isShouldUpdate(new_x, new_y, i, new_cost)) {
                    queue.add(new Node(new_x, new_y, i, new_cost));
                    visited[new_x][new_y][i] = new_cost;
                }
            }
        }

        return answer;
    }

}