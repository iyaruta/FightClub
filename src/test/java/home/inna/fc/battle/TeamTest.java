package home.inna.fc.battle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamTest {

    private Team team;
    private List<IHero> heroes;

    @Before
    public void init() {
        team = new Team();
        IHero hero1 = mock(IHero.class);
        IHero hero2 = mock(IHero.class);
        IHero hero3 = mock(IHero.class);
        IHero hero4 = mock(IHero.class);

//        when(hero1.getId()).thenReturn(100L);
        when(hero2.getId()).thenReturn(200L);

        heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);
        heroes.add(hero3);
        heroes.add(hero4);

        team.setHeroes(heroes);
    }

    @Test
    public void getHero() {
        IHero result = team.getHero(200L);

        assertNotNull(result);
        verify(heroes.get(0)).getId();
        verify(heroes.get(1)).getId();
        verify(heroes.get(2), never()).getId();
        verify(heroes.get(3), never()).getId();

    }

}
