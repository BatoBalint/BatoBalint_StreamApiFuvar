package com.balint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static List<Fuvar> fuvarList;

    public static void main(String[] args) {
        readData("fuvar.csv");

        task1();
        task2(6185);
        task3();
        task4();
        task5();
        task6(4261);
        task7();
        task8(1452);
        task9();
        task10();
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
        System.out.println("1. feladat: " + fuvarList.stream().count() + "db utazas kerult feljegyzesre");
    }

    private static void task2(int taxiId) {
        double rides = fuvarList.stream().filter((a) -> a.getTaxiId() == taxiId).count();
        double sum = fuvarList.stream().filter((a) -> a.getTaxiId() == taxiId).mapToDouble((a) -> a.getPrice() + a.getTip()).sum();

        System.out.printf("2. feladat: %d fuvarbol %.2f $ volt a fizetese\n", (int) rides, sum);
    }

    private static void task3() {
        System.out.printf("3. feladat: %.2f merfoldet tettek meg a taxisok\n", fuvarList.stream().mapToDouble(Fuvar::getDistance).sum());
    }

    private static void task4() {
        System.out.printf("4. feladat: a leghosszabb fuvar %d mp volt\n", fuvarList.stream().max(Comparator.comparingDouble(Fuvar::getLength)).get().getLength());
    }

    private static void task5() {
        Fuvar f = fuvarList.stream().max((f1, f2) -> (int) (f1.getTip() / f1.getPrice() - f2.getTip() / f2.getPrice())).get();
        System.out.printf("5. feladat: a legbokezubb utas %.2f $ borravalot fizetett a %.2f $ arhoz kepest\n", f.getTip(), f.getPrice());
    }

    private static void task6(int taxiId) {
        System.out.printf("6. feladat: a %d azonositoju taxis %.2f kilometert tett meg\n", taxiId, fuvarList.stream().filter(f -> f.getTaxiId() == taxiId).mapToDouble(Fuvar::getDistance).sum() * 1.6);
    }

    private static void task7() {
        List<Fuvar> errors = fuvarList.stream().filter((f) -> f.getLength() > 0 && f.getPrice() > 0 && f.getDistance() == 0).toList();
        System.out.printf("""
                        7. feladat: A hibas fuvarok szama: %d
                                    A hibas fuvarok hossza %d
                                    A hibas fuvarok befetele %.2f
                        """,
                errors.stream().count(),
                errors.stream().mapToInt(Fuvar::getLength).sum(),
                errors.stream().mapToDouble(f -> f.getPrice() + f.getTip()).sum());
    }

    private static void task8(int taxiId) {
        if (fuvarList.stream().anyMatch((f) -> f.getTaxiId() == taxiId)) {
            System.out.printf("8. feladat: Van %d id-val taxi a listaban\n", taxiId);
        } else {
            System.out.printf("8. feladat: Nincs %d id-val taxi a listaban\n", taxiId);
        }
    }

    private static void task9() {
        List<Fuvar> fuvars = fuvarList.stream().filter(f -> f.getLength() != 0).sorted((f1, f2) -> f2.getLength() - f1.getLength()).limit(3).toList();
        System.out.println("9. feladat: A 3 leggyorsabb fuvar:");
        for (Fuvar f : fuvars) {
            System.out.println("\t" + f.toString());
        }
    }

    private static void task10() {
        List<Fuvar> dec24List = fuvarList.stream().filter(f -> f.getStart().contains("12-24")).toList();
        System.out.printf("10. feladat:\n");
        for (Fuvar f : dec24List) {
            System.out.println("\t" + f.toString());
        }
    }
}
