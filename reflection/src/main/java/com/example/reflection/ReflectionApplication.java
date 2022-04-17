package com.example.reflection;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Arrays;

@SpringBootApplication
public class ReflectionApplication {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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

        /**
         * 어노테이션과 리플렉션
         */
        Book book1 = new Book();
        System.out.println("Book 클래스의 어노테이션 출력");
        Annotation[] annotations = book1.getClass().getAnnotations();
        Arrays.stream(annotations).forEach(System.out::println); // RetentionPolicy 설정에 따라 출력될수도 아닐수도

        /**
         * 활용하기
         */
        System.out.println("======활용하기======");
        Class<?> bookClass1 = Class.forName("com.example.reflection.Book");
        Constructor<?> constructor = bookClass1.getConstructor(int.class);// int 를 매개변수로 갖는 생성자로 객체 생성
        Book myBook = (Book) constructor.newInstance(16);
        System.out.println("Book 의 a 의 값: " + myBook.a);

        Method m = Book.class.getDeclaredMethod("add", int.class, int.class);
        int invoke = (int)m.invoke(myBook, 3, 4);
        System.out.println("invoke 값: " + invoke);


    }

}
