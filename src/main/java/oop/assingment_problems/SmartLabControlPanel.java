package oop.assingment_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Capability {
    String getName();
    boolean setValue(Object value);
    String getStatus(String deviceName);
}

class PowerCapability implements Capability {
    private boolean isOn = false;

    @Override
    public String getName() {
        return "Power";
    }

    @Override
    public boolean setValue(Object value) {
        if (value instanceof Boolean) {
            this.isOn = (Boolean) value;
            return true;
        } else if (value instanceof String) {
            String val = ((String) value).toUpperCase();
            if (val.equals("ON")) {
                this.isOn = true;
                return true;
            } else if (val.equals("OFF")) {
                this.isOn = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getStatus(String deviceName) {
        return deviceName + ": " + (isOn ? "ON" : "OFF") + ".";
    }
}

class BrightnessCapability implements Capability {
    private int brightness = 0;

    @Override
    public String getName() {
        return "Brightness";
    }

    @Override
    public boolean setValue(Object value) {
        int val = (Integer) value;
        if (val < 0 || val > 100) {
            System.out.println("Rejected: Brightness must be between 0% and 100%");
            return false;
        }
        this.brightness = val;
        return true;
    }

    @Override
    public String getStatus(String deviceName) {
        return deviceName + ": brightness set to " + brightness + "%.";
    }
}

class TemperatureCapability implements Capability {
    private int temperature = 20;

    @Override
    public String getName() {
        return "Temperature";
    }

    @Override
    public boolean setValue(Object value) {
        int val = (Integer) value;
        if (val < 16 || val > 30) {
            System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C");
            return false;
        }
        this.temperature = val;
        return true;
    }

    @Override
    public String getStatus(String deviceName) {
        return deviceName + ": temperature set to " + temperature + "°C.";
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
    }

    public boolean hasCapability(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }

    public boolean applyCapability(String capabilityName, Object value) {
        if (hasCapability(capabilityName)) {
            Capability cap = capabilities.get(capabilityName);
            boolean success = cap.setValue(value);
            if (success) {
                System.out.println(cap.getStatus(name));
            }
            return success;
        }
        return false;
    }
}

class SceneStep {
    private String capabilityName;
    private Object targetValue;

    public SceneStep(String capabilityName, Object targetValue) {
        this.capabilityName = capabilityName;
        this.targetValue = targetValue;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public Object getTargetValue() {
        return targetValue;
    }
}

class Scene {
    private String name;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(String capabilityName, Object targetValue) {
        steps.add(new SceneStep(capabilityName, targetValue));
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int actionsApplied = 0;

        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.hasCapability(step.getCapabilityName())) {
                    if (device.applyCapability(step.getCapabilityName(), step.getTargetValue())) {
                        actionsApplied++;
                    }
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + actionsApplied + " actions applied.");
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device labAC = new Device("Lab AC");
        labAC.addCapability(new PowerCapability());
        labAC.addCapability(new TemperatureCapability());

        Device ceilingLights = new Device("Ceiling Lights");
        ceilingLights.addCapability(new PowerCapability());
        ceilingLights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> labDevices = List.of(labAC, ceilingLights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", "ON");
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(labDevices);

        labAC.applyCapability("Temperature", 12);

        System.out.println("Projector: Brightness capability added.");
        projector.addCapability(new BrightnessCapability());
        projector.applyCapability("Brightness", 70);
    }
}