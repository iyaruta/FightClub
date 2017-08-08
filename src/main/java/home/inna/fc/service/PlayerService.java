package home.inna.fc.service;

import home.inna.fc.data.Player;
import home.inna.fc.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

    @Autowired
    public PlayerRepository playerRepository;

    @Autowired
    public Player findByName(String name){
        return playerRepository.findByName(name);
    }

    @Autowired
    public Player save(String name){
        Player player = new Player();
        player.setName(name);
        player.setForce(3);
        player.setAgility(3);
        player.setInstinct(3);
        player.setStamina(3);
        player.setHealth(30);
        player.setLevel(1);
        return playerRepository.save(player);
    }

}
