package home.inna.fc.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Duel_Request")
@Data
public class DuelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int timeout;

    @Column(name = "hero_one")
    private Long heroOne;

    @Column(name = "hero_two")
    private Long heroTwo;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataTime;

}
