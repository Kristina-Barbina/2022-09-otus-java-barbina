/*
 * Copyright (c) 2023. Created by Kristina Barbina.
 */

package testFramework;

import testFramework.annotations.After;
import testFramework.annotations.Before;
import testFramework.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class TestProcessor {

    public static void ExecuteTests(Class<?> testClass){

        TestApplication.clearExecutionCounter();
        Constructor<?> methodConstructor = getConstructor(testClass);


        var methodBefore = getMethodBeforeReference(testClass);
        var methodAfter = getMethodAfterReference(testClass);
        for (var testMethod: getTestMethodsReferences(testClass)) {

            Object testObject = getTestObject(methodConstructor);
            TestApplication application = new TestApplication(testObject);

            TestApplication.addExecutionCounter(TestApplication.TestResults.AllTests);

            application.executeMethod(methodBefore);
            application.executeMethod(testMethod);
            application.executeMethod(methodAfter);
            if(!application.hasError())
                TestApplication.addExecutionCounter(TestApplication.TestResults.TestsDone);

        }
        TestApplication.report();
    }

    private static Object getTestObject(Constructor<?> methodConstructor) {
        Object testObject;
        try {
            testObject = methodConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return testObject;
    }

    private static Constructor<?> getConstructor(Class<?> testClass) {
        try {
            return testClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    static Method getMethodBeforeReference(Class<?> testClass){
        var method = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m-> m.isAnnotationPresent(Before.class))
                .findFirst();
        if (method.isPresent())
            return method.get();
        throw new RuntimeException("\"Before\" annotated method not found!");
    }

    static Method getMethodAfterReference(Class<?> testClass){
        var method = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m-> m.isAnnotationPresent(After.class))
                .findFirst();
        if (method.isPresent())
            return method.get();
        throw new RuntimeException("\"After\" annotated method not found!");
    }

    static List<Method> getTestMethodsReferences(Class<?> testClass){
        var methods = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m-> m.isAnnotationPresent(Test.class))
                .toList();
        if (methods.size() > 0)
            return methods;
        throw new RuntimeException("\"Test\" annotated methods not found!");
    }

}
