// 文件位置：src/main/java/org/acme/model/Film.java
package org.acme.model;

public class Film {
    private String title;
    private int releaseYear;
    private String director;

    // 必须有无参构造函数（Quarkus 和 GraphQL 反射需要）
    public Film() {}

    // 带参数的构造函数（可选）
    public Film(String title, int releaseYear, String director) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.director = director;
    }

    // Getter/Setter（必须！否则 GraphQL 无法序列化字段）
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}