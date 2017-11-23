package home.inna.fc.controller;

import home.inna.fc.repository.HeroBattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HeroBattleRepository heroBattleRepository;

    @GetMapping(value = "/{id}")
    public Long cancel(@PathVariable Long id) {
        return heroBattleRepository.findByHero(id);
    }

}
