package com.tss.strategy.model;

public class Consultant implements IRole {
    @Override
    public String getDescription() {
        return "Consultant";
    }

    @Override
    public String getResponsibility() {
        return "Write code";
    }
}
