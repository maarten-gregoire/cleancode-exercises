package be.swsb.cleancode.ch9;

import org.junit.Before;
import org.junit.Test;

import static be.swsb.cleancode.ch9.EnvironmentController.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnvironmentControllerTest {

    private EnvironmentController controller;
    private MockControlHardware hw;

    @Before
    public void setUp() throws Exception {
        hw = new MockControlHardware();
        controller = new EnvironmentController(hw);
    }

    @Test
    public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
        tooHot();
        assertEquals("hBChl", hw.getState());
    }

    private void tooHot() {
        given(TOO_HOT);
    }

    private void given(double tooHot) {
        hw.setTemp(tooHot);
        controller.tic();
    }

    @Test
    public void turnOnHeaterAndBlowerIfTooCold() throws Exception {
        tooCold();
        assertEquals("HBchl", hw.getState());
    }

    private void tooCold() {
        given(TOO_COLD);
    }

    @Test
    public void turnOnHiTempAlarmAtThreshold() throws Exception {
        wayTooHot();
        assertEquals("hBCHl", hw.getState());
    }

    private void wayTooHot() {
        given(WAY_TOO_HOT);
    }

    @Test
    public void turnOnLoTempAlarmAtThreshold() throws Exception {
        wayTooCold();
        assertEquals("HBchL", hw.getState());
    }

    private void wayTooCold() {
        given(WAY_TOO_COLD);
    }

}