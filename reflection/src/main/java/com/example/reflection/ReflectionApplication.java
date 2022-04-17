package com.example.reflection;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.reflect.Field;
import java.util.Arrays;

@SpringBootApplication
public class ReflectionApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 클래스 객체 가져오기
         */
        // 방법 1. 클래스 타입
        Class<Book> bookClass = Book.class;
        
        // 방법 2. 인스턴스의 .getClass()
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 방법 3. 문자열
        Class<?> aClass1 = Class.forName("com.example.reflection.Book");

        // 정리. 타입을 통핼 때는 .class , 인스턴스를 통할 때는 getClass()
        /**
         * Class 객체 활용
         */
        // public 한 필드 가져오기
        Field[] fields = bookClass.getFields();
        Arrays.stream(fields).forEach(System.out::println);
        System.out.println("=============================");
        // 모든 필드 다 가져오기
        Field[] declaredFields = bookClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(System.out::println);



    }

}
