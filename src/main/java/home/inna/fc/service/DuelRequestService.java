package home.inna.fc.service;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.repository.DuelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@CrossOrigin
public class DuelRequestService {

    @Autowired
    private DuelRequestRepository duelRequestRepository;


    public List<DuelRequest> findAll() {
        return duelRequestRepository.findAll();
    }

    public DuelRequest save(DuelRequest duelRequest) {
        duelRequest.setDataTime(LocalDateTime.now());
        return duelRequestRepository.save(duelRequest);

    }

    public DuelRequest accept(Long requestId, Long heroId) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && request.getHeroTwo() == null) {
            request.setHeroTwo(heroId);
            duelRequestRepository.save(request);
        }

        return request;
    }

    public DuelRequest reject(Long requestId, Long owner) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && request.getHeroTwo() != null && Objects.equals(request.getHeroOne(), owner)) {
            request.setHeroTwo(null);
            duelRequestRepository.save(request);
        }

        return request;
    }

    public void cancel(Long requestId, Long owner) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && Objects.equals(request.getHeroOne(), owner)) {
            duelRequestRepository.delete(requestId);
        }
    }

    public void refuse(Long requestId, Long hero2) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && Objects.equals(request.getHeroTwo(), hero2)) {
            request.setHeroTwo(null);
            duelRequestRepository.save(request);
        }
    }

}
