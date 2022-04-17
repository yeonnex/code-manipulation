# 리플렉션 (Feat.스프링의 의존성 주입 원리)
> 클래스 로딩이 끝나면, Class 타입의 인스턴스를 만들어서 `힙` 이라는 메모리 공간에 넣어준다

#### 메모리 구조 🎁
 
`스택` `PC레지스터` `네이티브메소드스택` `메소드` `힙`

## 리플렉션
- 리플렉션의 시작은 `Class<T>`
- Class 타입의 객체를 통해 해당 클래스의 정보(상속,변수,인터페이스)에 접근할 수 있다
### Class 객체 만드는 세가지 방법 
```java
public class Book implements MyInterface{
    public int a;
    private int x;
    public int b;
    
    public static String B = "HELLO";
    public Book(){

    }
    public Book(int a){
        this.a = a;
    }
    public static void main(String[] args){
        // 방법 3. 타입으로 Class 객체 생성하기
        Class<Book> bookClass = Book.class;
        
        // 방법 2. 인스턴스에서 Class 객체 가져오기
        Book book = new Book();
        book.getClass();

        // 방법 3. 문자열
        Class<?> aClass1 = Class.forName("com.example.reflection.Book");

    }

}

```
`Book.class` 하는 순간, Class 타입의 인스턴스를 만들어지고 `힙` 영역에 저장된다.
### Class 객체의 특징
- 모든 클래스를 로딩하면 Class 타입의 객체가 생긴다
- 모든 인스턴스는 .getClass 메서드를 가지고 있다
- 인터페이스, 상속, 변수, 애노테이션 등의 정보를 볼 수 있다



