package ru.stepup.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentTests {

    @Test
    public void testGetName() {
        Student student = new Student("Peter");
        assertEquals("Peter", student.getName(), "Имя студента не получено");
    }

    @Test
    public void testSetName() {
        Student student = new Student("Peter");
        student.setName("Vasya");
        assertEquals("Vasya", student.getName(), "Имя студента не отредактировано");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void testAddValidGrade(int grade) {
        Student student = new Student("Peter");
        StudentRepo repo = Mockito.mock(StudentRepo.class);
        student.setRepo(repo);
        Mockito.when(repo.checkGrade(grade)).thenReturn(true);
        student.addGrade(grade);
        assertEquals(List.of(grade), student.getGrades(), "Оценка не добавлена в список оценок");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, -1, 100})
    public void testAddInvalidGrade(int invalidGrade) {
        Student student = new Student("Peter");
        StudentRepo repo = Mockito.mock(StudentRepo.class);
        student.setRepo(repo);
        Mockito.when(repo.checkGrade(invalidGrade)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> student.addGrade(invalidGrade), "В список оценок была добавлена невалидная оценка:" + invalidGrade);
    }

    @Test
    public void testGetGradesReturnsCopy() {
        Student student = new Student("Peter");
        StudentRepo repo = Mockito.mock(StudentRepo.class);
        student.setRepo(repo);
        Mockito.when(repo.checkGrade(3)).thenReturn(true);
        student.addGrade(3);
        List<Integer> grades = student.getGrades();
        grades.add(4);
        assertEquals(List.of(3), student.getGrades(), "Метод getGrades() возвращает оригинал объекта");
    }
}