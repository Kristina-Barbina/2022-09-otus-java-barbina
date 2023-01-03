/*
 * Copyright (c) 2023. Created by Kristina Barbina.
 */

package testFramework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestApplication {

    private final static Map<TestResults, Integer> TEST_STATISTICS = new HashMap<>();
    private final Object testObj;
    private boolean hasError = false;
    public TestApplication(Object testObj){
        this.testObj = testObj;
    }

    public static void clearExecutionCounter(){
        TEST_STATISTICS.clear();
    }
    public static void addExecutionCounter(TestResults key) {
        TEST_STATISTICS.put(key, TEST_STATISTICS.containsKey(key) ? TEST_STATISTICS.get(key) + 1: 1);
    }

    public boolean hasError(){
        return hasError;
    }

    public static void report() {
        System.out.println(TEST_STATISTICS);
    }


    void executeMethod(Method method){
        try {
            if(!hasError) {
                method.setAccessible(true);
                method.invoke(testObj);
            }
        } catch (InvocationTargetException e) {
            hasError = true;
            addExecutionCounter(TestResults.TestsWithErrors);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    enum TestResults {
        AllTests,
        TestsDone,
        TestsWithErrors

    }


}
