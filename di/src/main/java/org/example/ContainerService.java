package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

 public static <T> T getObject(Class<T> classType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//     return new BookRepository(); 소스 코드에서는 테스트 코드의 클래스 참조하지 못함. 반대로, 테스트에서는 소스코드 참조 가능!
     // 어쩔 수 없이 리플렉션을 써야겠군!
     T instance = createInstance(classType);
     Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
         if (f.getAnnotation(Inject.class) != null){
             try {
                 Object fieldInstance = createInstance(f.getType());
                 f.setAccessible(true);
                 f.set(instance, fieldInstance);
             } catch (InstantiationException e) {
                 e.printStackTrace();
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             } catch (InvocationTargetException e) {
                 e.printStackTrace();
             } catch (NoSuchMethodException e) {
                 e.printStackTrace();
             }
         }
     });
     return instance;
 }

    private static <T> T createInstance(Class<T> classType) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return classType.getConstructor(null).newInstance(); // 아무 인자도 받지 않는 생성자로 인스턴스 만들어서 반환!
    }
}
