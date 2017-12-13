package home.inna.fc.controller;

import home.inna.fc.auth.HeroAuth;
import home.inna.fc.battle.Attack;
import home.inna.fc.battle.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attack")
public class AttackController {

    @Autowired
    private AttackService attackService;

    @PostMapping
    public void attack(@RequestBody Attack attack, @AuthenticationPrincipal HeroAuth hero) {
        attackService.attack(hero.getId(), attack);

    }
}
