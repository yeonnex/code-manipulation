package org.example.chapter2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 📌 주의
 * <p>
 * ver 마다 하나의 ver 만 남기고 나머지는 주석 처리해서 실행시킬 것.
 * 매 실행 전 빌드를 클린하여 .class 파일을 없애고 새로 생성해줄 것.
 */
public class Masulsa {
    public static void main(String[] args) throws IOException {

//    /**
//     * ver1.
//     */
////        new ByteBuddy().redefine(Moja.class) // Moja.class 를 읽는 순간 Moja.class 파일이 생성됨
////                .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
////                .make().saveIn(new File("/Users/yeonnex/Dev/inflearn/code-manipulation/target/classes"));
//
//        // 먼저 위의 조작하는 코드를 실행시킨 뒤 주석처리 한 다음에 모자에서 꺼내야 한다
//        System.out.println(new Moja().pullOut());

//    /**
//     * ver2.
//     * 이렇게 하면, 클래스 파일이 먼저 변경되고(먼저 조작되고) 그것을 사용하게 된다.
//     *
//     * 근데 이 방법은 순서에 매우 의존적인데, 이 파일 말고 만약 다른 곳에서 먼저 Moja 를 읽었다면,
//     * 여기서 Moja 를 조작해서 이미 생성된 Moja.class 에 떨궈봤자 갱신되지 않을 가능성이 큼.
//     */
//
//        ClassLoader classLoader = Masulsa.class.getClassLoader();
//        TypePool typePool = TypePool.Default.of(classLoader);
//
//        // ByteBuddy api 가 체이닝이 좀 많긴 하다
//        new ByteBuddy().redefine(
//                        typePool.describe("org.example.chapter2.Moja").resolve(),
//                        ClassFileLocator.ForClassLoader.of(classLoader))
//                .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
//                .make().saveIn(new File("/Users/yeonnex/Dev/inflearn/code-manipulation/target/classes/"));
//
//        System.out.println(new Moja().pullOut());

        /**
         * ver3.
         * 새로운 프로젝트 생성하여 premain 에서 머리 손쓰기 (MasulsaAgent 프로젝트)
         * JVM 옵션에 -javaagent:/Users/yeonnex/Dev/inflearn/MasulsaAgent/target/MasulsaAgent-1.0-SNAPSHOT.jar 추가
         */
        System.out.println("쨔잔");
        System.out.println(new Moja().pullOut());

    }
}
