package home.inna.fc.service;

import home.inna.fc.data.Player;
import home.inna.fc.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) //обязательно для мокито тестов, что бы включился мокито-фреймворк
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService = new PlayerService();

    @Test
    public void testFindByName() throws Exception {
        when(playerRepository.findByName("test_player_name")).thenReturn(getPlayer());


        Player player = playerService.findByName("test_player_name");
        assertNotNull(player);
        assertEquals(1, player.getAbility());
        assertEquals(4, player.getForce());
        assertEquals(3, player.getAgility());
        assertEquals(3, player.getInstinct());
        assertEquals(3, player.getStamina());
        assertEquals(1, player.getLevel());
        assertEquals(0, player.getHealth());
        assertEquals(0, player.getExperience());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(playerRepository).findByName(captor.capture());
        String value = captor.getValue();

        assertEquals("test_player_name", value);
    }

    @Test
    public void testSave() throws Exception {
        playerService.save("test_name");

        ArgumentCaptor<Player> captor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(captor.capture());
        Player value = captor.getValue();

        assertNotNull(captor);
        assertEquals("test_name", value.getName());

    }

    private Player getPlayer() {
        Player player = new Player();
        player.setName("test_player_name");
        player.setForce(4);
        player.setAgility(3);
        player.setInstinct(3);
        player.setStamina(3);
        player.setLevel(1);
        player.setHealth(0);
        player.setExperience(0);
        player.setAbility(1);
        return player;
    }
}