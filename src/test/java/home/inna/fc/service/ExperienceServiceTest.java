package home.inna.fc.service;

import home.inna.fc.data.Experience;
import home.inna.fc.data.Player;
import home.inna.fc.repository.ExperienceRepository;
import home.inna.fc.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExperienceServiceTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private ExperienceService experienceService;

    private Player player;

    @Before
    public void setup() {
        player = getPlayer();
        when(playerRepository.save(any(Player.class))).thenReturn(player);
    }

    @Test
    public void testRecalculate_ZeroExp() {
        experienceService.recalculate(this.player, 0);

        verify(experienceRepository, never()).findByRange(anyInt(), anyInt());
        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    public void testRecalculate_MinusExp() {
        experienceService.recalculate(this.player, Integer.MIN_VALUE);

        verify(experienceRepository, never()).findByRange(anyInt(), anyInt());
        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    public void testRecalculate_OneAbility() {
        List<Experience> experienceList = Collections.singletonList(experience(1, 0));
        when(experienceRepository.findByRange(3, 93)).thenReturn(experienceList);

        Player player = experienceService.recalculate(this.player, 90);
        assertNotNull(player);
        assertEquals(93, player.getExperience());
        assertEquals(6, player.getLevel());
        assertEquals(4, player.getAbility());

        verify(playerRepository).save(player);
    }

    @Test
    public void testRecalculate_TwoLevel() {
        List<Experience> experienceList = Arrays.asList(experience(0, 1), experience(0, 1));
        when(experienceRepository.findByRange(3, 1003)).thenReturn(experienceList);

        Player player = experienceService.recalculate(this.player, 1000);
        assertNotNull(player);
        assertEquals(1003, player.getExperience());
        assertEquals(8, player.getLevel());

        verify(playerRepository).save(player);
    }

    @Test
    public void  testExperience() {
        List<Experience> experienceList = Collections.emptyList();
        when(experienceRepository.findByRange(3, 15)).thenReturn(experienceList);

        Player player = experienceService.recalculate(this.player, 12);
        assertNotNull(player);
        assertEquals(15, player.getExperience());
        assertEquals(6, player.getLevel());
        assertEquals(3, player.getAbility());

        verify(playerRepository).save(player);
    }

    @Test
    public void testRecalculate() throws Exception {
        List<Experience> experienceList = Arrays.asList(experience(1, 0), experience(1, 0), experience(1, 1));
        when(experienceRepository.findByRange(3, 93)).thenReturn(experienceList);

        Player player = experienceService.recalculate(this.player, 90);

        assertEquals("test_player_name", player.getName());
        assertEquals(6, player.getAbility());
        assertEquals(4, player.getForce());
        assertEquals(3, player.getAgility());
        assertEquals(3, player.getInstinct());
        assertEquals(3, player.getStamina());
        assertEquals(7, player.getLevel());
        assertEquals(0, player.getHealth());
        assertEquals(93, player.getExperience());

        verify(playerRepository).save(player);
    }

    private Player getPlayer() {
        Player player = new Player();
        player.setName("test_player_name");
        player.setAbility(3);
        player.setForce(4);
        player.setAgility(3);
        player.setInstinct(3);
        player.setStamina(3);
        player.setLevel(6);
        player.setHealth(0);
        player.setExperience(3);
        return player;
    }

    private Experience experience(int ability, int level) {
        Experience exp = new Experience();
        exp.setAbility(ability);
        exp.setLevel(level);
        return exp;
    }

}