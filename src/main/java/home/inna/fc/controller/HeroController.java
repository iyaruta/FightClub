package home.inna.fc.controller;

import home.inna.fc.data.Hero;
import home.inna.fc.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String name) {
        Hero hero = heroService.findByName(name);
        return ResponseEntity.ok(hero);
    }


}