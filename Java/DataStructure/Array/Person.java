package Java.DataStructure.Array;

public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }
}
