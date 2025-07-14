package com.tss.strategy.model;

public class TechLead implements IRole {
    @Override
    public String getDescription() {
        return "Tech Lead";
    }

    @Override
    public String getResponsibility() {
        return "Assign work";
    }
}
