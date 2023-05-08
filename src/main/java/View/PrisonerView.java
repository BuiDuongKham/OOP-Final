package View;

import Entity.Prisoner;
import Func.PrisonerFunc;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrisonerView extends JFrame {
    private JPanel panel1;
    private JLabel Name;
    private JTextField tfName;
    private JLabel CrimeLabel;
    private JTextField tfCrime;
    private JLabel DateInLabel;
    private JTextField tfDateIn;
    private JButton deleteButton;
    private JButton addButton;
    private JButton editButton;
    private JButton clearButton;
    private JTable table1;
    private JScrollPane jScrollPanelPrison;
    public JTextField tfSearch;
    public JButton sortByIDButton;
    public JButton sortByNameButton;
    public JButton searchByIdentityButton;
    public JButton searchByNameButton;
    public JButton resetSortButton;
    public JButton resetFilterButton;
    public JButton sortByAgeButton;
    private JTextField tfIdentity;
    private JTextField tfPunishment;
    public JTextField tfImprisonedIn;
    private JButton visitButton;
    private JTextField tfAge;
    private JTextField tfAddress;
    private JLabel AgeLabel;
    private JLabel AddressLabel;
    private JLabel ImprisonedInLabel;
    private JLabel PunishmentLabel;
    private JButton saveChangesButton;
    private JTextField tfCountPrisoners;
    private DatePicker tfDatePicker;

    private String[] columnNames = {"Tên", "Tuổi", "Địa chỉ", "CMND", "Tội danh", "Hình phạt", "Ngày vào tù", "Thụ án tại"};

    private Object data = new Object[][]{};

    public PrisonerView() {
        this.table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(1000, 500);
        visitButton.setEnabled(false);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        tfDatePicker.getSettings().setDateRangeLimits(LocalDate.MIN,LocalDate.now());
        tfDatePicker.getSettings().setFormatForDatesCommonEra("dd/MM/yyyy");
    }

    public void showMess(String mess) {
        JOptionPane.showMessageDialog(null, mess);
    }

    /**
     * hiển thị danh sách các tù nhân
     */
    public void showListPrisoners(List<Prisoner> list) {
        if (list == null)
            return;
        int size = list.size();

        Object[][] data = new Object[size][8];
        for (int i = 0; i < size; i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getAge();
            data[i][2] = list.get(i).getAddress();
            data[i][3] = list.get(i).getIdentity();
            data[i][4] = list.get(i).getCrime();
            data[i][5] = list.get(i).getPunishment();
            data[i][6] = list.get(i).getDateIn();
            data[i][7] = list.get(i).getPrisonId();
        }
        table1.setModel(new DefaultTableModel(data, columnNames));
        jScrollPanelPrison.setViewportView(table1);
        jScrollPanelPrison.setPreferredSize(new Dimension(400, 200));
        tfCountPrisoners.setText(String.valueOf(size));
    }

    public Prisoner getPrisonerInfo() {
        // name is not be empty
        if (tfName.getText().equals("")) {
            showMess("Không được để trống trường Tên");
            return null;
        }
        String name = tfName.getText().trim();
        // age>0
        if (Integer.parseInt(tfAge.getText()) <= 0) {
            showMess("Tuổi phải lớn hơn 0");
            return null;
        }
        int age = Integer.parseInt(tfAge.getText().trim());
        // address is not be empty
        if (tfAddress.getText().equals("")) {
            showMess("Không được để trống trường Địa chỉ");
            return null;
        }
        String address = tfAddress.getText().trim();

        // identity is not be empty
        if (tfIdentity.getText().equals("")){
            showMess("Không được để trống trường CMND");
            return null;
        }
        // identity phải là 1 dãy số 12 số
        if (!tfIdentity.getText().trim().matches("[0-9]{12}")){
            showMess("CMND phải là 1 dãy số 12 số");
            return null;
        }
        String identity = tfIdentity.getText().trim();

        // crime is not be empty
        if (tfCrime.getText().equals("")) {
            showMess("Không được để trống trường Phạm Tội");
            return null;
        }
        String crime = tfCrime.getText();

        // punishment is not be empty
        if (tfPunishment.getText().equals("")) {
            showMess("Không được để trống trường Hình Phạt");
            return null;
        }
        // punishment must be greater than 0
        if (Integer.parseInt(tfPunishment.getText().trim()) <= 0) {
            showMess("Hình phạt phải lớn hơn 0");
            return null;
        }
        int punishment = Integer.parseInt(tfPunishment.getText().trim());


        // imprisonedIn is not be empty
        if (tfImprisonedIn.getText().equals("")) {
            showMess("Không được để trống trường Tù");
            return null;
        }
        int imprisonedIn = Integer.parseInt(tfImprisonedIn.getText().trim());
        Prisoner prisoner = new Prisoner(name, age, address, identity, crime, punishment, tfDatePicker.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) , imprisonedIn);
        return prisoner;
    }

    public void clearPrisonerInfo() {
        tfName.setText("");
        tfAge.setText("");
        tfAddress.setText("");
        tfIdentity.setText("");
        tfCrime.setText("");
        tfPunishment.setText("");
//        tfDateIn.setText("");

        // disable edit, delete button
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        visitButton.setEnabled(false);
        // enable add button
        addButton.setEnabled(true);
    }

    public void fillPrisonerFromSelectedRow() {
        int row = table1.getSelectedRow();
        if (row >= 0) {
            tfName.setText(table1.getValueAt(row, 0).toString());
            tfAge.setText(table1.getValueAt(row, 1).toString());
            tfAddress.setText(table1.getValueAt(row, 2).toString());
            tfIdentity.setText(table1.getValueAt(row, 3).toString());
            tfCrime.setText(table1.getValueAt(row, 4).toString());
            tfPunishment.setText(table1.getValueAt(row, 5).toString());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tfDatePicker.setDate(LocalDate.parse((CharSequence) table1.getValueAt(row,6),formatter));
            tfImprisonedIn.setText(table1.getValueAt(row, 7).toString());
            // enable edit, delete button
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            visitButton.setEnabled(true);
            // disable add button
            addButton.setEnabled(false);
        }
    }

    public void showPrisoner(Prisoner prisoner) {
        tfName.setText(prisoner.getName());
        tfAge.setText(String.valueOf(prisoner.getAge()));
        tfAddress.setText(prisoner.getAddress());
        tfIdentity.setText(prisoner.getIdentity());
        tfCrime.setText(prisoner.getCrime());
        tfPunishment.setText(String.valueOf(prisoner.getPunishment()));
        tfDateIn.setText(prisoner.getDateIn());
        tfImprisonedIn.setText(String.valueOf(prisoner.getPrisonId()));
    }

    public static void main(String[] args) {
        PrisonerFunc prisonerFunc = new PrisonerFunc();
        PrisonerView prisonerView = new PrisonerView();
        List<Prisoner> list = prisonerFunc.readListPrisoners();
        List<Prisoner> list1 = prisonerFunc.getListPrisonersByPrisonId(1);
        prisonerView.showListPrisoners(list1);
    }

    public void addAddPrisonerListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void addDeletePrisonerListener(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }

    public void addEditPrisonerListener(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void addClearPrisonerListener(ActionListener actionListener) {
        clearButton.addActionListener(actionListener);
    }

    public void addSortByIdPrisonerListener(ActionListener actionListener) {
        sortByIDButton.addActionListener(actionListener);
    }

    public void addSortByNamePrisonerListener(ActionListener actionListener) {
        sortByNameButton.addActionListener(actionListener);
    }

    public void addSortByAgePrisonerListener(ActionListener actionListener) {
        sortByAgeButton.addActionListener(actionListener);
    }

    public void addSearchByIdPrisonerListener(ActionListener actionListener) {
        searchByIdentityButton.addActionListener(actionListener);
    }

    public void addSearchByNamePrisonerListener(ActionListener actionListener) {
        searchByNameButton.addActionListener(actionListener);
    }

    public void addResetSortPrisonerListener(ActionListener actionListener) {
        resetSortButton.addActionListener(actionListener);
    }

    public void addResetFilterPrisonerListener(ActionListener actionListener) {
        resetFilterButton.addActionListener(actionListener);
    }

    public void addVisitPrisonerListener(ActionListener actionListener) {
        visitButton.addActionListener(actionListener);
    }

    public void addListPrisonerSelectionListener(ListSelectionListener listener) {
        table1.getSelectionModel().addListSelectionListener(listener);
    }

    public void addSaveChangeListener(ActionListener actionListener) {
        saveChangesButton.addActionListener(actionListener);
    }


}
