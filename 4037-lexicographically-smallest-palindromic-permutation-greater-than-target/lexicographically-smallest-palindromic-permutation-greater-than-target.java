class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) return "";
        
        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }
        
        String firstHalf = buildHalf(halfCount);
        String palindrome = buildPalindrome(firstHalf, oddChar);
        
        if (palindrome.compareTo(target) > 0) return palindrome;
        
        char[] targetHalf = target.substring(0, halfLen).toCharArray();
        char[] result = new char[halfLen];
        
        if (findResult(0, result, halfCount, targetHalf, oddChar, target, halfLen)) {
            return buildPalindrome(new String(result), oddChar);
        }
        
        return "";
    }
    
    private boolean findResult(int pos, char[] result, int[] available, char[] targetHalf, int oddChar, String target, int halfLen) {
        if (pos == halfLen) {
            String palindrome = buildPalindrome(new String(result), oddChar);
            return palindrome.compareTo(target) > 0;
        }
        
        for (int c = (pos < targetHalf.length ? targetHalf[pos] - 'a' : 0); c < 26; c++) {
            if (available[c] > 0) {
                available[c]--;
                result[pos] = (char)('a' + c);
                
                if (pos < targetHalf.length && c > targetHalf[pos] - 'a') {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i <= pos; i++) {
                        sb.append(result[i]);
                    }
                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < available[i]; j++) {
                            sb.append((char)('a' + i));
                        }
                    }
                    String candidateHalf = sb.toString();
                    String palindrome = buildPalindrome(candidateHalf, oddChar);
                    if (palindrome.compareTo(target) > 0) {
                        for (int i = 0; i < halfLen; i++) {
                            result[i] = candidateHalf.charAt(i);
                        }
                        return true;
                    }
                    available[c]++;
                    return false;
                }
                
                if (findResult(pos + 1, result, available, targetHalf, oddChar, target, halfLen)) {
                    return true;
                }
                
                available[c]++;
            }
        }
        return false;
    }
    
    private String buildHalf(int[] count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
    
    private String buildPalindrome(String half, int oddChar) {
        StringBuilder sb = new StringBuilder(half);
        if (oddChar != -1) sb.append((char)('a' + oddChar));
        sb.append(new StringBuilder(half).reverse().toString());
        return sb.toString();
    }
}