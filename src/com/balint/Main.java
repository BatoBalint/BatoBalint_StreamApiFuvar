package com.balint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Fuvar> fuvarList;

    public static void main(String[] args) {
        readData("fuvar.csv");

        task1();
    }

    private static void readData(String fileName) {
        fuvarList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            String line = br.readLine();

            while (line != null) {
                fuvarList.add(new Fuvar(line));
                line = br.readLine();
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task1() {
        System.out.println(fuvarList.stream().count());
    }
}
