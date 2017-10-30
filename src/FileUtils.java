import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class FileUtils<T> {

    public FileUtils() {
    }

    public T[][] generateMatrix(Integer n, Integer m, Class<T> tClass) throws Exception {
        T[][] matrix = (T[][]) new Object[n][m];
        Random randomGenerator = new Random();

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (tClass.isAssignableFrom(Integer.class)) {
                    Integer randomInt = randomGenerator.nextInt(10);
                    matrix[j][i] = ((T) randomInt);
                } else if (tClass.isAssignableFrom(Double.class)) {
                    Double randomDouble = randomGenerator.nextDouble();
                    matrix[j][i] = ((T) randomDouble);
                }else if (tClass.isAssignableFrom(NumarComplex.class)) {
                    Integer randomReal = randomGenerator.nextInt(10);
                    Integer randomImag = randomGenerator.nextInt(10);
                    matrix[j][i] = ((T) new NumarComplex(randomReal,randomImag));
                }
            }
        }
        return matrix;
    }

    public void writeToFile(T matrix[][], String filename) throws Exception {
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

    public T[] readFromFile(String filename, Class<T> tClass) throws Exception {

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
            T[] data = (T[]) new Object[n * m];
            int k = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(" ");
                for (int i = 0; i < values.length; i++) {
                    if (tClass.isAssignableFrom(Integer.class)) {
                        data[k * m + i] = (T) ((Integer) Integer.parseInt(values[i]));
                    } else if (tClass.isAssignableFrom(Double.class)) {
                        data[k * m + i] = (T) ((Double) Double.parseDouble(values[i]));
                    }else if (tClass.isAssignableFrom(NumarComplex.class)) {
                        String[] realAndImag = values[i].split("\\+");
                        data[k * m + i] = (T) ( new NumarComplex(Integer.parseInt(realAndImag[0]), Integer.parseInt(realAndImag[1].substring(0,realAndImag[1].length()-1))));
                    }
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
