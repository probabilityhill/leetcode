// https://leetcode.com/problems/longest-substring-without-repeating-characters/

/*

    p w w k e w    map                    bgnIdx  endIdx  maxLen
    _              (p:0)                  0       0       1
    ___            (p:0, w:1)             0       1       2
        _          (p:0, w:2)             2       2       2
        ___        (p:0, w:2, k:3)        2       3       2
        _____      (p:0, w:2, k:3, e:4)   2       4       3
          _____    (p:0, w:5, k:3, e:4)   3       5       3
 
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {

        if (s.length() == 0) {
            return 0;
        }

        // 各文字とその場所のマップ
        HashMap<Character, Integer> map = new HashMap<>();

        int maxLen = 0;
        for (int endIdx = 0, bgnIdx = 0; endIdx < s.length(); endIdx++){
            char currChar = s.charAt(endIdx);
            // 現在の文字がマップに含まれている場合
            if (map.containsKey(currChar)){
                // マップから該当文字の場所を取得
                int dupCharIdx = map.get(currChar);
                // 開始位置を更新
                bgnIdx = Math.max(bgnIdx, dupCharIdx + 1);
            }
            // マップに現在地を登録
            map.put(currChar, endIdx);
            // 最大長の更新
            maxLen = Math.max(maxLen, endIdx - bgnIdx + 1);
        }
        return maxLen;
    }
}
