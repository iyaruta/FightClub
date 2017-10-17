package home.inna.fc.service;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.data.Player;
import home.inna.fc.repository.DuelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuelRequestService {

    @Autowired
    private DuelRequestRepository duelRequestRepository;



    public List<DuelRequest> findAll(){
        return duelRequestRepository.findAll();
    }

    public DuelRequest save(DuelRequest duelRequest) {
       return duelRequestRepository.save(duelRequest);

    }
}

