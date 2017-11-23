package home.inna.fc.service;

import home.inna.fc.entity.Experience;
import home.inna.fc.entity.Hero;
import home.inna.fc.repository.ExperienceRepository;
import home.inna.fc.repository.HeroRepository;
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
    private HeroRepository heroRepository;

    @InjectMocks
    private ExperienceService experienceService;

    private Hero hero;

    @Before
    public void setup() {
        hero = getHero();
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);
    }

    @Test
    public void testRecalculate_ZeroExp() {
        experienceService.recalculate(this.hero, 0);

        verify(experienceRepository, never()).findByRange(anyInt(), anyInt());
        verify(heroRepository, never()).save(any(Hero.class));
    }

    @Test
    public void testRecalculate_MinusExp() {
        experienceService.recalculate(this.hero, Integer.MIN_VALUE);

        verify(experienceRepository, never()).findByRange(anyInt(), anyInt());
        verify(heroRepository, never()).save(any(Hero.class));
    }

    @Test
    public void testRecalculate_OneAbility() {
        List<Experience> experienceList = Collections.singletonList(experience(1, 0));
        when(experienceRepository.findByRange(3, 93)).thenReturn(experienceList);

        Hero hero = experienceService.recalculate(this.hero, 90);
        assertNotNull(hero);
        assertEquals(93, hero.getExperience());
        assertEquals(6, hero.getLevel());
        assertEquals(4, hero.getAbility());

        verify(heroRepository).save(hero);
    }

    @Test
    public void testRecalculate_TwoLevel() {
        List<Experience> experienceList = Arrays.asList(experience(0, 1), experience(0, 1));
        when(experienceRepository.findByRange(3, 1003)).thenReturn(experienceList);

        Hero hero = experienceService.recalculate(this.hero, 1000);
        assertNotNull(hero);
        assertEquals(1003, hero.getExperience());
        assertEquals(8, hero.getLevel());

        verify(heroRepository).save(hero);
    }

    @Test
    public void testExperience() {
        List<Experience> experienceList = Collections.emptyList();
        when(experienceRepository.findByRange(3, 15)).thenReturn(experienceList);

        Hero hero = experienceService.recalculate(this.hero, 12);
        assertNotNull(hero);
        assertEquals(15, hero.getExperience());
        assertEquals(6, hero.getLevel());
        assertEquals(3, hero.getAbility());

        verify(heroRepository).save(hero);
    }

    @Test
    public void testRecalculate() throws Exception {
        List<Experience> experienceList = Arrays.asList(experience(1, 0), experience(1, 0), experience(1, 1));
        when(experienceRepository.findByRange(3, 93)).thenReturn(experienceList);

        Hero hero = experienceService.recalculate(this.hero, 90);

        assertEquals("test_player_name", hero.getName());
        assertEquals(6, hero.getAbility());
        assertEquals(4, hero.getForce());
        assertEquals(3, hero.getAgility());
        assertEquals(3, hero.getInstinct());
        assertEquals(3, hero.getStamina());
        assertEquals(7, hero.getLevel());
        assertEquals(0, hero.getHealth());
        assertEquals(93, hero.getExperience());

        verify(heroRepository).save(hero);
    }

    private Hero getHero() {
        Hero hero = new Hero();
        hero.setName("test_player_name");
        hero.setAbility(3);
        hero.setForce(4);
        hero.setAgility(3);
        hero.setInstinct(3);
        hero.setStamina(3);
        hero.setLevel(6);
        hero.setHealth(0);
        hero.setExperience(3);
        return hero;
    }

    private Experience experience(int ability, int level) {
        Experience exp = new Experience();
        exp.setAbility(ability);
        exp.setLevel(level);
        return exp;
    }

}