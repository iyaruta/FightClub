package home.inna.fc.service;

import home.inna.fc.battle.BattleBuilder;
import home.inna.fc.entity.DuelRequest;
import home.inna.fc.exception.ValidationException;
import home.inna.fc.repository.DuelRequestRepository;
import home.inna.fc.repository.HeroBattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

@Service
public class DuelRequestService {

    @Autowired
    private BattleBuilder battleBuilder;

    @Autowired
    private DuelRequestRepository duelRequestRepository;

    @Autowired
    private HeroBattleRepository heroBattleRepository;

    public List<DuelRequest> findAll() {
        return duelRequestRepository.findAll();
    }

    public DuelRequest save(DuelRequest duelRequest) {
        if (heroBattleRepository.findByHero(duelRequest.getHeroOne()) != null) {
            throw new ValidationException("Hero already in battle");
        }

        duelRequest.setDataTime(LocalDateTime.now());
        return duelRequestRepository.save(duelRequest);

    }

    public DuelRequest accept(Long requestId, Long hero2) {
        DuelRequest request = getRequest(requestId, hero2, r -> isNull(r.getHeroTwo()));
        request.setHeroTwo(hero2);
        duelRequestRepository.save(request);
        return request;
    }

    public DuelRequest reject(Long requestId, Long owner) {
        DuelRequest request = getRequest(requestId, owner, req -> req.getHeroTwo() != null && Objects.equals(req.getHeroOne(), owner));
        request.setHeroTwo(null);
        duelRequestRepository.save(request);
        return request;
    }

    public void cancel(Long requestId, Long owner) {
        DuelRequest request = getRequest(requestId, owner, dr -> Objects.equals(dr.getHeroOne(), owner));
        duelRequestRepository.delete(request);
    }

    public void refuse(Long requestId, Long hero2) {
        DuelRequest request = getRequest(requestId, hero2, dr -> Objects.equals(dr.getHeroTwo(), hero2));
        request.setHeroTwo(null);
        duelRequestRepository.save(request);
    }

    public void start(Long requestId, Long owner) {
        DuelRequest request = getRequest(requestId, owner, dr -> dr.getHeroTwo() != null);
        battleBuilder.build(request);

    }

    private DuelRequest getRequest(Long requestId, Long heroId, Predicate<DuelRequest> predicate) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request == null) {
            throw new ValidationException("No duel request with id " + requestId);
        }

        if (!predicate.test(request)) {
            throw new ValidationException("Permission denied");
        }

        Long heroInBattle = heroBattleRepository.findByHero(heroId);
        if (heroInBattle != null) {
            throw new ValidationException("Hero has already in battle");
        }

        return request;

    }

}
