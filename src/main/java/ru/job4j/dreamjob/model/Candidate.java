package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель кандидата
 *
 * @author Alex_life
 * @version 3.0
 * @since 09.10.2022
 */
public class Candidate {
    private int id;
    private String name;
    private String desc;
    private LocalDateTime created;
    private boolean visible;
    private City city;

    public Candidate() {
    }

    public Candidate(int id, String name, String desc, LocalDateTime created, City city) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + '}';
    }
}
