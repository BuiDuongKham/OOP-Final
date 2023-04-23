package View;

import Entity.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VisitorView extends JFrame {
    private JPanel pnlHeader;
    private JLabel labHeader;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JTextField tfRegister;
    public JTextField tfPrisionerName;
    public JTextField tfPrisonerID;
    private JButton btnCancel;
    private JButton btnOK;
    private JLabel labName;
    private JLabel labAddress;
    private JLabel labPhone;
    private JLabel labRegister;
    private JLabel labPrisionerName;
    private JLabel labPrsionerID;
    private JPanel pnlMain;
    private JTable table1;
    public JTextField tfCountVisitor;
    private JLabel labCountVisitor;

    private String[] columnNames = {"Tên", "Quê quán", "Điện thoại", "Ngày đăng ký", "Tên phạm nhân", "ID Phạm Nhân"};
    private Object data = new Object [][] {};

    public VisitorView() {
        this.add(pnlMain);
        table1.setModel(new DefaultTableModel((Object[][]) data,columnNames));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setTitle("VisitorView");
        this.setSize(1000, 500);
        this.tfAddress.setText("");
        this.tfName.setText("");
        this.tfPhone.setText("");
        this.tfRegister.setText("");
        this.tfPrisionerName.setText("");
        this.tfPrisonerID.setText("");
    }

    public Visitor getVisitorInfo()
    {
        // validate data
        if (tfName.getText().equals(""))
        {
            showMess("Không được để trống tên");
            return null;
        }
        if (tfAddress.getText().equals(""))
        {
            showMess("Không được để trống địa chỉ");
            return null;
        }
        if (tfPhone.getText().equals(""))
        {
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
        if (tfPhone.getText().trim().length() != 10)
        {
            showMess("Số điện thoại là 10 số");
            return null;
        }
        if (tfRegister.getText().equals(""))
        {
            showMess("Không được để trống ngày đăng ký");
            return null;
        }
        // ngày đăng ký phải theo dạng dd/mm/yyyy
        if (!tfRegister.getText().trim().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
        {
            showMess("Ngày đăng ký phải theo dạng dd/mm/yyyy");
            return null;
        }

        // ngày đăng kí phải lớn hơn ngày hiện tại
        String[] RegisterDay = tfRegister.getText().trim().split("/");
        int currentYear = new Date().getYear()+1900;
        int currentMonth = new Date().getMonth()+1;
        int currentDate = new Date().getDate();
        int dateInYear = Integer.parseInt(RegisterDay[2]);
        int dateInMonth = Integer.parseInt(RegisterDay[1]);
        int dateInDay = Integer.parseInt(RegisterDay[0]);
        if (dateInYear % 4 == 0 && dateInMonth == 2 && dateInDay > 29){
            showMess("Tháng 2 năm nhuận chỉ có 29 ngày");
            return null;
        }
        if (dateInYear % 4 != 0 && dateInMonth == 2 && dateInDay > 28){
            showMess("Tháng 2 năm không nhuận chỉ có 28 ngày");
            return null;
        }
        if (dateInYear < currentYear){
            showMess("Ngày thăm nuôi phải lớn hơn ngày hiện tại");
            return null;
        }
        if (dateInYear == currentYear && dateInMonth < currentMonth){
            showMess("Ngày thăm nuôi phải lớn hơn ngày hiện tại");
            return null;
        }
        if (dateInYear == currentYear && dateInMonth == currentMonth && dateInDay <= currentDate){
            showMess("Ngày thăm nuôi phải lớn hơn ngày hiện tại");
            return null;
        }



        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim();
        String phone = tfPhone.getText().trim();
        String register = tfRegister.getText().trim();
        String prisonerName = tfPrisionerName.getText().trim();
        String prisonerID = tfPrisonerID.getText().trim();
        Visitor visitor = new Visitor(name, address, phone, register, prisonerName, prisonerID);
        return visitor;
    }

    public void showMess(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void showListVisitors(List<Visitor> list)
    {
        if (list == null)
            return;
        int size = list.size();
        Object[][] data = new Object[size][6];
        for (int i = 0; i < size; i++)
        {
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
