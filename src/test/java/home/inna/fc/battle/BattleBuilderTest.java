package home.inna.fc.battle;

import home.inna.fc.dto.Color;
import home.inna.fc.entity.BattleEntity;
import home.inna.fc.entity.DuelRequest;
import home.inna.fc.entity.Hero;
import home.inna.fc.repository.BattleRepository;
import home.inna.fc.repository.HeroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BattleBuilderTest {

    @Mock
    private BattleRepository battleRepository;

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private Battles battles;

    @InjectMocks
    private BattleBuilder battleBuilder;

    @Test
    public void build() throws Exception {
        DuelRequest duelRequest = duelRequest();

        when(battleRepository.save(any(BattleEntity.class))).then(answer -> {
            BattleEntity entity = answer.getArgumentAt(0, BattleEntity.class);
            entity.setId(5L);
            return answer;
        });

        when(heroRepository.findOne(duelRequest.getHeroOne())).thenReturn(hero(duelRequest.getHeroOne()));
        when(heroRepository.findOne(duelRequest.getHeroTwo())).thenReturn(hero(duelRequest.getHeroTwo()));

        Battle battle = battleBuilder.build(duelRequest);
        assertBattle(battle);
        verify(battles).add(battle);

        Map<Color, Team> teams = battle.getTeams();

        assertNotNull(teams);
        assertEquals(2, teams.size());

        Team redTeam = teams.get(Color.RED);
        assertNotNull(redTeam);
        assertNotNull(redTeam.getHeroes());
        assertEquals(Color.RED, redTeam.getColor());
        assertEquals(redTeam.getBattleId(), battle.getId());

        List<IHero> redHeroes = redTeam.getHeroes();
        assertEquals(1, redHeroes.size());

        IHero redHero = redHeroes.get(0);
        assertEquals(Color.RED, redHero.getColor());
        assertEquals(Long.valueOf(100), redHero.getId());
        assertEquals("hero_100", redHero.getName());
        assertEquals(101, redHero.getAbility());
        assertEquals(102, redHero.getForce());
        assertEquals(103, redHero.getAgility());
        assertEquals(104, redHero.getInstinct());
        assertEquals(105, redHero.getStamina());
        assertEquals(106, redHero.getLevel());
        assertEquals(107, redHero.getHealth());
        assertEquals(107, redHero.getCurrentHealth());


        Team blueTeam = teams.get(Color.BLUE);
        assertNotNull(blueTeam);
        assertNotNull(blueTeam.getHeroes());
        assertEquals(Color.BLUE, blueTeam.getColor());

        List<IHero> blueHeroes = blueTeam.getHeroes();
        assertEquals(1, blueHeroes.size());

        IHero blueHero = blueHeroes.get(0);
        assertEquals(Color.BLUE, blueHero.getColor());
        assertEquals(Long.valueOf(200), blueHero.getId());
        assertEquals("hero_200", blueHero.getName());
        assertEquals(201, blueHero.getAbility());
        assertEquals(202, blueHero.getForce());
        assertEquals(203, blueHero.getAgility());
        assertEquals(204, blueHero.getInstinct());
        assertEquals(205, blueHero.getStamina());
        assertEquals(206, blueHero.getLevel());
        assertEquals(207, blueHero.getHealth());
        assertEquals(207, blueHero.getCurrentHealth());
    }

    private void assertBattle(Battle battle) {
        assertNotNull(battle);
        assertNotNull(battle.getDateTime());
        assertEquals(Long.valueOf(5), battle.getId());
        assertEquals(120, battle.getTimeout());

        ArgumentCaptor<BattleEntity> captor = ArgumentCaptor.forClass(BattleEntity.class);
        verify(battleRepository).save(captor.capture());
        BattleEntity battleEntity = captor.getValue();

        assertNotNull(battleEntity);
        assertEquals(120, battleEntity.getTimeout());
    }

    private DuelRequest duelRequest() {
        DuelRequest duelRequest = new DuelRequest();
        duelRequest.setId(1L);
        duelRequest.setHeroOne(100L);
        duelRequest.setHeroTwo(200L);
        duelRequest.setDataTime(LocalDateTime.now());
        duelRequest.setTimeout(120);
        return duelRequest;
    }

    private Hero hero(long heroId) {
        int koef = (int) heroId;
        Hero hero = new Hero();
        hero.setId(heroId);
        hero.setName("hero_" + heroId);
        hero.setAbility(++koef);
        hero.setForce(++koef);
        hero.setAgility(++koef);
        hero.setInstinct(++koef);
        hero.setStamina(++koef);
        hero.setLevel(++koef);
        hero.setHealth(++koef);
        return hero;
    }


}