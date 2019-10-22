import java.lang.reflect.Field;

public class A {

    private String str;

    public int B;

    public A(String str) {
        this.str = str;
    }

    public A() {
        this.B = 1;
        this.str = "Hello, world!";
    }

}