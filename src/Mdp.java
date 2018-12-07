import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
public class Mdp{
    public static void main(String[] args) {
        try {
            String pathname = "input";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            System.out.println(line);
            String stringArray[] = line.split(",");
            System.out.println(stringArray[0]);
            int row = Integer.parseInt(stringArray[0]);
            int colum = Integer.parseInt(stringArray[1]);
            double[][] input = new double[row][colum];
            System.out.println(row);
            int index = 2;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < colum; j++) {
                    input[i][j] = Double.parseDouble(stringArray[index++]);
                }
            }
            double gamma = Double.parseDouble(stringArray[index++]);
            double epsilon = Double.parseDouble(stringArray[index++]);
            double[] transition = new double[4];
            for (int i = 0; i < 4; i++) {
                transition[i] = Double.parseDouble(stringArray[index++]);
            }
            Solution solution = new Solution();
            solution.iteration(input, gamma, epsilon, transition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
