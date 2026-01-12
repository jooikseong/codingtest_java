public class J65 {

    public int solution(int n) {
        return Integer.toBinaryString(n).replace("0","").length();
    }

}