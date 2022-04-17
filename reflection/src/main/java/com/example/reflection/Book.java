package com.example.reflection;

@MyAnnotation(value = "yeonnex", myName = "seoyeon")
public class Book implements MyInterface{
    public int a;
    private int x;
    public int b;

    public static String B = "HELLO";
    public Book(){

    }
    public int add(int num1, int num2){
        return num1 + num2;
    }
    public Book(int a){
        this.a = a;
    }
}