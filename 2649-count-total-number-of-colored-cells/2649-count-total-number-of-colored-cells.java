class Solution {
    public long coloredCells(int n) {
        long N = (long) n; // Convert to long before multiplication
        return N * N + (N - 1) * (N - 1);
    }
}
