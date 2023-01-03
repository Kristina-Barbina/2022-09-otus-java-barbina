/*
 * Copyright (c) 2023. Created by Kristina Barbina.
 */


import testFramework.annotations.After;
import testFramework.annotations.Before;
import testFramework.annotations.Test;

public class TestClass {

    @Before
    void BeforeEach(){
        System.out.println("Before test");
    }

    @After
    void AfterEach(){
        System.out.println("After test");
    }

    @Test
    void Test(){
        var demoObj = new Demo();
        demoObj.DoSomething("Execute test 1", false);
    }

    @Test
    void Test2(){
        var demoObj = new Demo();
        demoObj.DoSomething("Execute Test2 with exception", true);
    }

    @Test
    void Test3(){
        var demoObj = new Demo();
        demoObj.DoSomething("Execute test3", false);
    }

}
