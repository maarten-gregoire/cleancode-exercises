package be.swsb.cleancode.ch9;

import org.junit.Before;
import org.junit.Test;

import static be.swsb.cleancode.ch9.EnvironmentController.*;
import static be.swsb.cleancode.ch9.HardwareState.*;

public class EnvironmentControllerTest {

    private EnvironmentController controller;
    private MockControlHardware controlHardware;

    @Before
    public void setUp() throws Exception {
        controlHardware = new MockControlHardware();
        controller = new EnvironmentController(controlHardware);
    }

    @Test
    public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
        givenTemp(TOO_HOT);
        controlHardware.assertOn(BLOWER_STATE, COOLER_STATE);
        controlHardware.assertOff(HEATER_STATE, HI_TEMP_ALARM, LO_TEMP_ALARM);
    }

    @Test
    public void turnOnHeaterAndBlowerIfTooCold() throws Exception {
        givenTemp(TOO_COLD);
        controlHardware.assertOn(HEATER_STATE, BLOWER_STATE);
        controlHardware.assertOff(COOLER_STATE, HI_TEMP_ALARM, LO_TEMP_ALARM);
    }

    @Test
    public void turnOnHiTempAlarmAtThreshold() throws Exception {
        givenTemp(WAY_TOO_HOT);
        controlHardware.assertOn(BLOWER_STATE, COOLER_STATE, HI_TEMP_ALARM);
        controlHardware.assertOff(HEATER_STATE, LO_TEMP_ALARM);
    }

    @Test
    public void turnOnLoTempAlarmAtThreshold() throws Exception {
        givenTemp(WAY_TOO_COLD);
        controlHardware.assertOn(HEATER_STATE, BLOWER_STATE, LO_TEMP_ALARM);
        controlHardware.assertOff(COOLER_STATE, HI_TEMP_ALARM);
    }

    private void givenTemp(double wayTooCold) {
        controlHardware.setTemp(wayTooCold);
        controller.tic();
    }

}