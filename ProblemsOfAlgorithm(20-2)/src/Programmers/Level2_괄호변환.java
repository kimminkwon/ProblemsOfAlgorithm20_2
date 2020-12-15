package Programmers;

public class Level2_괄호변환 {

    public static void main(String[] args) {
        String p = "()))((()";
        String answer = makeCurrectParenthesis(p);
        System.out.println(answer);
    }

    private static String makeCurrectParenthesis(String p) {
        if(p.equals("") || isCurrect(p)) return p;

        int cutIndex = findCutIndex(p);
        String u = p.substring(0, cutIndex + 1);
        String v = cutIndex == p.length() - 1 ? "" : p.substring(cutIndex + 1, p.length());

        System.out.println(p + "가 문자열일 때, 현재 U: " + u);
        System.out.println(p + "가 문자열일 때, 현재 V: " + v);

        String modifyV = makeCurrectParenthesis(v);
        if(isCurrect(u)) {
            return u + modifyV;
        } else {
            return modifyUAndV(u, modifyV);
        }
    }

    private static String modifyUAndV(String u, String v) {
        String modifyU = "(" + v + ")" + reverseU(u);
        return modifyU;
    }

    private static String reverseU(String u) {
        String reverseU = "";
        for(char c : u.substring(1, u.length() - 1).toCharArray()) {
            if (c == '(') {
                reverseU = reverseU + ")";
            } else {
                reverseU = reverseU + "(";
            }
        }

        return reverseU;
    }

    private static boolean isCurrect(String p) {

        int flag = 0;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') flag++;
            else flag--;

            if(flag < 0) return false;
        }
        return true;
    }

    private static int findCutIndex(String p) {
        int flag = 0; int index = 0;
        for(int i = 0; i < p.length(); i++) {
            index = i;

            if(p.charAt(i) == '(') flag++;
            else flag--;

            if(flag == 0) break;
        }
        return index;
    }

}
