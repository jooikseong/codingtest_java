import java.util.Arrays;
import java.util.HashSet;

// 문제79 단어 퍼즐 - 동적 계획법 
public class J76 {
    /*
    문제 설명
    단어 퍼즐은 주어진 단어 조각들을 이용해서 주어진 문장을 완성하는 퍼즐입니다. 이때, 주어진 각 단어 조각들은 각각 무한개씩 있다고 가정합니다. 예를 들어 주어진 단어 조각이 [“ba”, “na”, “n”, “a”]인 경우 "ba", "na", "n", "a" 단어 조각이 각각 무한개씩 있습니다. 이때, 만들어야 하는 문장이 “banana”라면 “ba”, “na”, “n”, “a”의 4개를 사용하여 문장을 완성할 수 있지만, “ba”, “na”, “na”의 3개만을 사용해도 “banana”를 완성할 수 있습니다. 사용 가능한 단어 조각들을 담고 있는 배열 strs와 완성해야 하는 문자열 t가 매개변수로 주어질 때, 주어진 문장을 완성하기 위해 사용해야 하는 단어조각 개수의 최솟값을 return 하도록 solution 함수를 완성해 주세요. 만약 주어진 문장을 완성하는 것이 불가능하면 -1을 return 하세요.

    제한사항
    strs는 사용 가능한 단어 조각들이 들어있는 배열로, 길이는 1 이상 100 이하입니다.
    strs의 각 원소는 사용 가능한 단어조각들이 중복 없이 들어있습니다.
    사용 가능한 단어 조각들은 문자열 형태이며, 모든 단어 조각의 길이는 1 이상 5 이하입니다.
    t는 완성해야 하는 문자열이며 길이는 1 이상 20,000 이하입니다.
    모든 문자열은 알파벳 소문자로만 이루어져 있습니다.
    입출력 예
    strs	t	result
    ["ba","na","n","a"]	"banana"	3
    ["app","ap","p","l","e","ple","pp"]	"apple"	2
    ["ba","an","nan","ban","n"]	"banana"	-1
    입출력 예 설명
    입출력 예 #1
    문제의 예시와 같습니다.

    입출력 예 #2
    "ap" 1개, "ple" 1개의 총 2개로 "apple"을 만들 수 있으므로 필요한 단어 개수의 최솟값은 2를 return 합니다.

    입출력 예 #3
    주어진 단어로는 "banana"를 만들 수 없으므로 -1을 return 합니다.
    * */

    private static final int INF = 20_001;

    public int solution(String[] strs, String t) {
        int n = t.length(); // ❶ 타겟 문자열 t의 길이
        // ❷ 각 위치에서 필요한 최소 조각수를 저장할 배열(초기값은 INF로 함)
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        // ❸ 빈 문자열을 위해 필요한 최소 조각수는 0
        dp[0] = 0;

        // ❹ strs 조각들의 길이를 저장한 해시셋
        HashSet<Integer> sizes = new HashSet<>();
        for (String str : strs) {
            sizes.add(str.length());
        }

        // ❺ dp[i]부터 dp[n]까지 채우기 위한 반복문
        for (int i = 1; i <= n; i++) {
            // ❻ 각 str 조각의 문자열 길이에 대하여
            for (int size : sizes) {
                if (i - size >= 0) {
                    int idx = i;
                    String sub = t.substring(idx - size, idx);
                    // ❼ 이미 구한 해와 strs 조각을 추가해서 문자열을 만들 수 있다면
                    if (Arrays.asList(strs).contains(sub)) {
                        // ❽ 해당 위치의 최소 조각수를 갱신
                        dp[i] = Math.min(dp[i], dp[i - size] + 1);
                    }
                }
            }
        }

        // ❾ 최소 조각수를 반환
        return dp[n] < INF ? dp[n] : -1;
    }

}