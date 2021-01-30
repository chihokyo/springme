package com.spring.demo;

/**
 * 使用有参数构造器注入
 */
public class Flower {
    // 属性
    private String smell;
    private int price;

    // 有参数构造器
    public Flower(String smell, int price) {
        this.smell = smell;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
            " smell='" + smell + "'" +
            ", price='" + price + "'" +
            "}";
    }

    public void show() {
        System.out.println(this);
    }
}
