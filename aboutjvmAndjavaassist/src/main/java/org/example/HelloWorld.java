package org.example;

/**
 * Hello world!
 */
public class HelloWorld {
    public static void main(String[] args) {
        // 계층형 구조이다
        ClassLoader classLoader = HelloWorld.class.getClassLoader();
        System.out.println("===============");
        System.out.println(classLoader); // 애플리케이션 클래스 로더: jdk.internal.loader.ClassLoaders$AppClassLoader@512ddf17
        System.out.println(classLoader.getParent()); // 플랫폼 클래스 로더: jdk.internal.loader.ClassLoaders$PlatformClassLoader@35bbe5e8
        System.out.println(classLoader.getParent().getParent()); // 부트스트랩 클래스 로더: 플랫폼 클래스 로더의 부모가 있긴 하지만 볼 수 없음. 네이티브 코드로 구현되어서 그럼 null
    }
}
