package com.mycompany.hotelreservation;

public class DashboardController {

    Dashboard view;

    public DashboardController(Dashboard v) {
        this.view = v;
        view.guestBTN.addActionListener(e -> openGuest());
        view.reservationBTN.addActionListener(e -> openReservation());
        view.exitBTN.addActionListener(e -> exitApp());
    }
    private void openGuest() {
        GuestView gv = new GuestView();
        new GuestController(gv);   
        gv.setVisible(true);
    }
    private void openReservation() {
        ReservationView rv = new ReservationView();
        new ReservationController(rv);
        rv.setVisible(true);
    }
    private void exitApp() {
        view.dispose();  
    }
}
