package com.tss.model;

class Chair implements IFurniture {
    private final String style;

    public Chair(String style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println("This is a " + style + " chair.");
    }
}