package com.balint;

public class Fuvar {
    //taxi_id;indulas;idotartam;tavolsag;viteldij;borravalo;fizetes_modja
    private int taxiId;
    private String start;
    private int length;
    private double distance;
    private double price;
    private double tip;
    private String paymentMethod;

    public Fuvar(String line) {
        line = line.replace(",", ".");
        String[] data = line.split(";");

        this.taxiId = Integer.parseInt(data[0]);
        this.start = data[1];
        this.length = Integer.parseInt(data[2]);
        this.distance = Double.parseDouble(data[3]);
        this.price = Double.parseDouble(data[4]);
        this.tip = Double.parseDouble(data[5]);
        this.paymentMethod = data[6];
    }

    @Override
    public String toString() {
        return String.format("%d: %s, length: %d, distance: %f mile, price: %f (+%f), method: %s",
                taxiId, start, length, distance, price, tip, paymentMethod);
    }
}
