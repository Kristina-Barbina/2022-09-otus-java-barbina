/*
 * Copyright (c) 2023. Created by Kristina Barbina.
 */

public class Demo {
    public void DoSomething(String SayThat, boolean isThrowException){
        System.out.println(SayThat);
        if(isThrowException)
            throw new RuntimeException("Exception when do something");
    }
}
