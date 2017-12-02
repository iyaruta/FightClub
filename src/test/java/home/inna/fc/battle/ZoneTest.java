package home.inna.fc.battle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ZoneTest {

    @Test
    public void hasBlocked_directZones() throws Exception {
        Zone attack = Zone.HEAD;
        Zone block = Zone.HEAD;
        boolean result = block.hasBlocked(attack, 2);
        assertTrue(result);
    }

    @Test
    public void hasBlocked_coveredZones() throws Exception {
        Zone attack = Zone.HEAD;
        Zone block = Zone.LEGS;
        boolean result = block.hasBlocked(attack, 2);
        assertTrue(result);
    }

    @Test
    public void hasBlocked_missedZone() throws Exception {
        Zone attack = Zone.HEAD;
        Zone block = Zone.STOMACH;
        boolean result = block.hasBlocked(attack, 2);
        assertFalse(result);
    }

    @Test
    public void hasBlocked_allZoneCovered() throws Exception {
        Zone attack = Zone.CHEST;
        Zone block = Zone.STOMACH;
        boolean result = block.hasBlocked(attack, 5);
        assertTrue(result);
    }

    @Test
    public void hasBlocked_noBlockZones() throws Exception {
        Zone attack = Zone.HEAD;
        Zone block = Zone.HEAD;
        boolean result = block.hasBlocked(attack, 0);
        assertFalse(result);
    }

    @Test
    public void hasBlocked_oneBlockZones() throws Exception {
        Zone attack = Zone.BELT;
        Zone block = Zone.BELT;
        boolean result = block.hasBlocked(attack, 1);
        assertTrue(result);
    }

}