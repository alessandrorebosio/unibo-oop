package it.unibo.composition;

import java.util.Arrays;

public class Professor implements User {

    final private int id;
    final private String name;
    final private String surname;
    final private String password;
    private String[] courses;

    public Professor(final int id, final String name, final String surname, final String password,
            final String[] courses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.courses = Arrays.copyOf(courses, courses.length);
    }

    @Override
    public String getUsername() {
        return this.name + "." + this.surname;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getDescription() {
        return this.toString();
    }

    public String[] getCourses() {
        return Arrays.copyOf(this.courses, courses.length);
    }

    public String toString() {
        return "Professor ["
                + "name: " + this.name
                + ", surname: " + this.surname
                + ", id: " + this.id
                + ", courses: " + this.courses + "]";
    }

    public void replaceCourse(final String course, final int index) {
        if (index < this.courses.length) {
            this.courses[index] = course;
        }
    }

    public void replaceAllCourses(final String[] courses) {
        this.courses = Arrays.copyOf(courses, courses.length);
    }
}
