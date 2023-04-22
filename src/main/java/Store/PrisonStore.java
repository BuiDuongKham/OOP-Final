package Store;

import Entity.Prison;
import Func.PrisonFunc;

import java.util.ArrayList;
import java.util.List;

public class PrisonStore {
    private PrisonFunc prisonDao;
    private List<Prison> listPrisons;
    public PrisonStore() {
        this.prisonDao = new PrisonFunc();
        this.listPrisons = prisonDao.readListPrisons();
    }
    public List<Prison> getListPrisons() {
        List<Prison> list = prisonDao.readListPrisons();
        if (list == null) {
            list = new ArrayList<Prison>();
        }
        // in ra màn hình để kiểm tra
        System.out.println("Logging");
        for (Prison prison : list) {
            System.out.println(prison.getId() + " "  + prison.getNumberPrisoner());
        }
        return list;
    }

    public void setListPrisons(List<Prison> listPrisons) {
        this.listPrisons = listPrisons;
        prisonDao.writeListPrisons(this.listPrisons);
    }
    public void addPrison(Prison prison) {
        List<Prison> DraftList = this.getListPrisons();
        DraftList.add(prison);
        this.setListPrisons(DraftList);
        prisonDao.writeListPrisons(this.getListPrisons());
    }
    public void removePrison(Prison prison){
        List<Prison> DraftList = this.getListPrisons();
        for (Prison prison1 : DraftList) {
            if (prison1.getId() == prison.getId()) {
                DraftList.remove(prison1);
                break;
            }
        }
        this.setListPrisons(DraftList);
        prisonDao.writeListPrisons(this.getListPrisons());
    }
    public void editPrison(Prison prison){
        List<Prison> DraftList = this.getListPrisons();
        for (Prison prison1 : DraftList) {
            if (prison1.getId() == prison.getId()) {
                prison1.setName(prison.getName());
                prison1.setAddress(prison.getAddress());
                prison1.setPhone(prison.getPhone());
            }
        }
        this.setListPrisons(DraftList);
        prisonDao.writeListPrisons(this.getListPrisons());
    }

}
