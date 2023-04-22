package View;

import Entity.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class VisitorListView extends JFrame {
    private JLabel labHeader;
    private JTable table1;
    private JPanel pnlMain;
    private JTextField tfCountVisitor;
    private JLabel labCountVisitor;

    private String[] columnNames = {"Ngày", "ID Tù nhân","Tên tù nhân","Tên người thăm nuôi", "Địa chỉ", "Số điện thoại"};
    private Object data = new Object [][] {};
    public VisitorListView() {
        table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        this.add(pnlMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setTitle("VisitorListView");
        this.setSize(1000, 500);
    }

    public static void main(String[] args) {
        VisitorListView visitorListView = new VisitorListView();
        visitorListView.setVisible(true);
    }

    public void showListVisitor(List<Visitor> list)
    {
        if (list == null)
            list = new ArrayList<Visitor>();
        int size = list.size();
        if (size == 0)
        {
            tfCountVisitor.setText("0");
            table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
            return;
        }
        Object[][] data = new Object[size][6];
        for(int i = 0; i < size; i++)
        {
            data[i][0] = list.get(i).getRegister();
            data[i][1] = list.get(i).getPrisonerID();
            data[i][2] = list.get(i).getPrisonerName();
            data[i][3] = list.get(i).getName();
            data[i][4] = list.get(i).getAddress();
            data[i][5] = list.get(i).getPhone();
        }
        tfCountVisitor.setText(String.valueOf(size));
        table1.setModel(new DefaultTableModel(data, columnNames));
    }
}
