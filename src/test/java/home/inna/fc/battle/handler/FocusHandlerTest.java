package home.inna.fc.battle.handler;

import home.inna.fc.battle.Battle;
import home.inna.fc.battle.Battles;
import home.inna.fc.battle.IHero;
import home.inna.fc.battle.Team;
import home.inna.fc.dto.Color;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FocusHandlerTest {

    public static final Long HERO_1 = 100L;
    public static final Long HERO_2 = 200L;

    @Mock
    private Battles battles;

    @InjectMocks
    private FocusHandler focusHandler;

    @Test
    public void handle() throws Exception {
        IHero hero = hero(HERO_1, Color.RED);
        Battle battle = mock(Battle.class);
        when(battle.getHero(HERO_1)).thenReturn(hero);

        Team team = mock(Team.class);
        when(team.findFocus(hero)).thenReturn(hero(HERO_2, Color.BLUE));

        when(battle.getOppositeTeams(hero)).thenReturn(Collections.singletonList(team));
        when(battles.getBattle(1L)).thenReturn(battle);

        focusHandler.handle(1L, HERO_1);

        assertNotNull(hero.getFocus());
        assertEquals(HERO_2, hero.getFocus().getId());
    }


    @Test
    public void handle_noOppositeTeams() {
        IHero hero = hero(HERO_1, Color.RED);
        Battle battle = mock(Battle.class);

        when(battles.getBattle(1L)).thenReturn(battle);
        when(battle.getHero(HERO_1)).thenReturn(hero);
        when(battle.getOppositeTeams(hero)).thenReturn(Collections.emptyList());

        focusHandler.handle(1L, HERO_1);

        assertNull(hero.getFocus());
    }

    private IHero hero(long heroId, Color color) {
        IHero hero = new IHero();
        hero.setId(heroId);

        hero.setBattleId(1L);
        hero.setColor(color);

        hero.setName("hero_" + heroId);

        return hero;
    }

}