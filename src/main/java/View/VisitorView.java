package View;

import Entity.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class VisitorView extends JFrame {
    private JPanel pnlHeader;
    private JLabel labHeader;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfPhone;
    public JTextField tfPrisionerName;
    private JLabel labRegister;
    public JTextField tfPrisonerID;
    private JButton btnCancel;
    private JButton btnOK;
    private JLabel labName;
    private JLabel labAddress;
    private JLabel labPhone;
    private JLabel labPrisionerName;
    private JLabel labPrsionerID;
    private JPanel pnlMain;
    private JTable table1;
    public JTextField tfCountVisitor;
    private JLabel labCountVisitor;
    private com.github.lgooddatepicker.components.DatePicker DatePicker;

    private String[] columnNames = {"Tên", "Quê quán", "Điện thoại", "Ngày đăng ký", "Tên phạm nhân", "ID Phạm Nhân"};
    private Object data = new Object[][]{};

    public VisitorView() {
        this.add(pnlMain);
        table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setTitle("VisitorView");
        this.setSize(1000, 500);
        this.tfAddress.setText("");
        this.tfName.setText("");
        this.tfPhone.setText("");
        this.tfPrisionerName.setText("");
        this.tfPrisonerID.setText("");
        this.DatePicker.getSettings().setDateRangeLimits(LocalDate.now(),LocalDate.MAX);
        this.DatePicker.getSettings().setFormatForDatesCommonEra("dd/MM/yyyy");
    }

    public Visitor getVisitorInfo() {
        // validate data
        if (tfName.getText().equals("")) {
            showMess("Không được để trống tên");
            return null;
        }
        if (tfAddress.getText().equals("")) {
            showMess("Không được để trống địa chỉ");
            return null;
        }
        if (tfPhone.getText().equals("")) {
            showMess("Không được để trống số điện thoại");
            return null;
        }
        // check phone number
        try {
            int phone = Integer.parseInt(tfPhone.getText().trim());
        } catch (Exception e) {
            showMess("Số điện thoại phải là số");
            return null;
        }
        // số điện thoại không là 10 số
        if (tfPhone.getText().trim().length() != 10) {
            showMess("Số điện thoại là 10 số");
            return null;
        }

        System.out.println(this.DatePicker.getDate().toString());


        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim();
        String phone = tfPhone.getText().trim();
        String prisonerName = tfPrisionerName.getText().trim();
        String prisonerID = tfPrisonerID.getText().trim();
        Visitor visitor = new Visitor(name, address, phone,DatePicker.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) , prisonerName, prisonerID);
        return visitor;
    }

    public void showMess(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showListVisitors(List<Visitor> list) {
        if (list == null)
            return;
        int size = list.size();
        Object[][] data = new Object[size][6];
        for (int i = 0; i < size; i++) {
            Visitor visitor = list.get(i);
            data[i][0] = visitor.getName();
            data[i][1] = visitor.getAddress();
            data[i][2] = visitor.getPhone();
            data[i][3] = visitor.getRegister();
            data[i][4] = visitor.getPrisonerName();
            data[i][5] = visitor.getPrisonerID();
        }
        table1.setModel(new DefaultTableModel(data, columnNames));
    }

    public void addBtnCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    public void addBtnOKListener(ActionListener listener) {
        btnOK.addActionListener(listener);
    }

}
