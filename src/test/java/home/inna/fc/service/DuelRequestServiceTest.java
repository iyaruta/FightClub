package home.inna.fc.service;

import home.inna.fc.data.DuelRequest;
import home.inna.fc.data.Experience;
import home.inna.fc.repository.DuelRequestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DuelRequestServiceTest {

    @Mock
    private DuelRequestRepository duelRequestRepository;

    @InjectMocks
    private DuelRequestService duelRequestService;

    @Test
    public void findAll() throws Exception {
        List<DuelRequest> duelRequestList = Collections.emptyList();
        when(duelRequestRepository.findAll()).thenReturn(duelRequestList);
        List<DuelRequest> list = duelRequestService.findAll();
        assertNotNull(list);
        assertEquals(0, list.size());


        verify(duelRequestRepository).findAll();


    }

    @Test
    public void save() throws Exception {
        duelRequestService.save(duelRequest());

        ArgumentCaptor<DuelRequest> captor = ArgumentCaptor.forClass(DuelRequest.class);
        verify(duelRequestRepository).save(captor.capture());
        DuelRequest value = captor.getValue();

        assertNotNull(value);
    }

    private DuelRequest duelRequest(){
        DuelRequest dR = new DuelRequest();
        dR.setPlayerTwo(1);
        return dR;
    }



}