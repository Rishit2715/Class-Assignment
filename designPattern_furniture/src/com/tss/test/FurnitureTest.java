package com.tss.test;
import java.util.Scanner;

import com.tss.model.IFurniture;
import com.tss.model.IFurnitureFactory;
import com.tss.model.ModernFurnitureFactory;
import com.tss.model.VictorianFurnitureFactory;

public class FurnitureTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a style: ");
        System.out.println("1. Modern");
        System.out.println("2. Victorian");
        int styleChoice = scanner.nextInt();
        scanner.nextLine(); 

        IFurnitureFactory factory;

        switch (styleChoice) {
            case 1:
                factory = new ModernFurnitureFactory();
                break;
            case 2:
                factory = new VictorianFurnitureFactory();
                break;
            default:
                System.out.println("Invalid style choice.");
                scanner.close();
                return;
        }

        System.out.println("Select furniture to create: ");
        System.out.println("1. Chair");
        System.out.println("2. Sofa");
        System.out.println("3. Table");
        int furnitureChoice = scanner.nextInt();

        IFurniture furniture;

        switch (furnitureChoice) {
            case 1:
                furniture = factory.createChair();
                break;
            case 2:
                furniture = factory.createSofa();
                break;
            case 3:
                furniture = factory.createTable();
                break;
            default:
                System.out.println("Invalid furniture choice.");
                scanner.close();
                return;
        }

        furniture.describe();
        scanner.close();
    }
}
