class Student {

    String name;
    String surname;
    int id;
    int matriculationYear;

    // È buona pratica mettere i campi in testa alla classe

    void build(final String name, final String surname, final int id, final int matriculationYear) {
        /*
         * Completare il corpo del metodo
         */
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.matriculationYear = matriculationYear;
    }

    void printStudentInfo() {
        /*
         * Completare il corpo del metodo
         */
        System.out.println("Sudent [name: " + this.name + ", surname: " + this.surname + ", id: " + this.id
                + ", matriculationYear: " + this.matriculationYear + "]");
    }
}
