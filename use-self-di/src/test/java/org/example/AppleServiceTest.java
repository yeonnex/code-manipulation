package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;

public class AppleServiceTest {
    @Test
    public void 주입_테스트() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        AppleService appleService = ContainerService.getObject(AppleService.class);
        assertNotNull(appleService.appleRepository);
    }
}
