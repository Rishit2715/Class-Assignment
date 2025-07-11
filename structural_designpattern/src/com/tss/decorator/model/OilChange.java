package com.tss.decorator.model;

public class OilChange extends CarServiceDecorator {
    public OilChange(ICarService obj) {
        super(obj);
    }

    @Override
    public double getCost() {
        return 500 + super.getCost();
    }
}
