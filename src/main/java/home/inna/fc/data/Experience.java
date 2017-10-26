package home.inna.fc.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "experience_table")
@Data
public class Experience {

    @Id
    private int experience;
    private int ability;
    private int level;

}
