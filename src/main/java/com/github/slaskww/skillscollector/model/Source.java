package com.github.slaskww.skillscollector.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) &&
                Objects.equals(name, source.name) &&
                Objects.equals(description, source.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
