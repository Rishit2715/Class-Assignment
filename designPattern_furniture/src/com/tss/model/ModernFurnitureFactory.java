package com.tss.model;

public class ModernFurnitureFactory implements IFurnitureFactory {
    @Override
    public IFurniture createChair() {
        return new Chair("Modern");
    }

    @Override
    public IFurniture createSofa() {
        return new Sofa("Modern");
    }

    @Override
    public IFurniture createTable() {
        return new Table("Modern");
    }
}
