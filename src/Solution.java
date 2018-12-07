public class Solution {
    public void iteration(double grid[][], double yy, double e, double[] transition) {
        int[] directionX = {1,-1,0,0};
        int[] directionY = {0,0,1,-1};
        int loop = 0;
        double diff = 10;
        double[][] copyGrid = new double[grid.length][grid[0].length];
        double[][] reward = new double[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++){
                copyGrid[i][j] = grid[i][j];
                reward[i][j] = grid[i][j];
            }
        }
        while(diff > e * (1 - yy) * yy){
            loop++;
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 1 || grid[i][j] == -1 || grid[i][j] == 0)
                        continue;
                    double currVal = reward[i][j];
                    double max = -100;
                    for(int k = 0; k < directionX.length; k++) {
                        double sum = 0;
                        int x = i + directionX[k];
                        int y = j + directionY[k];
                        if(isHitWall(grid,x,y))
                            sum = sum + grid[i][j] * transition[0];
                        else
                            sum = sum + grid[x][y] * transition[0];
                        if(x == i){
                            int x1 = i + y - j;
                            int y1 = j;
                            int x2 = i;
                            int y2 = j * 2 - y;
                            int x3 = i * 2 - x1;
                            int y3 = j;
                            if(isHitWall(grid,x1,y1)){
                                sum = sum + grid[i][j] * transition[1];
                            }
                            else{
                                sum = sum + grid[x1][y1] * transition[1];
                            }
                            if(isHitWall(grid,x2,y2)){
                                sum = sum + grid[i][j] * transition[2];
                            }
                            else{
                                sum = sum + grid[x2][y2] * transition[2];
                            }
                            if(isHitWall(grid,x3,y3)){
                                sum = sum + grid[i][j] * transition[3];
                            }
                            else{
                                sum = sum + grid[x3][y3] * transition[3];
                            }

                        }
                        else if(y == j){
                            int x1 = i;
                            int y1 = i - x + y;
                            int x2 = i * 2 - x;
                            int y2 = j;
                            int x3 = i;
                            int y3 = j * 2 - y1;
                            if(isHitWall(grid,x1,y1)) {
                                sum = sum + grid[i][j] * transition[1];
                            }
                            else {
                                sum = sum + grid[x1][y1] * transition[1];
                            }
                            if(isHitWall(grid,x2,y2)) {
                                sum = sum + grid[i][j] * transition[2];
                            }
                            else {
                                sum = sum + grid[x2][y2] * transition[2];
                            }
                            if(isHitWall(grid,x3,y3)) {
                                sum = sum + grid[i][j] * transition[3];
                            }
                            else {
                                sum = sum + grid[x3][y3] * transition[3];
                            }
                        }
                        max = Math.max(max,sum);
                    }
                    diff = max + yy * currVal - grid[i][j];
                    grid[i][j] = max + yy * currVal;
                }
            }
            //   for(int i = 0; i < grid.length; i++) {
            //     for(int j = 0; j < grid[0].length; j++){
            //         grid[i][j] = copyGrid[i][j];
            //     }
            // }
            System.out.println("Iteration "+ loop);
            for(int i = 0 ; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    System.out.print(grid[i][j]);
                    System.out.print('\t');
                }
                System.out.print('\n');
            }
            System.out.print('\n');
        }
    }
    public boolean isHitWall(double grid[][], int x, int y){
        if(x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1)
            return true;
        if(grid[x][y] == 0)
            return true;
        return false;
    }
}