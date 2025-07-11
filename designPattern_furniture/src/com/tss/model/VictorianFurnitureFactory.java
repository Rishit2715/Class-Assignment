package com.tss.model;

public class VictorianFurnitureFactory implements IFurnitureFactory {
    @Override
    public IFurniture createChair() {
        return new Chair("Victorian");
    }

    @Override
    public IFurniture createSofa() {
        return new Sofa("Victorian");
    }

    @Override
    public IFurniture createTable() {
        return new Table("Victorian");
    }
}