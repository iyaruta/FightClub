package home.inna.fc.controller;

import home.inna.fc.auth.HeroAuth;
import home.inna.fc.data.DuelRequest;
import home.inna.fc.dto.Timeout;
import home.inna.fc.service.DuelRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duel")
public class DuelRequestController {

    @Autowired
    private DuelRequestService duelRequestService;

    @GetMapping
    public List<DuelRequest> findAll() {
        return duelRequestService.findAll();
    }

    @PostMapping
    public DuelRequest save(@AuthenticationPrincipal HeroAuth hero) {
        DuelRequest duelRequest = new DuelRequest();
        duelRequest.setHeroOne(hero.getId());
        duelRequest.setTimeout(Timeout.ONE.getValue());
        return duelRequestService.save(duelRequest);
    }

    @DeleteMapping(value = "/{id}")
    public void cancel(@PathVariable Long id, @AuthenticationPrincipal HeroAuth hero) {
        duelRequestService.cancel(id, hero.getId());
    }

    @GetMapping(value = "/{id}/accept")
    public DuelRequest accept(@PathVariable Long id, @AuthenticationPrincipal HeroAuth hero) {
        return duelRequestService.accept(id, hero.getId());
    }

    @GetMapping(value = "/{id}/reject")
    public DuelRequest reject(@PathVariable Long id, @AuthenticationPrincipal HeroAuth hero) {
        return duelRequestService.reject(id, hero.getId());
    }

    @GetMapping(value = "/{id}/refuse")
    public void refuse(@PathVariable Long id, @AuthenticationPrincipal HeroAuth hero) {
        duelRequestService.refuse(id, hero.getId());
    }
}
