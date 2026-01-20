// 문제67 카펫 - 시뮬레이션
public class J64 {

    /*
    문제 설명
    Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.

    carpet.png

    Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.

    Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.

    제한사항
    갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
    노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
    카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
    입출력 예
    brown	yellow	return
    10	2	[4, 3]
    8	1	[3, 3]
    24	24	[8, 6]
    * */
    public int[] solution(int brown, int yellow) {
        // ❶ 격자의 총 개수 (파란색 격자 + 흰색 격자)
        int totalSize = brown + yellow;
        // ❷ 세로 길이의 범위는 3부터 (갈색 격자 + 노란색 격자)의 제곱근
        int sqrt = (int)Math.sqrt(totalSize);
        for (int vertical = 3; vertical <= sqrt; vertical++) {
            // ❸ 사격형 구성이 되는지 확인
            if (totalSize % vertical == 0) {
                // ❹ 사각형의 가로 길이
                int horizontal = (int)(totalSize / vertical);
                // ❺ 카펫 형태로 만들 수 있는지 확인
                if (brown == (horizontal + vertical - 2) * 2) {
                    return new int[]{horizontal, vertical}; // ❻ [가로 길이, 세로 길이]
                }
            }
        }
        return new int[]{}; // ❼ 만약 답을 찾지 못했다면 빈 리스트를 반환
    }

}