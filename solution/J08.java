import java.util.ArrayDeque;

class J08 {

    public static void main(String[] args) {
        System.out.println(solution3("()()"));
        System.out.println(solution3("(())()"));
        System.out.println(solution3("(())())"));
    }

    private static boolean solution(String s) {
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

    private static boolean solution2(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] a = s.toCharArray();
        for(char c : a) {
//            System.out.println(stack);
            if(c == '('){
                stack.push(c);
            }
            else {
                if(stack.isEmpty() || stack.pop() == ')'){
                    return false;
                }
//                if (stack.isEmpty()) {
//                    return false;
//                }
//                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static boolean solution3(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        char[] a = s.toCharArray();
        for(char c:a) {
            if(c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}