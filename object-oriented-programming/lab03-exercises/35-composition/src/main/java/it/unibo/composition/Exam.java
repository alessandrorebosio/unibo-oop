package it.unibo.composition;

import java.util.Arrays;

public class Exam {
    private final int id;
    private final int maxStudents;
    private int registeredStudents;
    private final String courseName;
    private final Professor professor;
    private final ExamRoom room;
    private final Student[] students;

    public Exam(final int id, final int maxStudents, final String courseName, final Professor professor,
            final ExamRoom room) {
        this.id = id;
        this.maxStudents = maxStudents;
        this.courseName = courseName;
        this.professor = professor;
        this.room = room;
        this.students = new Student[maxStudents];
    }

    public void registerStudent(Student student) {
        if (this.registeredStudents < this.students.length) {
            this.students[this.registeredStudents++] = student;
        }
    }

    public String toString() {
        return "Exam ["
                + "id: " + this.id
                + ", maxStudents: " + this.maxStudents
                + ", registeredStudents: " + this.registeredStudents
                + ", courseName: " + this.courseName
                + ", students: " + Arrays.toString(this.students)
                + ", professor: " + this.professor
                + ", room: " + this.room.toString() + "]";
    }
}
