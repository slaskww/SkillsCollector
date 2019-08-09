package com.github.slaskww.skillscollector.dto;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SOURCES")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String description;
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER) //ustawiamy FetchType.EAGER, żeby przy pobieraniu encji Source, Hibernate automatycznie pobierał pola będące kolekcjami Skillsów
    // (Hiber jest nadgorliwy jedynie wobec typów prostych. Nie pobiera typów złożonych od razu, lecz dopiero wtedy, gdy użytkownik jawnie o nie poprosi. Tryb Lazy służy temu,
    // by zoptymalizować wydajność pobierania danych z SQL. Gdyby nie chodziło o kolekcję obiektów Skill a o dużo większe obiekty przechowywane w kolekcjach, to pobieranie takich danych
    // zajmowałoby dużo czasu. Stąd domyślnie kolekcje mają FetchType ustawiony na Lazy)
    @JoinTable(name = "SOURCES_ATTACHED_SKILLS",
            joinColumns = {@JoinColumn(name = "SOURCE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SKILL_ID")})
    private Collection<Skill> skills = new ArrayList<>();

    @ManyToMany(mappedBy = "sources")
    private Collection<User> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Collection<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Collection<Skill> skills) {
        this.skills = skills;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) &&
                Objects.equals(description, source.description) &&
                Objects.equals(name, source.name) &&
                Objects.equals(skills, source.skills) &&
                Objects.equals(users, source.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name, skills, users);
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                ", users=" + users +
                '}';
    }
}