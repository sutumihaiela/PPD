import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class FileUtils {

    public FileUtils(){
    }

    public static int[][] generateMatrix(Integer n, Integer m) {
        int[][] matrix = new int[n][m];
        Random randomGenerator = new Random();

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int randomInt = randomGenerator.nextInt(10);
                matrix[j][i] = randomInt;
            }
        }
        return matrix;
    }

    public static void writeToFile(int matrix[][], String filename) throws Exception {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(matrix.length);
            writer.print(" ");
            writer.print(matrix[1].length);
            writer.println();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {

                    writer.print(matrix[i][j]);
                    writer.print(" ");
                }
                writer.println();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] readAsMatrixFromFile(String filename) throws Exception {

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            sCurrentLine = br.readLine();
            String[] dim = sCurrentLine.split(" ");
            int n = Integer.parseInt(dim[0]);
            int m = Integer.parseInt(dim[1]);
            int[][] data = new int[n][m];
            int k = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(" ");
                for (int i = 0; i < values.length; i++) {
                    data[k][i] = Integer.parseInt(values[i]);
                }
                k++;
            }
            return data;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }


    public static Integer[] readFromFile(String filename) throws Exception {

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            sCurrentLine = br.readLine();
            String[] dim = sCurrentLine.split(" ");
            int n = Integer.parseInt(dim[0]);
            int m = Integer.parseInt(dim[1]);
            Integer[] data = new Integer[n * m];
            int k = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(" ");
                for (int i = 0; i < values.length; i++) {
                    data[k * m + i] = Integer.parseInt(values[i]);
                }
                k++;
            }
            return data;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }

    public static NumarComplex[] readFromFileNrComplexe(String filename) throws Exception {

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            sCurrentLine = br.readLine();
            String[] dim = sCurrentLine.split(" ");
            int n = Integer.parseInt(dim[0]);
            int m = Integer.parseInt(dim[1]);
            NumarComplex[] data = new NumarComplex[n * m];
            int k = 0;
            String [] realAndImag;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(" ");
                for (int i = 0; i < values.length; i++) {
                    realAndImag = values[i].split("\\+");
                    data[k * m + i] = new NumarComplex(Integer.parseInt(realAndImag[0]),Integer.parseInt(realAndImag[1]));
                }
                k++;
            }
            return data;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }
}
