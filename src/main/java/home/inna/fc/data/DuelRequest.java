package home.inna.fc.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Duel_Request")
public class DuelRequest {

    @Id
    private int id;
    private int timeOut;
    private Integer playerOne;
    private Integer playerTwo;
    private LocalDateTime dataTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Integer playerOne) {
        this.playerOne = playerOne;
    }

    public Integer getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Integer playerTwo) {
        this.playerTwo = playerTwo;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }
}
