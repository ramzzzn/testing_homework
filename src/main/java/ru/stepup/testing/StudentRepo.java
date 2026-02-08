package ru.stepup.testing;

public interface StudentRepo {
    boolean checkGrade(int grade);

    int getRatingForGradeSum(int sumGrade);
}
