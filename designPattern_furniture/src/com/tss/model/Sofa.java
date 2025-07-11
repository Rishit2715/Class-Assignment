package com.tss.model;


class Sofa implements IFurniture {
    private final String style;

    public Sofa(String style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println("This is a " + style + " sofa.");
    }
}