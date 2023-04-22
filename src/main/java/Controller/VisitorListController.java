package Controller;

import Entity.Visitor;
import Func.VisitorFunc;
import Store.VisitorStore;
import View.VisitorListView;

import java.util.List;

public class VisitorListController {
    private VisitorListView visitorListView;
    private VisitorFunc visitorDao;
    private VisitorStore visitorStore;
    public VisitorListController() {
        visitorListView = new VisitorListView();
        visitorDao = new VisitorFunc();
        visitorStore = new VisitorStore();
        List<Visitor> visitors = visitorStore.getVisitorList();
        visitorListView.showListVisitor(visitors);
    }
    public void showVisitorListView() {
        visitorListView.setVisible(true);
    }
}
