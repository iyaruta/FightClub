package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BattleTest {

    private Battle battle;
    private Map<Color, Team> teams;

    @Before
    public void init() {
        battle = new Battle();

        Team team1 = mock(Team.class);
        Team team2 = mock(Team.class);
        Team team3 = mock(Team.class);

        teams = new LinkedHashMap<>();
        teams.put(Color.RED, team1);
        teams.put(Color.BLUE, team2);
        teams.put(Color.WHITE, team3);

        battle.setTeams(teams);
    }

    @Test
    public void getHero() {
        IHero hero = hero(100L, Color.RED);
        mockTeam(Color.RED, hero);

        IHero result = battle.getHero(100L);
        assertNotNull(result);

        verify(teams.get(Color.RED)).getHero(100L);
        verify(teams.get(Color.BLUE), never()).getHero(100L);
        verify(teams.get(Color.WHITE), never()).getHero(100L);
    }

    @Test
    public void getHero_blueTeam() {
        IHero hero = hero(100L, Color.BLUE);
        mockTeam(Color.BLUE, hero);

        IHero result = battle.getHero(100L);
        assertNotNull(result);

        verify(teams.get(Color.RED)).getHero(100L);
        verify(teams.get(Color.BLUE)).getHero(100L);
        verify(teams.get(Color.WHITE), never()).getHero(100L);
    }

    @Test
    public void getHero_noHero() {
        IHero result = battle.getHero(100L);
        assertNull(result);
    }

    @Test
    public void getOppositeTeams() {
        when(teams.get(Color.BLUE).hasAlive()).thenReturn(true);
        when(teams.get(Color.WHITE).hasAlive()).thenReturn(true);

        IHero hero = hero(100L, Color.RED);
        List<Team> result = battle.getOppositeTeams(hero);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void getOppositeTeams_oneAlive() {
        when(teams.get(Color.BLUE).hasAlive()).thenReturn(false);
        when(teams.get(Color.WHITE).hasAlive()).thenReturn(true);

        IHero hero = hero(100L, Color.RED);
        List<Team> result = battle.getOppositeTeams(hero);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void getOppositeTeams_noAlive() {
        IHero hero = hero(100L, Color.RED);
        List<Team> result = battle.getOppositeTeams(hero);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    private void mockTeam(Color color, IHero hero) {
        Team team = teams.get(color);
        when(team.getHero(hero.getId())).thenReturn(hero);
    }

    private IHero hero(long heroId, Color color) {
        IHero hero = new IHero();
        hero.setId(heroId);

        hero.setBattleId(1L);
        hero.setColor(color);

        return hero;
    }

}

