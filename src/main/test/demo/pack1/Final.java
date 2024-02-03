package demo.pack1;

public class Final {
    final int constantValue = 10;
    final MyClass myObject = new MyClass();

    void modifyValues() {
        // 编译错误，常量值不能被修改
        // constantValue = 20;

        // 合法，虽然引用不能改变，但对象的状态（字段值）可以改变
        myObject.modifyField();

    }

    public static void main(String[] args) {
        Final finalTest = new Final();
        finalTest.modifyValues();
    }
}
class MyClass {
    int value = 10;

    void modifyField() {
        value = 20;
    }
}