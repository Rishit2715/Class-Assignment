package com.tss.test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.tss.model.Student;
 


public class SerializeDemo {
 public static void main(String[] args) {
     try {
         Student s = new Student(101, "Rishit");

         FileOutputStream fos = new FileOutputStream("student.txt");
         ObjectOutputStream oos = new ObjectOutputStream(fos);

         oos.writeObject(s); 
         oos.close();
         fos.close();

         System.out.println("Object serialized to student.txt");
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
