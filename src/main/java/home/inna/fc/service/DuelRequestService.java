package home.inna.fc.service;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.repository.DuelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuelRequestService {

    @Autowired
    private DuelRequestRepository duelRequestRepository;


    public List<DuelRequest> findAll() {
        return duelRequestRepository.findAll();
    }

    public DuelRequest save(DuelRequest duelRequest) {
        return duelRequestRepository.save(duelRequest);

    }

    public DuelRequest accept(int requestId, int playerId) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && request.getPlayerTwo() == null) {
            request.setPlayerTwo(playerId);
            duelRequestRepository.save(request);
        }

        return request;
    }

    public DuelRequest reject(int requestId) {
        DuelRequest request = duelRequestRepository.findOne(requestId);
        if (request != null && request.getPlayerTwo() != null) {
            request.setPlayerTwo(null);
            duelRequestRepository.save(request);
        }

        return request;
    }

}
