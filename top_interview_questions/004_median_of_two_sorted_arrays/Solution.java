// https://leetcode.com/problems/median-of-two-sorted-arrays/description/

/* 

A [1 2 3 4]
B [1 2 3 4 5 6 7 8]  (LenA <= LenB)

A + B [1 1 2 2 3 (3 4) 4 5 6 7 8]
median = (3 + 4) / 2 = 3.5

totalNum = 12
halfNum = 6

--------------------------------------------

step1.

LenLeftA = (LenA + 1) / 2 = 2
LenLeftB = halfNum - LenLeftA = 6 - 2 = 4

A [1 2 | 3 4]
B [1 2 3 4 | 5 6 7 8]

max(leftA) <= min(rightB) -> true
max(leftB) <= min(rightA) -> false

--------------------------------------------

step2.

LenLeftA = LenLeftA + 1 = 3
LenLeftB = halfNum - LenLeftA = 6 - 3 = 3

A [1 2 3 | 4]
B [1 2 3 | 4 5 6 7 8]

max(leftA) <= min(rightB) -> true
max(leftB) <= min(rightA) -> true

median = (max(max(leftA), max(leftB)) + min(min(rightA), min(rightB))) / 2

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int max = (int)Math.pow(10, 6);
        final int min = -(int)Math.pow(10, 6);

        final int len1 = nums1.length;
        final int len2 = nums2.length;

        // nums1の方が配列の長さが短くなるようにする
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        final int totalLen = len1 + len2;
      
        int midIdx1 = len1 == 0 ? -1 : (len1 - 1) / 2;

        while (true) {
            int midIdx2 = totalLen / 2 - midIdx1 - 2;  // totalLen / 2 - (midIdx1 + 1) - 1

            int midLeft1 = midIdx1 >= 0 ? nums1[midIdx1] : min;
            int midRight1 = midIdx1 + 1 < len1 ? nums1[midIdx1 + 1] : max;
            int midLeft2 = midIdx2 >= 0 ? nums2[midIdx2] : min;
            int midRight2 = midIdx2 + 1 < len2 ? nums2[midIdx2 + 1] : max;

            if (midLeft1 <= midRight2 && midLeft2 <= midRight1) {
                if(totalLen % 2 != 0) {
                    return (double)Math.min(midRight1, midRight2);
                }
                return (Math.max(midLeft1, midLeft2) + Math.min(midRight1, midRight2)) / 2.0;
            }
            if (midLeft1 > midRight2) {
                midIdx1--;
            }
            if (midLeft2 > midRight1) {
                midIdx1++;
            }
        }
    }
}
