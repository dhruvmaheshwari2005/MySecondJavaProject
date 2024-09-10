package com.example;

public class Example {
    static class A {
        static {
            System.out.println("A is loaded");
        }
        A() {
            System.out.println("Hello");
        }
    }

    static class B {
        static {
            System.out.println("B is loaded");
        }

        B () {
            System.out.println("Welcome");
        }
    }
    public static void main(String[] args) {
        String a = "B";
        try {
            Class c = Class.forName(a);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
