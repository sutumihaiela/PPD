import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.BinaryOperator;

public class OperationClass<T> {
    FileUtils<T> fileUtils;

    public OperationClass(){
        fileUtils = new FileUtils<>();
    }

    public void calculeaza(BinaryOperator operator, Class<T> type) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter n : ");
            int n = Integer.parseInt(br.readLine());
            System.out.print("Enter m : ");
            int m = Integer.parseInt(br.readLine());
            System.out.print("Enter p : ");
            int p = Integer.parseInt(br.readLine());

            fileUtils.writeToFile(fileUtils.generateMatrix(n, m, type), "matrice1.txt");
            T[] valuesMatrix1 = fileUtils.readFromFile("matrice1.txt", type);
            fileUtils.writeToFile(fileUtils.generateMatrix(n, m, type), "matrice2.txt");
            T[] valuesMatrix2 = fileUtils.readFromFile("matrice2.txt", type);
            Object[] result = new Object[n * m];
            calculate(n, m, p, valuesMatrix1, valuesMatrix2, result, operator);
            int k = 0;
//            for (int i = 0; i < n * m; i++) {
//                System.out.print(result[i] + " ");
//                k++;
//                if (k == m) {
//                    System.out.println();
//                    k = 0;
//                }
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void calculate(int n, int m, int p, T[] valuesOfMatrix1, T[] valuesOfMatrix2, Object[] result, BinaryOperator operation) throws Exception {

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
}
