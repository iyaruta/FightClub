package home.inna.fc.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Duel_Request")
public class DuelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int timeout;
    @Column(name = "hero_one")
    private Long heroOne;

    @Column(name = "hero_two")
    private Long heroTwo;
    private LocalDateTime dataTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Long getHeroOne() {
        return heroOne;
    }

    public void setHeroOne(Long heroOne) {
        this.heroOne = heroOne;
    }

    public Long getHeroTwo() {
        return heroTwo;
    }

    public void setHeroTwo(Long heroTwo) {
        this.heroTwo = heroTwo;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }
}
