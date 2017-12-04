class Solution {
    public int calculateMinimumHP(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1){
            return 1;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = Math.max(1 - matrix[0][0], 1);
        int x = matrix.length - 1;
        int y = matrix[0].length - 1;

        for (int i = matrix.length - 2; i >= 0; i--){
            dp[i][y] = Math.max(1 - matrix[i + 1][y], 1);
        }
        for (int i = matrix[x].length - 2; i >= 0; i--) {
            dp[x][i] = Math.max(1 - matrix[x][i + 1], 1);
        }

        int up = 0;
        int right = 0;
        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                up = Math.max(dp[i + 1][j] - matrix[i][j], 1);
                right = Math.max(dp[i][j + 1] - matrix[i][j], 1);
                dp[i][j] = Math.min(up, right);
            }
        }
        return  dp[0][0];
    }
}