package demo.pack2;

import demo.pack1.Parent;

public class Child extends Parent {
    public int x = i;

    public void xx(){
        System.out.println(x);
    }
    public static void main(String[] args) {
        Child c = new Child();
        c.xx();
    }

}
