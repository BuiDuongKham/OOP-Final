package Store;

import Entity.Visitor;
import Func.VisitorFunc;

import java.util.List;
import java.util.ArrayList;
public class VisitorStore {
    private VisitorFunc visitorDao;
    private List<Visitor> visitorList;

    public VisitorStore(){
        this.visitorDao = new VisitorFunc();
        this.visitorList = visitorDao.readListVisitors();
    }

    public List<Visitor> getVisitorList() {
        if (visitorList == null) {
            visitorList = new ArrayList<Visitor>();
        }
        return visitorList;
    }

    public List<Visitor> getVisitorListByPrisonerId(String prisonerId) {
        System.out.println("Hello from getVisitorListByPrisonerId");
        List<Visitor> list_filtered = new ArrayList<>();
        List<Visitor> list_unfiltered = this.getVisitorList();
        if (list_unfiltered == null) {
            return list_filtered;
        }
        for (Visitor visitor : list_unfiltered) {
            if (visitor.getPrisonerID().equals(prisonerId)) {
                list_filtered.add(visitor);
            }
        }
        return list_filtered;
    }

    public void setVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }

    public void addVisitor(Visitor visitor) {
        List<Visitor> DraftList = this.getVisitorList();
        DraftList.add(visitor);
        this.setVisitorList(DraftList);
        visitorDao.writeListVisitors(this.getVisitorList());
    }

    public void removeVisitor(Visitor visitor){
        List<Visitor> DraftList = this.visitorList;
        for (Visitor visitor1 : visitorList) {
            if (visitor1.getRegister().equals(visitor.getRegister())) {
                DraftList.remove(visitor1);
                break;
            }
        }
        this.setVisitorList(DraftList);
        visitorDao.writeListVisitors(this.getVisitorList());
    }
}
