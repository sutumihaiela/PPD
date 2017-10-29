import java.io.*;
import java.util.function.BinaryOperator;

/**
 * Created by Mihaiela Sutu on 10/10/2017.
 */
public class Main {
    static FileUtils fileUtils = new FileUtils();

    public static void main(String[] str) throws Exception {

        BinaryOperator<Integer> operatorAdunare = (a, b) -> a + b;
        BinaryOperator<Integer> operatorInmultire = (a, b) -> a * b;
        BinaryOperator<NumarComplex> operatorAdunareNrComplex = (a, b) -> new NumarComplex(a.getReal()+b.getReal(), a.getImag()+b.getImag());
        BinaryOperator<Double> doubleBinaryOperator = (a,b) ->1/(1/a+1/b);

        //aduna(operatorAdunare);
        //aduna(operatorInmultire);
        operatiePeNumereComplexe(operatorAdunareNrComplex);
    }

    private static void aduna(BinaryOperator operator) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter n : ");
            int n = Integer.parseInt(br.readLine());
            System.out.print("Enter m : ");
            int m = Integer.parseInt(br.readLine());
            System.out.print("Enter p : ");
            int p = Integer.parseInt(br.readLine());

            fileUtils.writeToFile(fileUtils.generateMatrix(n, m), "matrice1.txt");
            Integer[] valuesMatrix1 = fileUtils.readFromFile("matrice1.txt");
            fileUtils.writeToFile(fileUtils.generateMatrix(n, m), "matrice2.txt");
            Integer[] valuesMatrix2 = fileUtils.readFromFile("matrice2.txt");
            Object[] result = new Object[n * m];
            calculate(n, m, p, valuesMatrix1, valuesMatrix2, result, operator);
            int k = 0;
            for (int i = 0; i < n * m; i++) {
                System.out.print(result[i] + " ");
                k++;
                if (k == m) {
                    System.out.println();
                    k = 0;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void calculate(int n, int m, int p, Integer[] valuesOfMatrix1, Integer[] valuesOfMatrix2, Object[] result, BinaryOperator operation) throws Exception {

        int nrElemPerThread = n * m / p;
        MyThread[] threads = new MyThread[p];
        int rest = n * m % p;
        int iStart = 0;
        int iStop = nrElemPerThread - 1;

        if (rest > 0) {
            iStop = nrElemPerThread;
            rest--;
        }

        for (int i = 0; i < p; i++) {
            threads[i] = new MyThread(iStart, iStop, valuesOfMatrix1, valuesOfMatrix2, result, operation);
            iStart = iStop + 1;
            if (rest > 0) {
                iStop = iStart + nrElemPerThread;
                rest--;
            } else {
                iStop = iStart + nrElemPerThread - 1;
            }
        }

        long start = System.currentTimeMillis();
        for (int j = 0; j < p; j++) {
            threads[j].start();
        }
        for (int i = 0; i < p; i++) {
            threads[i].join();
        }
        long stop = System.currentTimeMillis();
        System.out.println("TIMP TOTAL: " + (stop - start));
    }

    private static void operatiePeNumereComplexe(BinaryOperator operator) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter p : ");
            int p = Integer.parseInt(br.readLine());
            NumarComplex[] valuesMatrix1 = fileUtils.readFromFileNrComplexe("matrice1NrComplexe");
            NumarComplex[] valuesMatrix2 = fileUtils.readFromFileNrComplexe("matrice2NrComplexe");

            int n = 2;
            int m = 2;

            Object[] result = new Object[n * m];
            //calculate(n, m, p, valuesMatrix1, valuesMatrix2, result, operator);
            int k = 0;
            for (int i = 0; i < n * m; i++) {
                System.out.print(result[i] + " ");
                k++;
                if (k == m) {
                    System.out.println();
                    k = 0;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
