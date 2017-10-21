package home.inna.fc.service;

import home.inna.fc.data.DuelRequest;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DuelRequestServiceTest {

    @Mock
    private DuelRequestRepository duelRequestRepository;

    @InjectMocks
    private DuelRequestService duelRequestService;

    private static final Integer PLAYER_1 = 100;
    private static final Integer PLAYER_2 = 200;


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
        duelRequestService.save(duelRequest(null));
        ArgumentCaptor<DuelRequest> captor = ArgumentCaptor.forClass(DuelRequest.class);
        verify(duelRequestRepository).save(captor.capture());
        DuelRequest value = captor.getValue();

        assertNotNull(value);
    }


    @Test
    public void accept() throws Exception {
        DuelRequest duelRequest = duelRequest(null);

        when(duelRequestRepository.findOne(1)).thenReturn(duelRequest);

        DuelRequest res = duelRequestService.accept(1, PLAYER_2);
        assertEquals(1, res.getId());
        assertEquals(PLAYER_1, res.getPlayerOne());
        assertEquals(PLAYER_2, res.getPlayerTwo());
        verify(duelRequestRepository).save(duelRequest);
    }

    @Test
    public void accept_NotReq() throws Exception {
        when(duelRequestRepository.findOne(1)).thenReturn(null);

        DuelRequest res = duelRequestService.accept(1, PLAYER_2);
        assertNull(res);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void accept_RequestOccupied() {
        DuelRequest duelRequest = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(duelRequest);

        duelRequestService.accept(1, PLAYER_2);

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject() {
        DuelRequest request = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(request);

        DuelRequest res = duelRequestService.reject(1, PLAYER_1);
        assertEquals(1, res.getId());
        assertEquals(PLAYER_1, res.getPlayerOne());
        assertNull(res.getPlayerTwo());

        verify(duelRequestRepository).save(any(DuelRequest.class));
    }

    @Test
    public void reject_AlreadyRefused() {
        DuelRequest request = duelRequest(null);
        when(duelRequestRepository.findOne(1)).thenReturn(request);

        DuelRequest res = duelRequestService.reject(1, PLAYER_1);
        assertEquals(1, res.getId());
        assertEquals(PLAYER_1, res.getPlayerOne());
        assertNull(res.getPlayerTwo());

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject_NoRequest() {
        when(duelRequestRepository.findOne(1)).thenReturn(null);

        DuelRequest res = duelRequestService.reject(1, PLAYER_1);
        assertNull(res);

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject_NotOwner() {
        DuelRequest req = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(req);
        duelRequestService.reject(1, 3);

        verify(duelRequestRepository, never()).delete(anyInt());

    }

    @Test
    public void cancel() {
        DuelRequest req = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(req);

        duelRequestService.cancel(1, PLAYER_1);

        verify(duelRequestRepository).delete(1);
    }

    @Test
    public void cancel_NoRequest() {
        when(duelRequestRepository.findOne(1)).thenReturn(null);
        verify(duelRequestRepository, never()).delete(anyInt());
    }

    @Test
    public void cancel_NotOwner() {
        DuelRequest req = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(req);
        duelRequestService.cancel(1, 2);

        verify(duelRequestRepository, never()).delete(anyInt());
    }

    @Test
    public void refuse() {
        DuelRequest request = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(request);
        duelRequestService.refuse(1, PLAYER_2);

        ArgumentCaptor<DuelRequest> captor = ArgumentCaptor.forClass(DuelRequest.class);
        verify(duelRequestRepository).save(captor.capture());
        DuelRequest res = captor.getValue();
        assertEquals(1, res.getId());
        assertEquals(PLAYER_1, res.getPlayerOne());
        assertNull(res.getPlayerTwo());
    }

    @Test
    public void refuse_NoRequest() {
        when(duelRequestRepository.findOne(1)).thenReturn(null);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void refuse_NotOwner() {
        DuelRequest request = duelRequest(PLAYER_2);
        when(duelRequestRepository.findOne(1)).thenReturn(request);
        duelRequestService.refuse(1, 3);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    private DuelRequest duelRequest(Integer player2) {
        DuelRequest duelRequest = new DuelRequest();
        duelRequest.setId(1);
        duelRequest.setPlayerOne(PLAYER_1);
        duelRequest.setPlayerTwo(player2);
        return duelRequest;
    }


}