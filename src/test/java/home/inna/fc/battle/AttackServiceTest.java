package home.inna.fc.battle;

import home.inna.fc.battle.handler.FocusHandler;
import home.inna.fc.repository.HeroBattleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttackServiceTest {

    public static final Long HERO_1 = 100L;
    public static final Long HERO_2 = 200L;

    @Mock
    private HeroBattleRepository heroRepository;

    @Mock
    private Battles battles;

    @Mock
    private FocusHandler focusHandler;

    @InjectMocks
    private AttackService attackService;

    @Test
    public void attack() {
        Battle battle = mock(Battle.class);
        when(battles.getBattle(1L)).thenReturn(battle);

        when(heroRepository.findByHero(HERO_1)).thenReturn(1L);

        IHero hero = mock(IHero.class);
        IHero focusHero = mock(IHero.class);
        when(hero.getId()).thenReturn(HERO_1);
        when(hero.getFocus()).thenReturn(focusHero);
        when(focusHero.getId()).thenReturn(HERO_2);

        when(battle.getHero(HERO_1)).thenReturn(hero);

        Attack attack = mock(Attack.class);
        attackService.attack(HERO_1, attack);

        verify(hero).attack(attack);
        verify(focusHandler).handle(1L, HERO_1);
        verify(focusHandler).handle(1L, HERO_2);
    }
}
