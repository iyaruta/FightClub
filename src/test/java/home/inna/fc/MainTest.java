package home.inna.fc;


import home.inna.fc.data.Player;
import home.inna.fc.service.PlayerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void testGetByName() throws Exception{
        Player player = new Player();
        player.setName("inna");
        player.setForce(4);
        player.setAgility(3);
        player.setInstinct(3);
        player.setStamina(3);
        player.setLevel(1);
        player.setHealth(0);
        player.setExperience(0);

        Player plr = playerService.findByName("inna");
        assertNotNull("inna", plr);
        assertEquals(4, player.getForce());
        assertEquals(3, player.getAgility());
        assertEquals(3, player.getInstinct());
        assertEquals(3, player.getStamina());
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getHealth());
        assertEquals(0, player.getExperience());
    }

}
