package com.mycompany.hotelreservation;
public class AMainClass {
    public static void main(String[] args) {
        Dashboard dash = new Dashboard();
        new DashboardController(dash);
        dash.setVisible(true);
    }
}

