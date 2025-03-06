// src/main/java/org/acme/graphql/FilmResource.java
package org.acme.graphql;

import org.acme.model.Film;
import org.acme.model.Hero; // 确保导入 Hero 类
import org.acme.service.GalaxyService;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Mutation;
import jakarta.inject.Inject;
import java.util.List;


@GraphQLApi
public class FilmResource {

    @Inject
    GalaxyService galaxyService;

    @Query("allFilms")
    public List<Film> getAllFilms() {
        return galaxyService.getAllFilms();
    }

    @Query("allHeroes")
    public List<Hero> getAllHeroes() { // 返回 Hero 列表
        return galaxyService.getAllHeroes();
    }

    @Mutation("addFilm")
    public Film addFilm(Film film) {
        return galaxyService.addFilm(film);
    }
}