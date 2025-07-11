package com.tss.model;

class Table implements IFurniture {
    private final String style;

    public Table(String style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println("This is a " + style + " table.");
    }
}
