package Store;

import Entity.Prisoner;
import Func.PrisonerFunc;

import java.util.ArrayList;
import java.util.List;

public class PrisonerStore {
    private PrisonerFunc prisonerDao;
    private List<Prisoner> listPrisoners;
    public PrisonerStore() {
        this.prisonerDao = new PrisonerFunc();
        this.listPrisoners = prisonerDao.readListPrisoners();
    }

    public List<Prisoner> getListPrisoners() {
        if (listPrisoners == null) {
            listPrisoners = new ArrayList<Prisoner>();
        }
        return listPrisoners;
    }

    public List<Prisoner> getListPrisonersByPrisonId(int prisonId) {
        List<Prisoner> list_filtered = new ArrayList<Prisoner>();
        List<Prisoner> list_unfiltered = this.getListPrisoners();
        if (list_unfiltered == null) {
            return list_filtered;
        }
        for (Prisoner prisoner : list_unfiltered) {
            if (prisoner.getPrisonId() == prisonId) {
                list_filtered.add(prisoner);
            }
        }
        return list_filtered;
    }

    public void setListPrisoners(List<Prisoner> listPrisoners) {
        this.listPrisoners = listPrisoners;
    }

    public void addPrisoner(Prisoner prisoner) {
        List<Prisoner> DraftList = this.getListPrisoners();
        DraftList.add(prisoner);
        this.setListPrisoners(DraftList);
        prisonerDao.writeListPrisoners(this.getListPrisoners());
    }

    public void removePrisoner(Prisoner prisoner){
        List<Prisoner> DraftList = this.listPrisoners;
        for (Prisoner prisoner1 : listPrisoners) {
            if (prisoner1.getIdentity().equals(prisoner.getIdentity())) {
                DraftList.remove(prisoner1);
                break;
            }
        }
        this.setListPrisoners(DraftList);
        prisonerDao.writeListPrisoners(this.getListPrisoners());
    }

    public void editPrisoner(Prisoner prisoner){
        List<Prisoner> DraftList = this.getListPrisoners();
        for (Prisoner prisoner1 : DraftList) {
           if (prisoner1.getIdentity().equals(prisoner.getIdentity())){
               prisoner1.setName(prisoner.getName());
               prisoner1.setAge(prisoner.getAge());
               prisoner1.setAddress(prisoner.getAddress());
               prisoner1.setCrime(prisoner.getCrime());
               prisoner1.setPunishment(prisoner.getPunishment());
               prisoner1.setDateIn(prisoner.getDateIn());
               break;
           }
        }
        this.setListPrisoners(DraftList);
        prisonerDao.writeListPrisoners(this.getListPrisoners());
    }

}
