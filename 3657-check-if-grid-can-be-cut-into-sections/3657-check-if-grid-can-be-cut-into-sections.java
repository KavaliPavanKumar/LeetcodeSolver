import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        return checkVertical(rectangles) || checkHorizontal(rectangles);
    }

    private boolean checkVertical(int[][] rectangles) {
        // Sort rectangles by startx
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[0]));

        // Precompute prefix max of endx
        int[] prefixMaxEndX = new int[rectangles.length];
        prefixMaxEndX[0] = rectangles[0][2];
        for (int i = 1; i < rectangles.length; i++) {
            prefixMaxEndX[i] = Math.max(prefixMaxEndX[i - 1], rectangles[i][2]);
        }

        // Precompute suffix min of startx
        int[] suffixMinStartX = new int[rectangles.length];
        suffixMinStartX[rectangles.length - 1] = rectangles[rectangles.length - 1][0];
        for (int i = rectangles.length - 2; i >= 0; i--) {
            suffixMinStartX[i] = Math.min(suffixMinStartX[i + 1], rectangles[i][0]);
        }

        // Iterate to find possible x1 and x2
        for (int i = 0; i < rectangles.length - 2; i++) {
            int x1 = prefixMaxEndX[i];
            if (suffixMinStartX[i + 1] < x1) {
                continue;
            }

            // Now, find x2 in the remaining rectangles
            int[] middlePrefixMaxEndX = new int[rectangles.length - (i + 1)];
            middlePrefixMaxEndX[0] = rectangles[i + 1][2];
            for (int j = 1; j < middlePrefixMaxEndX.length; j++) {
                middlePrefixMaxEndX[j] = Math.max(middlePrefixMaxEndX[j - 1], rectangles[i + 1 + j][2]);
            }

            int[] middleSuffixMinStartX = new int[rectangles.length - (i + 1)];
            middleSuffixMinStartX[middleSuffixMinStartX.length - 1] = rectangles[rectangles.length - 1][0];
            for (int j = middleSuffixMinStartX.length - 2; j >= 0; j--) {
                middleSuffixMinStartX[j] = Math.min(middleSuffixMinStartX[j + 1], rectangles[i + 1 + j][0]);
            }

            for (int j = 0; j < middlePrefixMaxEndX.length - 1; j++) {
                int x2 = middlePrefixMaxEndX[j];
                if (middleSuffixMinStartX[j + 1] >= x2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal(int[][] rectangles) {
        // Sort rectangles by starty
        Arrays.sort(rectangles, Comparator.comparingInt(a -> a[1]));

        // Precompute prefix max of endy
        int[] prefixMaxEndY = new int[rectangles.length];
        prefixMaxEndY[0] = rectangles[0][3];
        for (int i = 1; i < rectangles.length; i++) {
            prefixMaxEndY[i] = Math.max(prefixMaxEndY[i - 1], rectangles[i][3]);
        }

        // Precompute suffix min of starty
        int[] suffixMinStartY = new int[rectangles.length];
        suffixMinStartY[rectangles.length - 1] = rectangles[rectangles.length - 1][1];
        for (int i = rectangles.length - 2; i >= 0; i--) {
            suffixMinStartY[i] = Math.min(suffixMinStartY[i + 1], rectangles[i][1]);
        }

        // Iterate to find possible y1 and y2
        for (int i = 0; i < rectangles.length - 2; i++) {
            int y1 = prefixMaxEndY[i];
            if (suffixMinStartY[i + 1] < y1) {
                continue;
            }

            // Now, find y2 in the remaining rectangles
            int[] middlePrefixMaxEndY = new int[rectangles.length - (i + 1)];
            middlePrefixMaxEndY[0] = rectangles[i + 1][3];
            for (int j = 1; j < middlePrefixMaxEndY.length; j++) {
                middlePrefixMaxEndY[j] = Math.max(middlePrefixMaxEndY[j - 1], rectangles[i + 1 + j][3]);
            }

            int[] middleSuffixMinStartY = new int[rectangles.length - (i + 1)];
            middleSuffixMinStartY[middleSuffixMinStartY.length - 1] = rectangles[rectangles.length - 1][1];
            for (int j = middleSuffixMinStartY.length - 2; j >= 0; j--) {
                middleSuffixMinStartY[j] = Math.min(middleSuffixMinStartY[j + 1], rectangles[i + 1 + j][1]);
            }

            for (int j = 0; j < middlePrefixMaxEndY.length - 1; j++) {
                int y2 = middlePrefixMaxEndY[j];
                if (middleSuffixMinStartY[j + 1] >= y2) {
                    return true;
                }
            }
        }
        return false;
    }
}