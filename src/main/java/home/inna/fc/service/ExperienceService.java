package home.inna.fc.service;

import home.inna.fc.entity.Experience;
import home.inna.fc.entity.Hero;
import home.inna.fc.repository.ExperienceRepository;
import home.inna.fc.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    public HeroRepository heroRepository;

    @Autowired
    public ExperienceRepository experienceRepository;

    public Hero recalculate(Hero hero, int exp) {
        if (exp <= 0) {
            return hero;
        }

        int currentExp = hero.getExperience() + exp;
        List<Experience> experienceList = experienceRepository.findByRange(hero.getExperience(), currentExp);
        for (Experience experience : experienceList) {
            hero.setLevel(hero.getLevel() + experience.getLevel());
            hero.setAbility(hero.getAbility() + experience.getAbility());
        }
        hero.setExperience(currentExp);
        return heroRepository.save(hero);
    }

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }


}
