# 자바, JVM, JDK 그리고 JRE

<img src="/Users/yeonnex/Library/Application Support/typora-user-images/image-20220416111843538.png" alt="image-20220416111843538" style="zoom:50%;" />

## JVM (Java Virtual Machine)

- 자바 가상 머신으로, 자바 바이트 코드(.class 파일)를 OS 에 특화된 코드로 변환(인터프리터와 JIT 컴파일러)하여 실행한다

  ```
  $ vi HelloJava.java
  $ javac HelloJava.java #컴파일
  $ ls
  HelloJava.class HelloJava.java #.class 파일에 바이트 코드가 있음
  
  $ javap -c HelloJava # 자바 바이트 코드 보기
  Compiled from "HelloJava.java"
  public class HelloJava {
    public HelloJava();
      Code:
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
  
    public static void main(java.lang.String[]);
      Code:
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String hello!!
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
  }
  
  ```

    - 자바 프로그램을 실행시킨다는 것은, 인터프리터와 JIT 컴파일러가 위 바이트 코드를 각 OS 에 맞는 머신 코드로 변경(010101..)하여 CPU 에서 돌아가게 한다는 것이다.
    - JVM 이 하는일은,
        - 클래스를 읽어들이기
        - 메모리에 올리기 (한 장소만 있는게 아님)
        - OS 에 특화된 코드로 변환하기

- JVM 은 바이트 코드를 실행하는 표준이며, 여러 벤더마다 구현해놓은 JVM 구현체가 존재한다

    - JVM 벤더: 오라클, 아마존, Azul ...

## JRE (Java Runtime Environment)

- JVM + 라이브러리 (세팅 파일 등)

- 최소한의 배포단위. JVM 은 JVM 홀로 배포되지 않음

- 자바 애플리케이션을 실행할 때 필요한 것만 가지고 있다.

  ```plain
  $ java HelloJava # JRE 만으로도 가능
  ```

- 컴파일 할 때 쓰는 컴파일러(javac) 는 들어있지 않음

- 개발 관련은 JDK 에서 제공

    - 본인은 자바 개발자이기 때문에 JRE만 다운받아 쓰는 경우는 흔치 않음

## JDK (Java Development Kit)

- JRE + 개발에 필요한 툴
- 오라클은 자바 11부터 JDK 만 제공하며 JRE를 따로 제공하지 않음

## 자바

- 프로그래밍 언어
- JDK 에 들어있는 자바 컴파일러(javac)로 바이트코드(.class 파일)로 컴파일할 수 있다
- 자바 유료화? 오라클에서 만든 Oracle JDK 11 버전부터 상용으로 사용할 때 유료.
    - 오라클에서 만든 Oracle open JDK 는 무료

## 다른 언어 지원

<img src="/Users/yeonnex/Library/Application Support/typora-user-images/image-20220416105718357.png" alt="image-20220416105718357" style="zoom:50%;" />

```k
$ vi HelloKotlin.kt
$ kotlinc HelloKotlin.kt

@ javap -c HelloKotlin
Compiled from "HelloKotlin.kt"
public final class HelloKotlinKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin!
       8: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      11: swap
      12: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      15: return
}

$ kotlinc HelloKotlin.kt -include-runtime -d hellokotlin.jar # jar 파일 생성
$ java -jar hellokotlin.jar
Hello, Kotlin!

```

- JVM 기반으로 동작하는 프로그래밍 언어
- 클로저, 그루비, Jython, Kotlin, Scala ...

# JVM 구조

<img src="/Users/yeonnex/Library/Application Support/typora-user-images/image-20220416122453878.png" alt="image-20220416122453878" style="zoom:75%;" />

## 클래스 로더 시스템

- .class 에서 바이트 코드를 읽고 메모리에 저장
- **loading**: 클래스를 읽어오는 과정
- **linking**: 레퍼런스를 연결하는 과정
- **initialization**: static 값들 초기화 및 변수에 할당

## 메모리

- **method 영역**에는 클래스 수준의 정보(클래스 이름, 부모 클래스 이름, 메소드, 변수) 저장. *공유자원이다*
- **heap 영역**에는 실제 객체(인스턴스)를 저장. *공유 자원이다*

> 공유자원?
> **stack, PC register, native method stack** 영역은 **어떤 스레드인지에 따라** 그 스레드에서만 공유하는 자원이지, heap 이나 method 처럼 공유 자원이 아님

- **stack 영역** 에는 스레드마다 런타임 스택을 만들고, 그 안에 메소드 호출을 스택 프레임이라 부르는 블럭으로 쌓는다. 스레드 종료하면 런타임 스택도 사라진다

- **PC(Program Counter) 레지스터**에는 스레드마다 스레드 내 현재 실행할 스택 프레임을 가리키는 포인터가 생성된다

- **native method stack** 역시 스레드 별로 생기며, 애플리케이션에서 네이티브 메서드 호출 시 스택프레임이 쌓임

  ```java
  /**
  * Hello.java
  */
  Thread.currentThread();
  // currentThread 메서드는 자바로 구현되어있지 않다. c 로 구현되어있음.
  
  /**
  * Thread.java
  */
  public static native Thread currentThread(); 
  // JNI 라 부름. 네이티브로 된 코드를 호출할 수 있게하는 인터페이스
  // 이것의 실제 구현은 네이티브 메소드 라이브러리에 있음. 이 라이브러리는 항상 JNI 를 통해서 써야 한다
  ```

## 실행엔진

- **인터프리터**:생성된 바이트 코드(.class) 를 한 줄 한 줄 읽어가며 OS 에 맞는 머신 코드를 생성
- **JIT 컴파일러**: 한줄 한줄 실행하는 인터프리터의 효율을 높이기 위한 방안으로, 반복되는 코드가 나왔을 때, 인터프리터는 JIT 컴파일러에게 보낸다. 이름은 컴파일러지만, 자바 파일을 컴파일하는 것이
  아니라, 반복되는 코드를 네이티브 코드로 바꿔두는 역할을 한다. 그 다음부터 반복되는 코드가 나왔을때, 인터프리터는 interpreting 하지 않고 네이티브 코드로 컴파일된 코드를 바로 사용할 수 있게 된다.
  이로써 실행 속도 향상!
- **GC(Garbage Collector)** : 더이상 참조되지 않는 객체를 모아서 정리한다

## JNI(Java Natice Interface)

- 자바 애플리케이션에서 C, C++, 어셈블리로 작성된 함수를 사용할 수 있는 방법 제공
- native 키워드를 통한 메소드 호출

## 네이티브 메소드 라이브러리

- C, C++ 로 작성된 라이브러리

## 라이프사이클 정리

- 클래스 로더가 클래스를 읽는다
- 메모리에 배치한다
- 실행엔진의 인터프리터가 한줄한줄 실행하는데, 비효율적이니 JIT 컴파일러를 사용한다
- 메모리나 실행엔진에서 네이티브 메소드를 쓴다면 JNI 를 꼭 통한다
- 메모리 최적화를 위해 안쓰는 객체는 GC 시킨다

# 클래스 로더

<img src="/Users/yeonnex/Library/Application Support/typora-user-images/image-20220416122742633.png" alt="image-20220416122742633" style="zoom:67%;" />

- 로딩, 링크, 초기화 수능로 진행된다

## 로딩

- 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 "method" 영역에 저장
- 이때 메소드 영역에 저장되는 데이터
    - FQCN
    - 메소드와 변수
- 로딩이 끝나면 해당 클래스 타입의 Class 객체를 생성하여 "힙" 영역에 저장

- 제일 먼저 가장 부모 클래스 로더인 Bootstrap 이 읽고, 못읽으면 Extension, 그래도 못 읽으면 Application 이 읽는다
- Application 도 읽지 못하면 ClassNotFoundException 이 발생한다

## 링크

- Verity, Prepare, Resolve(optional) 세 단계
- **Verify**: .class 파일 형식이 유효한지 체크
- **Preparation**: 클래스 변수(static 변수)와 기본값에 필요한 메모리 준비
- **Resolve**: 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체

## 초기화

- static 변수의 값을 할당 (static 블럭이 있다면 이때 실행된다)

> 클래스 로더는 계층 구조로 이루어져 있으면서 기본적으로 세가지 클래스 로더가 제공된다

1. **BootstrapClassLoader**
    1. JAVA_HOME\lib 에 있는 코어 자바 API 를 제공한다. 최상위 우선순위를 가진 클래스로더
2. **PlatformClassLoader**
    1. JAVA_HOME\lib\ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽는다
3. **AppClassLoader**
    1. 애플리케이션 클래스 패스(애플리케이션 실행할 때 주는 -classpath 옵션 또는 java.class.path 환경 변수의 값에 해당하는 위치)에서 클래스를 읽는다

```java
public class HelloWorld
{
    public static void main( String[] args )
    {
        // 계층형 구조이다
        ClassLoader classLoader = HelloWorld.class.getClassLoader();
        System.out.println("===============");
        System.out.println(classLoader); // 애플리케이션 클래스 로더: jdk.internal.loader.ClassLoaders$AppClassLoader@512ddf17
        System.out.println(classLoader.getParent()); // 플랫폼 클래스 로더: jdk.internal.loader.ClassLoaders$PlatformClassLoader@35bbe5e8
        System.out.println(classLoader.getParent().getParent()); // 부트스트랩 클래스 로더: 플랫폼 클래스 로더의 부모가 있긴 하지만 볼 수 없음. 네이티브 코드로 구현되어서 그럼 null
    }
}
```

​	