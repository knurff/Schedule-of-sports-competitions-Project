package com.example.sport_schedule.entity;

import com.example.sport_schedule.validation.game.DateValidation;
import com.example.sport_schedule.validation.game.NotSameTeams;
import com.example.sport_schedule.validation.team.TeamValidation;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "games")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@NotSameTeams
@DateValidation
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @TeamValidation
    @ManyToOne
    protected Team firstTeam;

    @TeamValidation
    @ManyToOne
    protected Team secondTeam;

    @NotNull(message = "Please, enter date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime date;

    @Valid
    @Embedded
    protected GameResult gameResult;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    protected User lastInteractionBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
