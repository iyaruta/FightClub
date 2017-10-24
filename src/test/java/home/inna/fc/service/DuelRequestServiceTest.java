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

    private static final Long HERO_1 = 100L;
    private static final Long HERO_2 = 200L;


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

        when(duelRequestRepository.findOne(1L)).thenReturn(duelRequest);

        DuelRequest res = duelRequestService.accept(1L, HERO_2);
        assertEquals(Long.valueOf(1), res.getId());
        assertEquals(HERO_1, res.getHeroOne());
        assertEquals(HERO_2, res.getHeroTwo());
        verify(duelRequestRepository).save(duelRequest);
    }

    @Test
    public void accept_NotReq() throws Exception {
        when(duelRequestRepository.findOne(1L)).thenReturn(null);

        DuelRequest res = duelRequestService.accept(1L, HERO_2);
        assertNull(res);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void accept_RequestOccupied() {
        DuelRequest duelRequest = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(duelRequest);

        duelRequestService.accept(1L, HERO_2);

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject() {
        DuelRequest request = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(request);

        DuelRequest res = duelRequestService.reject(1L, HERO_1);
        assertEquals(Long.valueOf(1), res.getId());
        assertEquals(HERO_1, res.getHeroOne());
        assertNull(res.getHeroTwo());

        verify(duelRequestRepository).save(any(DuelRequest.class));
    }

    @Test
    public void reject_AlreadyRefused() {
        DuelRequest request = duelRequest(null);
        when(duelRequestRepository.findOne(1L)).thenReturn(request);

        DuelRequest res = duelRequestService.reject(1L, HERO_1);
        assertEquals(Long.valueOf(1), res.getId());
        assertEquals(HERO_1, res.getHeroOne());
        assertNull(res.getHeroTwo());

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject_NoRequest() {
        when(duelRequestRepository.findOne(1L)).thenReturn(null);

        DuelRequest res = duelRequestService.reject(1L, HERO_1);
        assertNull(res);

        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void reject_NotOwner() {
        DuelRequest req = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(req);
        duelRequestService.reject(1L, 3L);

        verify(duelRequestRepository, never()).delete(anyLong());

    }

    @Test
    public void cancel() {
        DuelRequest req = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(req);

        duelRequestService.cancel(1L, HERO_1);

        verify(duelRequestRepository).delete(1L);
    }

    @Test
    public void cancel_NoRequest() {
        when(duelRequestRepository.findOne(1L)).thenReturn(null);
        verify(duelRequestRepository, never()).delete(anyLong());
    }

    @Test
    public void cancel_NotOwner() {
        DuelRequest req = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(req);
        duelRequestService.cancel(1L, 2L);

        verify(duelRequestRepository, never()).delete(anyLong());
    }

    @Test
    public void refuse() {
        DuelRequest request = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(request);
        duelRequestService.refuse(1L, HERO_2);

        ArgumentCaptor<DuelRequest> captor = ArgumentCaptor.forClass(DuelRequest.class);
        verify(duelRequestRepository).save(captor.capture());
        DuelRequest res = captor.getValue();
        assertEquals(Long.valueOf(1), res.getId());
        assertEquals(HERO_1, res.getHeroOne());
        assertNull(res.getHeroTwo());
    }

    @Test
    public void refuse_NoRequest() {
        when(duelRequestRepository.findOne(1L)).thenReturn(null);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    @Test
    public void refuse_NotOwner() {
        DuelRequest request = duelRequest(HERO_2);
        when(duelRequestRepository.findOne(1L)).thenReturn(request);
        duelRequestService.refuse(1L, 3L);
        verify(duelRequestRepository, never()).save(any(DuelRequest.class));
    }

    private DuelRequest duelRequest(Long hero2) {
        DuelRequest duelRequest = new DuelRequest();
        duelRequest.setId(1L);
        duelRequest.setHeroOne(HERO_1);
        duelRequest.setHeroTwo(hero2);
        return duelRequest;
    }


}