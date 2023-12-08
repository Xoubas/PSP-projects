package org.example;

import static java.lang.Thread.sleep;

public class Person implements Runnable{
    private String name;
    private Greeting greeting;

    public Person(String name, Greeting greeting) {
        this.name = name;
        this.greeting=greeting;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

        if (this instanceof Teacher){
            greeting.teacherEnters(this.name);
        }else {
            greeting.studentEnters(this.name);
        }
    }
}
