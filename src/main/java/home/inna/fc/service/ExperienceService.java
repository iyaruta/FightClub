package home.inna.fc.service;

import home.inna.fc.data.Experience;
import home.inna.fc.data.Player;
import home.inna.fc.repository.ExperienceRepository;
import home.inna.fc.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public ExperienceRepository experienceRepository;

    public Player recalculate(Player player, int exp){
        if (exp <= 0) {
            return player;
        }

        int currentExp = player.getExperience() + exp;
        List<Experience> experienceList = experienceRepository.findByRange(player.getExperience(), currentExp);
        for (Experience experience: experienceList ) {
            player.setLevel(player.getLevel() + experience.getLevel());
            player.setAbility(player.getAbility() + experience.getAbility());
        }
        player.setExperience(currentExp);
        return playerRepository.save(player);
    }

    public List<Experience> findAll(){
        return experienceRepository.findAll();
    }


}
