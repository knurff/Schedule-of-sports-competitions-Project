package com.example.sport_schedule.entity;

import com.example.sport_schedule.validation.team.UniqueName;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@UniqueName
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "Please fill the team name")
    @Length(max = 64, message = "Team name too long (more than 64 character)")
    @Setter(AccessLevel.NONE)
    protected String name;

    @NotBlank(message = "Please fill the team city")
    @Length(max = 64, message = "Team city too long (more than 64 character)")
    protected String city;

    @NotBlank(message = "Please fill the team country")
    @Length(max = 64, message = "Team country too long (more than 64 character)")
    protected String country;

    @OneToMany(mappedBy = "firstTeam", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Game> firstGames;

    @OneToMany(mappedBy = "secondTeam", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Game> secondGames;

    @Builder
    public Team(Long id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Team setName(String name) {
        this.name = name.trim();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
