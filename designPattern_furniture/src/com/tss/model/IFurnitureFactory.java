package com.tss.model;

public interface IFurnitureFactory {
    IFurniture createChair();
    IFurniture createSofa();
    IFurniture createTable();
}