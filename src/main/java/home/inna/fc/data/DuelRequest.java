package home.inna.fc.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Duel_Request")
public class DuelRequest {

    @Id
    private Long id;
    private int timeOut;
    private Long playerOne;
    private Long playerTwo;
    private LocalDateTime dataTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public Long getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Long playerOne) {
        this.playerOne = playerOne;
    }

    public Long getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Long playerTwo) {
        this.playerTwo = playerTwo;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }
}
