package demo.pack1;

public class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.bark();
    }
}
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}