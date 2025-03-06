package org.acme.service;

import org.acme.model.Film;
import org.acme.model.Hero;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GalaxyService {
    private List<Film> films = new ArrayList<>();
    private List<Hero> heroes = new ArrayList<>();

    // 初始化一些测试数据
    public GalaxyService() {
        films.add(new Film("A New Hope", 1977, "George Lucas"));
        heroes.add(new Hero("Luke Skywalker", "Force Sensitivity"));
    }

    // 获取所有电影
    public List<Film> getAllFilms() {
        return films;
    }

    // 获取所有英雄
    public List<Hero> getAllHeroes() {
        return heroes;
    }

    // 添加新电影
    public Film addFilm(Film film) {
        films.add(film);
        return film;
    }
}