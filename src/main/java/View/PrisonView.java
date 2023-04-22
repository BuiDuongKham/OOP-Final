package View;

import Entity.Prison;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
public class PrisonView extends JFrame {
    private JPanel panel1;
    private JLabel ID;
    private JTextField tfID;
    private JLabel Name;
    private JTextField tfName;
    private JLabel Address;
    private JTextField tfAddress;
    private JLabel Phone;
    private JTextField tfPhone;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton manageButton;
    private JButton clearButton;
    private JTable table1;
    private JScrollPane jScrollPanelPrison;
    private JTextField tfSearch;
    private JButton searchByNameButton;
    private JButton searchByIDButton;
    private JButton sortByIDButton;
    private JButton sortByNameButton;
    private JButton resetFilterButton;
    private JButton resetSortButton;
    private JButton sortByNumberOfButton;
    private JLabel countPrison;
    private JLabel countPrisoner;
    private JTextField tfCountPrison;
    private JTextField tfCountPrisoner;
    private JButton btnVisitorList;

    private String[] columnNames = {"ID", "Tên nhà tù", "Địa chỉ", "Điện thoại","Số tù nhân"};
    private Object data = new Object [][] {};
    public PrisonView() {
        table1.setModel(new DefaultTableModel((Object[][]) data,columnNames));
        jScrollPanelPrison = new JScrollPane();
        jScrollPanelPrison.setPreferredSize(new Dimension(400, 200));
        this.add(panel1);
        this.pack();
        this.setTitle("PrisonView");
        this.setSize(1000, 500);

}

    public void showMess(String mess){
                                    JOptionPane.showMessageDialog(null, mess);
                                                                               }

    public void showListPrisons(List<Prison> list)
    {
            if (list == null)
                return;
            int size = list.size();

            Object[][] data = new Object[size][5];
            for(int i = 0; i < size; i++)
            {
                data[i][0] = list.get(i).getId();
                data[i][1] = list.get(i).getName();
                data[i][2] = list.get(i).getAddress();
                data[i][3] = list.get(i).getPhone();
                data[i][4] = list.get(i).getNumberPrisoner();
            }
            table1.setModel(new DefaultTableModel(data, columnNames));
            // disable sửa trực tiếp trên table
            table1.setDefaultEditor(Object.class, null);
        }
    /**
     * điền thông tin của student vào các textfield
      */
    public void fillPrisonFromSelectedRow()
    {
        // lấy chỉ số của hàng được chọn
        int row = table1.getSelectedRow();

        if (row >=0)
        {
            // lấy giá trị của cell tại row và column
            int id = (int) table1.getValueAt(row, 0);
            String name = (String) table1.getValueAt(row, 1);
            String address = (String) table1.getValueAt(row, 2);
            String phone = (String) table1.getValueAt(row, 3);

            // điền giá trị vào textfield
            tfID.setText(String.valueOf(id));
            tfID.setEnabled(false);
            tfName.setText(name);
            tfAddress.setText(address);
            tfPhone.setText(phone);
        }
        deleteButton.setEnabled(true);
        editButton.setEnabled(true);
        manageButton.setEnabled(true);
        tfID.setEnabled(false);
        addButton.setEnabled(false);
    }

    /**
     * xóa thông tin của prison trong các textfield
     */
    public void clearPrisonInfo()
    {
        tfID.setText("");
        tfName.setText("");
        tfAddress.setText("");
        tfPhone.setText("");

        tfID.setEnabled(true);
        addButton.setEnabled(true);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        manageButton.setEnabled(false);
    }

    /**
     * hiển thị thông tin của prison lên các textfield
     * @param prison
     */
    public void showPrison(Prison prison)
    {
        tfID.setText(prison.getId() + "");
        tfName.setText(prison.getName());
        tfAddress.setText(prison.getAddress());
        tfPhone.setText(prison.getPhone());
    }

    /**
     * lấy thông tin của prison từ các textfield
     * @return
     */

    public Prison getPrisonInfo(){
        if (tfID.getText().equals(""))
        {
            showMess("ID đang để trống");
            return null;
        }
        // kiểm tra id có phải là số không
        try
        {
            Integer.parseInt(tfID.getText().trim());
        }
        catch (NumberFormatException e)
        {
            showMess("ID phải là số");
            return null;
        }

        // Kiểm tra tên có để trống không
        if (tfName.getText().equals(""))
        {
            showMess("Tên đang để trống");
            return null;
        }
        // Kiểm tra địa chỉ có để trống không
        if (tfAddress.getText().equals(""))
        {
            showMess("Địa chỉ đang để trống");
            return null;
        }
        // Kiểm tra số điện thoại có để trống không
        if (tfPhone.getText().equals(""))
        {
            showMess("Số điện thoại đang để trống");
            return null;
        }
        // Kiểm tra số điện thoại có dài hơn 10 số không
        if (tfPhone.getText().trim().length() > 10)
        {
            showMess("Số điện thoại không được dài hơn 10 số");
            return null;
        }
        // Kiểm tra số điện thoại có phải là số không
        try
        {
            Integer.parseInt(tfPhone.getText().trim());
        }
        catch (NumberFormatException e)
        {
            showMess("Số điện thoại phải là số");
            return null;
        }
        int id = Integer.parseInt(tfID.getText().trim());
        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim();
        String phone = tfPhone.getText().trim();
        return new Prison(id, name, address, phone);
    }

    public int getSearchID(){
        // Kiem tra ID co phai la so khong chi hien 1 lan thong bao loi
        String text = tfSearch.getText().trim();
        for (int i = 0; i < text.length(); i++)
        {
            if (!Character.isDigit(text.charAt(i)))
            {
                return -1;
            }
        }
        return Integer.parseInt(tfSearch.getText().trim());
    }
    public String getSearchName(){
        return tfSearch.getText().trim();
    }
    public void setSearch(String mess){
        tfSearch.setText(mess);
    }
   public void addAddPrisonListener(ActionListener listener){addButton.addActionListener(listener);}
    public void addDeletePrisonListener(ActionListener listener){deleteButton.addActionListener(listener);}
    public void addEditPrisonListener(ActionListener listener){editButton.addActionListener(listener);}
    public void addManagePrisonListener(ActionListener listener){manageButton.addActionListener(listener);}
    public void addClearPrisonListener(ActionListener listener){clearButton.addActionListener(listener);}

    public void addResetFilterListener(ActionListener listener){resetFilterButton.addActionListener(listener);}
    public void addListPrisonListener(ListSelectionListener listener){table1.getSelectionModel().addListSelectionListener(listener);}
    public void addSearchByNameListener(ActionListener listener){searchByNameButton.addActionListener(listener);}
    public void addSearchByIDListener(ActionListener listener){searchByIDButton.addActionListener(listener);}
    public void addSortByIDListener(ActionListener listener){sortByIDButton.addActionListener(listener);}
    public void addSortByNameListener(ActionListener listener){sortByNameButton.addActionListener(listener);}

    public void addResetSortListener(ActionListener listener){resetSortButton.addActionListener(listener);}
    public void addSortByNumberPrisonerListener(ActionListener listener){sortByNumberOfButton.addActionListener(listener);}
    public void addShowVisitorListener(ActionListener listener){btnVisitorList.addActionListener(listener);}
    public void updateNumberPrison(int number){
        tfCountPrison.setText(number + "");
    }
    public void updateNumberPrisoner(int number){
        tfCountPrisoner.setText(number + "");
    }

    public void updateAll(boolean check,List<Prison> list ){
        while (check){
            this.showListPrisons(list);
            this.updateNumberPrison(list.size());
            int number = 0;
            for (Prison prison : list)
            {
                number += prison.getNumberPrisoner();
            }
            this.updateNumberPrisoner(number);
            check = false;
        }
    }
}
