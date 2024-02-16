package demo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 使用动态代理
public class Demo {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        InvocationHandler handler = new MyInvocationHandler(realSubject);

        // 创建动态代理对象
        Subject proxy = (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[] { Subject.class },
                handler
        );

        // 通过代理调用真实对象的方法
        proxy.request();
    }
}


// 接口
interface Subject {
    void request();
}

// 真实对象
class RealSubject implements Subject {
    public void request() {
        System.out.println("RealSubject: Processing request.");
    }
}

// InvocationHandler 实现
class MyInvocationHandler implements InvocationHandler {
    private Object realSubject;

    public MyInvocationHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 额外的操作
        System.out.println("Proxy: Pre-processing request.");

        // 调用真实对象的方法
        Object result = method.invoke(realSubject, args);

        // 额外的操作
        System.out.println("Proxy: Post-processing request.");

        return result;
    }
}

