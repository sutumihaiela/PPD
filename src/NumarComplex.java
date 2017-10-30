public class NumarComplex {

    private Integer real;
    private Integer imag;

    public NumarComplex(Integer real, Integer imag) {
        this.real = real;
        this.imag = imag;
    }

    public Integer getReal() {
        return real;
    }

    public Integer getImag() {
        return imag;
    }

    public void setReal(Integer real) {
        this.real = real;
    }

    public void setImag(Integer imag) {
        this.imag = imag;
    }

    @Override
    public String toString() {
        return real + "+" + imag + "i";
    }
}
