package home.inna.fc.battle;

import home.inna.fc.data.DuelRequest;
import org.springframework.stereotype.Component;

@Component
public class BattleBuilder {


    public Battle build(DuelRequest request) {
        return null;
    }

/*    private Team team() {
        Team team = new Team();
        List<IHero> iHeroList = new ArrayList<>();
        iHeroList.add(iHero();


        return null;
    }

    private IHero iHero(Hero hero) {

        IHero iHero = new IHero();
        iHero.setId(hero.getId());
        iHero.setBattleId(build(request).getId());
        iHero.setName(hero.getName());
        iHero.setAbility(hero.getAbility());
        iHero.setForce(hero.getForce());
        iHero.setAgility(hero.getAgility());
        iHero.setInstinct(hero.getInstinct());
        iHero.setStamina(hero.getStamina());
        iHero.setLevel(hero.getLevel());
        iHero.setHealth(hero.getHealth());
        iHero.setCurrentHealth(0);

        return iHero;
    }
*/
}
