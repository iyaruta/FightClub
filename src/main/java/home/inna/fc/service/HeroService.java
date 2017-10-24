package home.inna.fc.service;

import home.inna.fc.data.Hero;
import home.inna.fc.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    @Autowired
    public HeroRepository heroRepository;

    public Hero findByName(String name){
        return heroRepository.findByName(name);
    }

    public Hero save(Long accountId, String name) {
        Hero hero = new Hero();
        hero.setAccountId(accountId);
        hero.setName(name);
        hero.setForce(3);
        hero.setAgility(3);
        hero.setInstinct(3);
        hero.setStamina(3);
        hero.setHealth(30);
        hero.setLevel(1);
        return heroRepository.save(hero);
    }

    public Hero getHero(Long accountId) {
        return heroRepository.findByAccountId(accountId);
    }
}
