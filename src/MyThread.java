import java.util.function.BinaryOperator;

/**
 * Created by Mihaiela Sutu on 10/10/2017.
 */
public class MyThread<T> extends Thread {
    private int iStart;
    private int iStop;
    private T[] matrice1;
    private T[] matrice2;
    private T[] matriceRezultat;
    private BinaryOperator operator;


    public MyThread(int iStart, int iStop, T[] matrice1, T[] matrice2, T[] matrice3, BinaryOperator operator) {
        super();
        this.iStart = iStart;
        this.iStop = iStop;
        this.matrice1 = matrice1;
        this.matrice2 = matrice2;
        this.matriceRezultat=matrice3;
        this.operator = operator;
    }

    @Override
    public void run() {
        for(int i = iStart; i<= iStop; i++){
            this.matriceRezultat[i] = ((T) operator.apply(matrice1[i], matrice2[i]));
        }
    }

    public T[] getMatriceRezultat() {
        return matriceRezultat;
    }
}
