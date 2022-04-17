# 모자에서 토끼 꺼내기

```java
public class Moja {
  public String pullOut(){
    return "";
  }
}
```

```java
public class Masulsa {
    public static void main(String[] args){
        String rabbit = new Moja().pullOut();
        System.out.println(rabbit);
    }
}
```

## 바이트 코드 조작 라이브러리

- Asm
- Javassist
- **ByteBuddy**

```java
new ByteBuddy().redefine(Moja.class)
               .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
               .make().saveIn(new File("/Users/yeonnex/Dev/inflearn/code-manipulation/target/classes"));
```

## 바이트 코드 조작하는 법 1 - Moja.class 직접 접근

```java
// 위 코드 실행하면, .class 파일이 이렇게 바뀌게 된다. .java 파일은 그대로
public class Moja {
    public Moja() {
    }

    public String pullOut() {
        return "Rabbit!";
    }
}
```

```java
System.out.println(new Moja().pullOut()); // Rabbit! 출력
```

> 먼저 조작 코드 실행 뒤, 주석처리 한 다음 모자에서 꺼내야 한다. redefine(Moja.class) 에서 Moja.class 를 먼저 이미 읽었고, 변경한 다음 다시 클래스 파일을 떨어뜨려도 한번 읽은 건 다시 클래스 로딩을 하지 않기 때문이다.

소스코드와 상관없이, 바이트 코드가 바뀌어있기 때문에 앞으로 모자에서는 토끼가 나올 것이다. (컴파일을 다시 하지 않는이상!)

## 바이트 코드 조작하는 법 2 - 문자열로 우회하기

Moja.class 로 직접 접근하지 않고, 우회해서 접근하면 가능해진다. 문자열로 우회해서 접근!

## 바이트 코드 조작하는 법 3 - javaagent 사용하기

### javaagent JAR 만들기

- https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html

- 붙이는 방식은 시작시 붙이는 premain과 런타임중 동적으로 붙이는 agentmain 이 있다
- Instrumentation 을 사용한다

```java
public class MasulsaAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default() // bytebuddy 의 agentbuilder 를 사용하였다
                .type(ElementMatchers.any())
                .transform((builder, typeDescription, classLoader, javaModule) -> builder.method(named("pullOut")).intercept(FixedValue.value("Rabbit!🐰❤️"))).installOn(inst);
    }

}
```

### javaagent 붙여서 사용하기

- JVM 옵션에 위에서 만든 jar 경로를 지정

<img src="/Users/yeonnex/Library/Application Support/typora-user-images/image-20220416161531287.png" alt="image-20220416161531287" style="zoom:25%;" />

- 클래스 로더가 클래스를 읽어올 때 javaagent 를 거쳐서 변경된 바이트 코드를 읽어들여 사용한다
- 변경된 바이트 코드가 메모리에 들어가고, 그걸 실행하게 되는 것!

- 클래스 파일은 여전히 "" 을 리턴한다고 나오지만, **메모리 내에는 이미 바뀌어 있는 것**

## 바이트코드 조작 활용 예시

- 프로그램 분석
    - 코드에서 버그 찾는 툴
    - 코드 복잡도 계산
- 클래스 파일 생성
    - 프록시
    - 특정 API 호출 접근 제한
- **그 밖에도 자바 소스코드 건드리지 않고 코드 변경이 필요한 여러 경우에 사용할 수** 있다
    - 프로파일러
    - 최적화
    - 로깅
    - ...
- 스프링이 컴포넌트 스캔을 하는 방법 (asm 사용!)
    - 컴포넌트 스캔으로 빈으로 등록할 후보 클래스 정보를 찾는데 사용

## Next

다음 시간에는 리플렉션에 대해 알아볼 것이다. 리플렉션은 클래스 로딩시가 아닌, 이미 메모리에 클래스 정보가 올라왔을 때 이것을 이용하여 또 뭔가를 해볼 수가 있다고 한다.