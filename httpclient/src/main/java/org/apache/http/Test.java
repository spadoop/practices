package org.apache.http;

public class Test {
    public static <T> T getInstance(String qualifiedClassName) throws Exception {
        Class clazz = Class.forName(qualifiedClassName);
 
        return (T) clazz.newInstance();
    }
 
    public static void main(String[] args) throws Exception {
        Foo foo = getInstance("org.apache.http.Foo"); // 根据左值就能自动判断类型
        foo.go();
        Foo foo1 = getInstance("org.apache.http.Foo"); // 根据左值就能自动判断类型
        foo1.go();
 
        Bar bar = getInstance("org.apache.http.Bar"); // 根据左值就能自动判断类型
        bar.go();
    }
}
 
class Foo {
    public void go() {
        System.out.println(this+" Foo.go()");
    }
}
 
class Bar {
    public void go() {
        System.out.println("Bar.go()");
    }
}

