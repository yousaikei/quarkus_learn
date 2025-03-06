package org.acme.microprofile.graphql;

import io.smallrye.graphql.api.GraphQLApi;
import io.smallrye.graphql.api.Query;
import io.smallrye.graphql.api.Description;
import io.smallrye.graphql.api.Name;
import io.smallrye.graphql.api.Source;
import io.smallrye.graphql.api.Mutation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@GraphQLApi
@ApplicationScoped
public class FilmResource {

    @Inject
    GalaxyService service;

    // 查询所有电影
    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    // 根据 filmId 查询单个电影
    @Query
    @Description("Get a Film from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    // 在返回 Film 时，同时查询该电影的英雄列表
    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    // 查询所有盟友
    @Query
    public List<Ally> allies() {
        return service.getAllAllies();
    }

    // 查询所有角色（英雄和盟友）
    @Query
    @Description("Get all characters from a galaxy far far away")
    public List<Character> characters() {
        return service.getAllCharacters();
    }

    // 搜索接口，查询电影和角色
    @Query
    @Description("Search for heroes or films")
    public List<SearchResult> search(String query) {
        return service.search(query);
    }

    // 创建英雄（变更）
    @io.smallrye.graphql.api.Mutation
    public Hero createHero(Hero hero) {
        service.addHero(hero);
        return hero;
    }

    // 删除英雄（变更）
    @io.smallrye.graphql.api.Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }
}
