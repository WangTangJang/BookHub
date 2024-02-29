package demo;


import java.util.Iterator;
import java.util.Map;

public class Demo {
    public static void main(String[] args)  {
        // 读取系统变量中的
        Map map = System.getenv();
        Iterator it = map.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.print(entry.getKey()+"=");
            System.out.println(entry.getValue());
        }
    }
}
