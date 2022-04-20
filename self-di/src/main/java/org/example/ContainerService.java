package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
    public static <T> T getObject(Class<T> classType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null){ // @Inject 어노테이션이 있다면
                try {
                    Object fieldInstance = createInstance(field.getType());
                    field.setAccessible(true);
                    field.set(instance, fieldInstance);
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
        return classType.getConstructor().newInstance();
    }
}
