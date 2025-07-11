package com.tss.model;

import java.util.Random;

public class FourWheeler implements IVehicle {

    @Override
    public String generateLiscenceNumber() {
        Random random = new Random();
        int number = random.nextInt(9000) + 1000; 
        return "FW" + number;
    }

}
