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
 * ๐ ์ฃผ์
 * <p>
 * ver ๋ง๋ค ํ๋์ ver ๋ง ๋จ๊ธฐ๊ณ  ๋๋จธ์ง๋ ์ฃผ์ ์ฒ๋ฆฌํด์ ์คํ์ํฌ ๊ฒ.
 * ๋งค ์คํ ์  ๋น๋๋ฅผ ํด๋ฆฐํ์ฌ .class ํ์ผ์ ์์ ๊ณ  ์๋ก ์์ฑํด์ค ๊ฒ.
 */
public class Masulsa {
    public static void main(String[] args) throws IOException {

//    /**
//     * ver1.
//     */
////        new ByteBuddy().redefine(Moja.class) // Moja.class ๋ฅผ ์ฝ๋ ์๊ฐ Moja.class ํ์ผ์ด ์์ฑ๋จ
////                .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
////                .make().saveIn(new File("/Users/yeonnex/Dev/inflearn/code-manipulation/target/classes"));
//
//        // ๋จผ์  ์์ ์กฐ์ํ๋ ์ฝ๋๋ฅผ ์คํ์ํจ ๋ค ์ฃผ์์ฒ๋ฆฌ ํ ๋ค์์ ๋ชจ์์์ ๊บผ๋ด์ผ ํ๋ค
//        System.out.println(new Moja().pullOut());

//    /**
//     * ver2.
//     * ์ด๋ ๊ฒ ํ๋ฉด, ํด๋์ค ํ์ผ์ด ๋จผ์  ๋ณ๊ฒฝ๋๊ณ (๋จผ์  ์กฐ์๋๊ณ ) ๊ทธ๊ฒ์ ์ฌ์ฉํ๊ฒ ๋๋ค.
//     *
//     * ๊ทผ๋ฐ ์ด ๋ฐฉ๋ฒ์ ์์์ ๋งค์ฐ ์์กด์ ์ธ๋ฐ, ์ด ํ์ผ ๋ง๊ณ  ๋ง์ฝ ๋ค๋ฅธ ๊ณณ์์ ๋จผ์  Moja ๋ฅผ ์ฝ์๋ค๋ฉด,
//     * ์ฌ๊ธฐ์ Moja ๋ฅผ ์กฐ์ํด์ ์ด๋ฏธ ์์ฑ๋ Moja.class ์ ๋จ๊ถ๋ดค์ ๊ฐฑ์ ๋์ง ์์ ๊ฐ๋ฅ์ฑ์ด ํผ.
//     */
//
//        ClassLoader classLoader = Masulsa.class.getClassLoader();
//        TypePool typePool = TypePool.Default.of(classLoader);
//
//        // ByteBuddy api ๊ฐ ์ฒด์ด๋์ด ์ข ๋ง๊ธด ํ๋ค
//        new ByteBuddy().redefine(
//                        typePool.describe("org.example.chapter2.Moja").resolve(),
//                        ClassFileLocator.ForClassLoader.of(classLoader))
//                .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
//                .make().saveIn(new File("/Users/yeonnex/Dev/inflearn/code-manipulation/target/classes/"));
//
//        System.out.println(new Moja().pullOut());

        /**
         * ver3.
         * ์๋ก์ด ํ๋ก์ ํธ ์์ฑํ์ฌ premain ์์ ๋จธ๋ฆฌ ์์ฐ๊ธฐ (MasulsaAgent ํ๋ก์ ํธ)
         * JVM ์ต์์ -javaagent:/Users/yeonnex/Dev/inflearn/MasulsaAgent/target/MasulsaAgent-1.0-SNAPSHOT.jar ์ถ๊ฐ
         */
        System.out.println("์จ์");
        System.out.println(new Moja().pullOut());

    }
}
