package roomescape.waiting;

import jakarta.persistence.*;
import roomescape.theme.Theme;
import roomescape.time.Time;

import java.time.LocalDate;

@Entity
public class Waiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long memberId;

    @ManyToOne
    private Theme theme;

    @ManyToOne
    private Time time;

    private LocalDate date;

    public Waiting(long memberId, Theme theme, Time time, LocalDate date) {
        this.memberId = memberId;
        this.theme = theme;
        this.time = time;
        this.date = date;
    }

    public Waiting() {
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Theme getTheme() {
        return theme;
    }

    public Time getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
