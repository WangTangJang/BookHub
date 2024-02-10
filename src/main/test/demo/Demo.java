package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Calculator calculator = context.getBean(Calculator.class);
        //调用方法
        int result = calculator.add(10, 20);
        System.out.println("结果为：" + result);
    }
}

interface Calculator {
    //加法
    public int add(int a, int b);
    //减法
    public int sub(int a, int b);
    //乘法
    public int mul(int a, int b);
    //除法
    public int div(int a, int b);
}
class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
    @Override
    public int sub(int a, int b) {
        return a - b;
    }
    @Override
    public int mul(int a, int b) {
        return a * b;
    }
    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
//标注这是一个切面类
@Aspect
//交给Spring管理
@Component
class LogAspect {
    //定义一个切点表达式，表示匹配Calculator接口中的所有方法
    @Pointcut("execution(* com.example.Calculator.*(..))")
    public void pointcut() {}
    //定义一个前置通知，表示在切点方法执行前执行
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        //获取方法名和参数
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法" + name + "开始执行，参数为：" + Arrays.toString(args));
    }
    //定义一个后置通知，表示在切点方法执行后执行
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        //获取方法名
        String name = joinPoint.getSignature().getName();
        System.out.println("方法" + name + "执行结束");
    }
}
