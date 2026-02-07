package ru.stepup.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentTests {

    @Test
    public void testGetName() {
        Student student = new Student("Peter");
        assertEquals("Peter", student.getName());
    }

    @Test
    public void testSetName() {
        Student student = new Student("Peter");
        student.setName("Vasya");
        assertEquals("Vasya", student.getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void testAddValidGrade(int grade) {
        Student student = new Student("Peter");
        student.addGrade(grade);
        assertEquals(List.of(grade), student.getGrades());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, -1, 100})
    public void testAddInvalidGrade(int grade) {
        Student student = new Student("Peter");
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(grade));
    }

    @Test
    public void testGetGradesReturnsCopy() {
        Student student = new Student("Peter");
        student.addGrade(3);
        List<Integer> grades = student.getGrades();
        grades.add(4);
        assertEquals(List.of(3), student.getGrades());
    }
}
