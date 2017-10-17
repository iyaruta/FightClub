package home.inna.fc.controller;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.dto.Timeout;
import home.inna.fc.service.DuelRequestService;
import home.inna.fc.service.PlayerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.util.calendar.BaseCalendar;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/duel")
public class DuelRequestController {

    @Autowired
    private DuelRequestService duelRequestService;

    @Autowired
    private PlayerProvider playerProvider;

    @RequestMapping(method = RequestMethod.GET)
    public List<DuelRequest> findAll() {
        return duelRequestService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public DuelRequest save() {
        DuelRequest duelRequest = new DuelRequest();
        duelRequest.setPlayerTwo(playerProvider.getCurrentPlayer().getId());
        duelRequest.setTimeOut(Timeout.ONE.getValue());
        duelRequest.setDataTime(LocalDateTime.now());
        return duelRequestService.save(duelRequest);

    }



}
