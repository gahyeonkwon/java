package com.example.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class SortSample {

    public static void main(String[] args) {

        Stream<Student2> studentStream = Stream.of(
                new Student2("권가현", 3, 300),
                new Student2("박상아", 1, 400),
                new Student2("김서영", 1, 500),
                new Student2("정승기", 2, 500),
                new Student2("문햇살", 2, 400)

        );

        // 반별로 먼저 정렬된 후에 naturalOrder 실행
        studentStream.sorted(Comparator.comparing(Student2::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

    }

}



class Student2 implements  Comparable<Student2> {
    private String name;
    private int ban;
    private int totalScore;

    public Student2(String name, Integer ban, Integer totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", ban=" + ban +
                ", totalScore=" + totalScore +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getBan() {
        return  ban;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int compareTo(Student2 s) {
        return s.totalScore - this.totalScore;
    }
}
