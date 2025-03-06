// 文件位置：src/main/java/org/acme/model/Hero.java
package org.acme.model;

public class Hero {
    private String name;
    private String superpower;

    // 必须有无参构造函数
    public Hero() {}

    // 带参数的构造函数（可选）
    public Hero(String name, String superpower) {
        this.name = name;
        this.superpower = superpower;
    }

    // Getter/Setter（必须！）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }
}