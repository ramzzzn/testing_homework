package ru.stepup.testing;

public class StudentRepoMock implements StudentRepo {

    @Override
    public boolean checkGrade(int grade) {
        return grade >= 2 && grade <= 5;
    }

    @Override
    public int getRatingForGradeSum(int sum) {
        if (sum > 50) return 9;
        return 10;
    }
}
