package com.mycompany.hotelreservation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class GuestController {
    GuestView gv;
    Guest selectedGuest = null;
    public GuestController(GuestView temp) {
        gv = temp;
        gv.allListeners(new AllActions());
        loadGuestTable();
    }
    public void loadGuestTable() {
        List<Guest> list = GuestDAO.getAll();
        DefaultTableModel model = (DefaultTableModel) gv.jTable2.getModel();
        model.setRowCount(0);
        for (Guest g : list) {
            model.addRow(new Object[]{
                g.getGuestID(),
                g.getFirstName(),
                g.getLastName(),
                g.getAddress(),
                g.getPhoneNumber()
            });
        }
    }
    class AllActions implements ActionListener {
    public void loadGuestTable() {
    List<Guest> list = GuestDAO.getAll();
    javax.swing.table.DefaultTableModel model =
            (javax.swing.table.DefaultTableModel) gv.jTable2.getModel();
    model.setRowCount(0); 
    for (Guest g : list) {
        model.addRow(new Object[]{
            g.getGuestID(),
            g.getFirstName(),
            g.getLastName(),
            g.getAddress(),
            g.getPhoneNumber()
        });
    }
}
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== gv.addBTN) {

                String FirstName = gv.jTextField1.getText();
                String LastName = gv.jTextField5.getText();
                String Address = gv.jTextField6.getText();
                String PhoneNumber = gv.jTextField7.getText();

                Guest g = new Guest();
                g.setFirstName(FirstName);
                g.setLastName(LastName);
                g.setAddress(Address);
                g.setPhoneNumber(PhoneNumber);

                GuestDAO.save(g);
                loadGuestTable();

                JOptionPane.showMessageDialog(null, "Guest Save!");
            }
            if (e.getSource() == gv.editBTN) {

    int row = gv.jTable2.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Select a guest to edit.");
        return;
    }

    int id = Integer.parseInt(gv.jTable2.getValueAt(row, 0).toString());

    selectedGuest = GuestDAO.getById(id);

    if (selectedGuest != null) {
        gv.jTextField1.setText(selectedGuest.getFirstName());
        gv.jTextField5.setText(selectedGuest.getLastName());
        gv.jTextField6.setText(selectedGuest.getAddress());
        gv.jTextField7.setText(selectedGuest.getPhoneNumber());
    }
} 
            if (e.getSource() == gv.UpdateBTN) {

                if (selectedGuest == null) {
                    JOptionPane.showMessageDialog(null, "No guest loaded to update!");
                    return;
                }

                selectedGuest.setFirstName(gv.jTextField1.getText());
                selectedGuest.setLastName(gv.jTextField5.getText());
                selectedGuest.setAddress(gv.jTextField6.getText());
                selectedGuest.setPhoneNumber(gv.jTextField7.getText());

                GuestDAO.update(selectedGuest);
                loadGuestTable();

                JOptionPane.showMessageDialog(null, "Guest Updated!");
            }
            if (e.getSource() == gv.deleteBTN) {

                int row = gv.jTable2.getSelectedRow();

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a guest to delete.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this guest?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                int id = Integer.parseInt(gv.jTable2.getValueAt(row, 0).toString());
                GuestDAO.delete(id);
                loadGuestTable();
                JOptionPane.showMessageDialog(null, "Guest Deleted!");
                gv.jTextField1.setText("");
                gv.jTextField5.setText("");
                gv.jTextField6.setText("");
                gv.jTextField7.setText("");
                selectedGuest = null;
            }
                    }
                }
            }