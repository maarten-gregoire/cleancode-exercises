package be.swsb.cleancode.ch9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class MockControlHardware implements ControlHardware {

    private double temp;
    private boolean heaterState;
    private boolean blowerState;
    private boolean coolerState;
    private boolean hiTempAlarm;
    private boolean loTempAlarm;

    void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public double getTemp() {
        return temp;
    }

    @Override
    public boolean heaterState() {
        return heaterState;
    }

    @Override
    public boolean blowerState() {
        return blowerState;
    }

    @Override
    public boolean coolerState() {
        return coolerState;
    }

    @Override
    public boolean hiTempAlarm() {
        return hiTempAlarm;
    }

    @Override
    public boolean loTempAlarm() {
        return loTempAlarm;
    }

    @Override
    public void turnOnBlower() {
        this.blowerState = true;
    }

    @Override
    public void turnOnHeater() {
        this.heaterState = true;
    }

    @Override
    public void turnOnLoTempAlarm() {
        this.loTempAlarm = true;
    }

    @Override
    public void turnOnCooler() {
        this.coolerState = true;
    }

    @Override
    public void turnOnHiTempAlarm() {
        this.hiTempAlarm = true;
    }

    @Override
    public void turnOffBlower() {
        this.blowerState = false;
    }

    @Override
    public void turnOffHeater() {
        this.heaterState = false;
    }

    @Override
    public void turnOffLoTempAlarm() {
        this.loTempAlarm = false;
    }

    @Override
    public void turnOffCooler() {
        this.coolerState = false;
    }

    @Override
    public void turnOffHiTempAlarm() {
        this.hiTempAlarm = false;
    }

    void assertOn(HardwareState... hardwareStates) {
        for (HardwareState hardwareState: hardwareStates) {
            assertTrue(getState(hardwareState));
        }
    }

    void assertOff(HardwareState... hardwareStates) {
        for (HardwareState stateState: hardwareStates) {
            assertFalse(getState(stateState));
        }
    }

    private boolean getState(HardwareState hardwareStateState) {
        switch (hardwareStateState) {
            case BLOWER_STATE:
                return this.blowerState;
            case COOLER_STATE:
                return this.coolerState;
            case HEATER_STATE:
                return this.heaterState;
            case HI_TEMP_ALARM:
                return this.hiTempAlarm;
            case LO_TEMP_ALARM:
                return this.loTempAlarm;
            default:
                throw new IllegalStateException("Unknown state");
        }
    }
}
