class ComplexNumber {
    /*
     * Inserire qui la dichiarazione dei campi della classe
     */
    double re;
    double im;

    void build(double re, double im) {
        /*
         * Inserire qui l'inizializzazione dei campi con i parametri forniti in input
         */
        this.re = re;
        this.im = im;
    }

    boolean equal(ComplexNumber num) {
        /*
         * Implementare il metodo in modo che restituisca true se e solo se il
         * numero complesso è uguale al parametro num passato in input
         */
        return this.re == num.re && this.im == num.im;
    }

    void add(ComplexNumber num) {
        /*
         * Implementare il metodo in modo che venga aggiunto il numero complesso
         * passato in input - Rif. Appendice A1 (slide)
         */
        this.re += num.re;
        this.im += num.im;
    }

    String toStringRep() {
        /*
         * Implementare il metodo in modo che restituisca una rappresentazione
         * testuale del numero complesso
         */
        return im == 0
                ? re + ""
                : (re == 0 ? "" : re)
                        + (re != 0 && im > 0 ? "+" : "")
                        + (im == 1 ? "" : (im == -1 ? "-" : im + "")) + "i";
    }
}