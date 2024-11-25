package it.unibo.constructors;

class Student {

    String name;
    String surname;
    int id;
    int matriculationYear;

    Student(final int id, final String name, final String surname, final int matriculationYear) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.matriculationYear = matriculationYear;
    }

    void printStudentInfo() {
        System.out.println("Sudent [name: " + this.name + ", surname: " + this.surname + ", id: " + this.id
                + ", matriculationYear: " + this.matriculationYear + "]");
    }
}
