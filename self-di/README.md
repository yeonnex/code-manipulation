## 의존성 주입 프레임워크 만들기
리플렉션을 사용하여! 필드주입을 해주는 컨테이너 서비스를 만들어보자.
`@Inject` 라는 어노테이션을 만들었음. 

클래스의 멤버 객체에 어노테이션이 붙으면, 그 객체 필드에 알맞은 객체를 주입해준다.

이걸 설치해서 사용해보자!

`$mvn install` 하면 로컬 메이븐 저장소에 설치가 된다. .jar 의 형태로!

다른 프로젝트에서 저걸 참조할 수 있다.

```xml
<groupId>org.example</groupId>
<artifactId>self-di</artifactId>
<version>1.0-SNAPSHOT</version>
```

