package home.inna.fc.service;

import home.inna.fc.data.Hero;
import home.inna.fc.repository.HeroRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //обязательно для мокито тестов, что бы включился мокито-фреймворк
public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService = new HeroService();

    @Test
    public void testFindByName() throws Exception {
        when(heroRepository.findByName("test_hero_name")).thenReturn(getPlayer());


        Hero hero = heroService.findByName("test_hero_name");
        assertNotNull(hero);
        assertEquals(1, hero.getAbility());
        assertEquals(4, hero.getForce());
        assertEquals(3, hero.getAgility());
        assertEquals(3, hero.getInstinct());
        assertEquals(3, hero.getStamina());
        assertEquals(1, hero.getLevel());
        assertEquals(0, hero.getHealth());
        assertEquals(0, hero.getExperience());

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(heroRepository).findByName(captor.capture());
        String value = captor.getValue();

        assertEquals("test_hero_name", value);
    }

    @Test
    public void testSave() throws Exception {
        heroService.save(1L, "test_name");

        ArgumentCaptor<Hero> captor = ArgumentCaptor.forClass(Hero.class);
        verify(heroRepository).save(captor.capture());
        Hero value = captor.getValue();

        assertNotNull(captor);
        assertEquals("test_name", value.getName());
        assertEquals(Long.valueOf(1), value.getAccountId());

    }

    private Hero getPlayer() {
        Hero hero = new Hero();
        hero.setName("test_hero_name");
        hero.setForce(4);
        hero.setAgility(3);
        hero.setInstinct(3);
        hero.setStamina(3);
        hero.setLevel(1);
        hero.setHealth(0);
        hero.setExperience(0);
        hero.setAbility(1);
        return hero;
    }
}