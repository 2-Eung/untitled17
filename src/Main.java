import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface CustomInfo {
    String author();
    String date();
    int version() default 1;
}
@CustomInfo(author = "ㅇㅇㅎ", date = "2025-06-24", version = 2)   // CustomInfo 업데이트?
class Demo {

    @CustomInfo(author = "ㅇㅇㅎ", date ="2026-06-26")
    public void display() {
        System.out.println("Display method executed.");
    }
}
public class Main {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Class<?> demoClass = demo.getClass();

        if (demoClass.isAnnotationPresent(CustomInfo.class)) {
            CustomInfo classInfo = demoClass.getAnnotation(CustomInfo.class);
            System.out.println("Author: " + classInfo.author());
            System.out.println("Date: " + classInfo.date());
            System.out.println("Version: " + classInfo.version());
        }
        try {                                                // "display" 가 있든없든
            Method m = demoClass.getMethod("display"); // 꺼내오려하기때문에 에러발생
                                                             // try-catch 필수 (위험한 코드)
            if(m.isAnnotationPresent(CustomInfo.class)) {
                CustomInfo mi = m.getAnnotation(CustomInfo.class);

                System.out.println("Author: " + mi.author());
                System.out.println("Date: " + mi.date());
                System.out.println("Version: " + mi.version());
            }
        } catch(NoSuchMethodException e) {
            e.getStackTrace();
        }
    }
}