import java.util.function.BinaryOperator;

/**
 * Created by Mihaiela Sutu on 10/10/2017.
 */
public class Main<T> {
    static OperationClass<Integer> aClassOfInteger = new OperationClass<>();
    static OperationClass<Double> aClassOfDouble = new OperationClass<>();
    static OperationClass<NumarComplex> aClassOfNumarComplex = new OperationClass<>();


    public static void main(String[] str) throws Exception {

        BinaryOperator<Double> operatorAdunare = (a, b) -> a + b;
        BinaryOperator<Double> operatorInmultire = (a, b) -> a * b;
        BinaryOperator<Double> operatorPunctDouble = (a,b) ->1/(1/a+1/b);

        BinaryOperator<NumarComplex> operatorAdunareNrComplex = (a, b) -> new NumarComplex(a.getReal()+b.getReal(), a.getImag()+b.getImag());
        BinaryOperator<NumarComplex> operatorInmultireNrComplex = (a,b) ->new NumarComplex(a.getReal() * b.getReal() - a.getImag()*b.getImag() ,a.getReal()*b.getImag() +a.getImag()*b.getReal());
        BinaryOperator<NumarComplex> operatorPunctNrComplex = (a, b) -> {
            Integer a1 = a.getReal();
            Integer a2 = b.getReal();
            Integer b1 = a.getImag();
            Integer b2 = b.getImag();
            return new NumarComplex(((a1*a2-b1*b2)*(a1+a2) + (a1*b2+a2*b1)*(b1+b2)),((a1*a2-b1*b2)*(b1+b2) - (a1*b2+a2*b1)*(a1+a2)));
        };

        //aClassOfDouble.calculeaza(operatorInmultire,Double.class);
        ///aClassOfDouble.calculeaza(operatorPunctDouble,Double.class);
        //aClassOfNumarComplex.calculeaza(operatorPunctNrComplex, NumarComplex.class);
        aClassOfNumarComplex.calculeaza(operatorInmultireNrComplex, NumarComplex.class);


    }

}