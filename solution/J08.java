import java.util.ArrayDeque;

class J08 {

    private boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] a = s.toCharArray();
        for (char c : a) {
            if (c == '(') {
                stack.push(c);
            }
            else {
                if(stack.isEmpty() || stack.pop() == c)
                    return false;
            }
        }

        return stack.isEmpty();
    }

}