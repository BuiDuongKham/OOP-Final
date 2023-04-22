package Controller;

import Entity.Prisoner;
import Entity.Visitor;
import Store.VisitorStore;
import View.VisitorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VisitorController {
    private VisitorView visitorView;
    private VisitorStore visitorStore;
    private String identity;
    public VisitorController(Prisoner prisoner, String identity) {
        visitorView = new VisitorView();
        visitorStore = new VisitorStore();

        this.identity = identity;
        visitorView.tfPrisionerName.setText(prisoner.getName());
        visitorView.tfPrisonerID.setText(prisoner.getIdentity());
        visitorView.tfPrisionerName.setEnabled(false);
        visitorView.tfPrisonerID.setEnabled(false);

        visitorView.addBtnCancelListener(new BtnCancelListener());
        visitorView.addBtnOKListener(new BtnOKListener());
    }
    public void showVisitorView() {
        List<Visitor> visitors = visitorStore.getVisitorList();
        List<Visitor> Draft = new ArrayList<>();
        if (visitors == null)
            visitors = new ArrayList<>();
        for (Visitor v:visitors) {
            if (v.getPrisonerID().equals(visitorView.tfPrisonerID.getText()))
            {
                Draft.add(v);
            }
        }
        visitorView.setVisible(true);
        visitorView.showListVisitors(Draft);
        visitorView.tfCountVisitor.setText(String.valueOf(Draft.size()));
    }

    class BtnCancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            visitorView.dispose();
        }
    }
    class BtnOKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Visitor visitor = visitorView.getVisitorInfo();
            if (visitor == null)
                return;
            List<Visitor> visitors = visitorStore.getVisitorList();
            if (visitors == null)
                visitors = new ArrayList<>();
            for (Visitor v:visitors) {
                if (v.getPrisonerID().equals(visitor.getPrisonerID()) && v.getRegister().equals(visitor.getRegister()))
                {
                    visitorView.showMess("Ngày hôm đó tù nhân có người đăng kí thăm nuôi rồi");
                    return;
                }
            }
            visitorStore.addVisitor(visitor);
            visitorView.showMess("Đăng kí thành công");
            visitorView.getVisitorInfo();
            visitorView.dispose();
        }
    }
}
