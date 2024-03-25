package com.example.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectSample  {

    public static void main(String[] args) {


        Student [] students =  {   new Student("나자바", true, 1, 1, 300),
        new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true, 1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true, 1, 2, 100),
                new Student("안지미", false, 1, 2, 50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true, 1, 3, 200),

                new Student("나자바", true, 2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true, 2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true, 2, 2, 100),
                new Student("안지미", false, 2, 2, 50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true, 2, 3, 200) };

        //Stream 생성
        Stream<Student> studentStream = Stream.of(students);
        Stream<Student> studentStream2 =  Stream.of(students);

        //  ===================== partitioningBy 예제 ======================
        // partitioningBy 의 원형
        // public static <T>
        //    Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate) {
        //        return partitioningBy(predicate, toList());
        //    }
        // *** 기본 분할
        Map<Boolean, List<Student>> studBySex = studentStream.collect(partitioningBy(Student::isMale));
        //Map<Boolean, List<Student>> studBySex2 = studentStream2.collect(partitioningBy(s -> s.isMale()));
        Map<Boolean, List<Student>> studBySex3 = studentStream2.collect(partitioningBy(Student::isMale, toList()));

        List<Student> maleStudent = studBySex.get(true);
        List<Student> femaleStudent = studBySex.get(false);


        Stream<Student> studentStream3 =  Stream.of(students);

        // *** 기본 분할 + 통계정보 ( counting() )
        Map<Boolean, Long> stuNumBySex = studentStream3.collect(partitioningBy(Student::isMale, counting()));


        Stream<Student> studentStream4 =  Stream.of(students);
        Map<Boolean, Optional<Student>> topScoreBySex = studentStream4.collect(partitioningBy(Student::isMale,
                maxBy(Comparator.comparingInt(Student::getScore))));

        System.out.println("남학생 1등 :" + topScoreBySex.get(true));
        System.out.println("여학생 1등 :" + topScoreBySex.get(false));

        // Optional<T> 가 아니라 T 로 받는 방법
        Stream<Student> studentStream5 =  Stream.of(students);
        Map<Boolean, Student> topScoreBySex2 = studentStream5.collect(partitioningBy(Student::isMale,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Student::getScore)), Optional::get
                        )));


        System.out.println("남학생 1등 :" + topScoreBySex.get(true));
        System.out.println("여학생 1등 :" + topScoreBySex.get(false));


        // 다중분할 ( 성별 불합격자 + 100점 이하 )
        Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex = Stream.of(students)
                .collect(partitioningBy(Student::isMale, partitioningBy(s -> s.getScore() <= 100)));
        List<Student> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student> failedFeMaleStu = failedStuBySex.get(false).get(true);

        failedMaleStu.stream().forEach(System.out::println);
        failedFeMaleStu.stream().forEach(System.out::println);


        //  ===================== groupingBy 예제 ======================
//      ** groupingBy 의 원형
//        public static <T, K, A, D>
//                Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
//                Collector<? super T, A, D> downstream) {
//            return groupingBy(classifier, HashMap::new, downstream);
        // ** 기본 그룹화
        Map<Integer, List<Student>> stuByBan = Stream.of(students).collect(groupingBy(Student::getBan, toList()));

        // toList 는 생략 가능하다
        Map<Integer, List<Student>> stuByBan2 = Stream.of(students).collect(groupingBy(Student::getBan));

        for(List<Student> ban : stuByBan.values()) {
             ban.stream().forEach(System.out::println);
        }

        // ** 성적 등급으로 그룹화 해보기
        Map<Student.Level, List<Student>> stuByLevel = Stream.of(students).collect(groupingBy(s -> {
                if(s.getScore() >= 200) return Student.Level.HIGH;
                else if(s.getScore() >= 100) return Student.Level.MID;
                else return Student.Level.LOW;
        }));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student.Level key : keySet) {
            System.out.println(" k ->" + key);
            stuByLevel.get(key).stream().forEach(System.out::println);
        }

        // ** 성적 그릅으로 그룹화 + 통계 함수 추가 ( counting )
        Map<Student.Level, Long> stuCntByLevel = Stream.of(students)
                .collect(groupingBy(s -> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }, counting())
                );

        for(Student.Level key : keySet) {
            System.out.println(" k ->" + key);
            System.out.println(" key.value -> "  + stuCntByLevel.get(key));
        }


        // ** 다중 그룹화 - 성적별, 반별
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan = Stream.of(students)
                .collect(groupingBy(Student::getHak, groupingBy(Student::getBan)));

        for(Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
             for(List<Student> ban : hak.values()) {
                 System.out.println("hak -> " + hak);
                 ban.stream().forEach(System.out::println);
             }
        }



        //** 다중그룹화 - 성적별, 반별 + 통계 ( 학년별, 반별 1등 )
        Map<Integer, Map<Integer, Student>> topStuByHakAndBan =
                Stream.of(students)
                        .collect(groupingBy(Student::getHak,
                                    groupingBy(Student::getBan,
                                        collectingAndThen(
                                                    maxBy(Comparator.comparingInt(Student::getScore))
                                                , Optional::get
                                        )))
                        );

        for(Map<Integer, Student> ban : topStuByHakAndBan.values() ) {
            for(Student s : ban.values()) {
                System.out.println(s);
            }
        }


        //** 다중그룹화 + 통계 ( 학년별, 반별 성적 그룹 )
        Map<String, Set<Student.Level>> stuByScoreGroup = Stream.of(students)
                .collect(groupingBy(s -> s.getHak() + "-" + s.getBan(),
                        mapping(s -> {
                            if(s.getScore() >= 200) return Student.Level.HIGH;
                           else if(s.getScore() >= 100) return Student.Level.MID;
                            else return Student.Level.LOW;
                        }, toSet())));

        Set<String> keySet2 = stuByScoreGroup.keySet();

        for(String key : keySet2) {
            System.out.println("key ->" + key + ", key.value ->" + stuByScoreGroup.get(key)) ;
        }

    }




}
