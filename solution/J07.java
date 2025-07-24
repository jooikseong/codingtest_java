import java.util.HashMap;
import java.util.HashSet;

public class J07 {

    public static void main(String[] args) {
        J07 rs = new J07();

        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println("dirs: " + dirs1 + " -> answer: " + rs.solution2(dirs1));
        System.out.println("dirs: " + dirs2 + " -> answer: " + rs.solution2(dirs2));
    }

    // ❶ 좌표평면을 벗어나는지 체크하는 메소드
    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }

    // ❷ 다음 좌표 결정을 위한 HashMap 생성
    private static final HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    public int solution(String dirs) {
        initLocation();
        int x = 5, y = 5;
        HashSet<String> answer = new HashSet<>(); // ❸ 겹치는 좌표는 1개로 처리하기 위함
        // ❹ 주어진 명령어로 움직이면서 좌표 저장
        for (int i = 0; i < dirs.length(); i++) {
            int[] offset = location.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];
            if (!isValidMove(nx, ny)) // ❺ 벗어난 좌표는 인정하지 않음
                continue;
            // ❻ A에서 B로 간 경우 B에서 A도 추가해야 함(총 경로의 개수는 방향성이 없음)
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);
            // ❼ 좌표를 이동했으므로 업데이트
            x = nx;
            y = ny;
        }

        return answer.size() / 2;
    }

    private static final HashMap<Character, int[]> location2 = new HashMap<>();

    public static void initLocation2() {
        location2.put('U', new int[]{0,1});
        location2.put('D', new int[]{0,-1});
        location2.put('L', new int[]{-1,0});
        location2.put('R', new int[]{1, 0});
    }

    public static int solution2(String dirs) {
        initLocation2();
        int x=5, y=5;
        HashSet<String> answer = new HashSet<>();
        for(int i=0; i< dirs.length(); i++ ){
            int[] offset = location2.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];
//            System.out.println(i + " " + nx + " " + ny);
            if(!isVaildMove2(nx, ny)){
                continue;
            }
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " "  + y);
            System.out.println(answer);
            x = nx;
            y = ny;
        }
//        System.out.println("answer.size()" + answer);
        return answer.size() / 2;

    }

    private static boolean isVaildMove2(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }

}