package org.acme.microprofile.graphql;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GalaxyService {

    private List<Hero> heroes = new ArrayList<>();
    private List<Film> films = new ArrayList<>();
    private List<Ally> allies = new ArrayList<>();

    public GalaxyService() {
        // 初始化 Film 数据
        Film aNewHope = new Film();
        aNewHope.title = "A New Hope";
        aNewHope.releaseDate = LocalDate.of(1977, Month.MAY, 25);
        aNewHope.episodeID = 4;
        aNewHope.director = "George Lucas";

        Film empire = new Film();
        empire.title = "The Empire Strikes Back";
        empire.releaseDate = LocalDate.of(1980, Month.MAY, 21);
        empire.episodeID = 5;
        empire.director = "George Lucas";

        Film jedi = new Film();
        jedi.title = "Return Of The Jedi";
        jedi.releaseDate = LocalDate.of(1983, Month.MAY, 25);
        jedi.episodeID = 6;
        jedi.director = "George Lucas";

        films.add(aNewHope);
        films.add(empire);
        films.add(jedi);

        // 初始化 Hero 数据
        Hero luke = new Hero();
        luke.name = "Luke";
        luke.surname = "Skywalker";
        luke.height = 1.7;
        luke.mass = 73;
        luke.lightSaber = LightSaber.GREEN;
        luke.darkSide = false;
        luke.episodeIds.addAll(Arrays.asList(4, 5, 6));

        Hero leia = new Hero();
        leia.name = "Leia";
        leia.surname = "Organa";
        leia.height = 1.5;
        leia.mass = 51;
        leia.darkSide = false;
        leia.episodeIds.addAll(Arrays.asList(4, 5, 6));

        Hero vader = new Hero();
        vader.name = "Darth";
        vader.surname = "Vader";
        vader.height = 1.9;
        vader.mass = 89;
        vader.darkSide = true;
        vader.lightSaber = LightSaber.RED;
        vader.episodeIds.addAll(Arrays.asList(4, 5, 6));

        heroes.add(luke);
        heroes.add(leia);
        heroes.add(vader);

        // 初始化 Ally 示例
        Ally jarjar = new Ally();
        jarjar.name = "Jar Jar";
        jarjar.surname = "Binks";
        allies.add(jarjar);
    }

    public List<Film> getAllFilms() {
        return films;
    }

    public Film getFilm(int id) {
        return films.get(id);
    }

    public List<Hero> getHeroesByFilm(Film film) {
        return heroes.stream()
                .filter(hero -> hero.episodeIds.contains(film.episodeID))
                .collect(Collectors.toList());
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public Hero deleteHero(int id) {
        return heroes.remove(id);
    }

    public List<Hero> getHeroesBySurname(String surname) {
        return heroes.stream()
                .filter(hero -> hero.surname.equals(surname))
                .collect(Collectors.toList());
    }

    public List<Ally> getAllAllies() {
        return allies;
    }

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();
        characters.addAll(heroes);
        characters.addAll(allies);
        return characters;
    }

    public List<SearchResult> search(String query) {
        List<SearchResult> results = new ArrayList<>();
        List<Film> matchingFilms = films.stream()
            .filter(film -> film.title.contains(query) || film.director.contains(query))
            .collect(Collectors.toList());
        results.addAll(matchingFilms);
        List<Character> matchingCharacters = getAllCharacters().stream()
            .filter(character -> character.getName().contains(query) || character.getSurname().contains(query))
            .collect(Collectors.toList());
        results.addAll(matchingCharacters);
        return results;
    }
}
