package org.thetomtomson.shadowslavecraft.data.player.custom.aspect;

import java.util.ArrayList;
import java.util.List;

public class AspectLegacy {
    private final String legacyName;
    private final List<LegacyStep> steps = new ArrayList<>();

    public AspectLegacy(String legacyName) {
        this.legacyName = legacyName;
    }

    public void addStep(String task, String rewardDescription) {
        steps.add(new LegacyStep(task, rewardDescription));
    }

    public List<LegacyStep> getSteps() { return steps; }

    public record LegacyStep(String task, String reward) {}
}