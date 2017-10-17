package home.inna.fc.controller;

import home.inna.fc.data.Player;
import home.inna.fc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String name) {
        Player player = playerService.findByName(name);
        if (player == null){
            playerService.save(name);
        }
        return ResponseEntity.ok(player);
    }



}