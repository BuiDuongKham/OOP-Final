package Controller;

import Entity.Prison;
import Entity.Prisoner;
import Entity.Visitor;
import Func.PrisonerFunc;
import Store.PrisonStore;
import Store.PrisonerStore;
import Store.VisitorStore;
import View.PrisonView;
import View.PrisonerView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrisonerController {
    private PrisonerFunc prisonerDao;
    private PrisonStore prisonStore;
    private PrisonerView prisonerView;
    private PrisonerStore prisonerStore;

    private VisitorStore visitorStore;
    private int id;
    public PrisonerController(PrisonerView view, int id){
        this.id = id;
        this.prisonerView = view;
        this.prisonerDao = new PrisonerFunc();
        this.prisonStore = new PrisonStore();
        this.prisonerStore = new PrisonerStore();
        this.visitorStore = new VisitorStore();

        view.tfImprisonedIn.setEnabled(false);
        view.tfImprisonedIn.setText(String.valueOf(id));
        view.addAddPrisonerListener(new AddPrisonerListener());
        view.addDeletePrisonerListener(new DeletePrisonerListener());
        view.addEditPrisonerListener(new EditPrisonerListener());
        view.addClearPrisonerListener(new ClearPrisonerListener());
        view.addSortByNamePrisonerListener(new SortByNamePrisonerListener());
        view.addSortByAgePrisonerListener(new SortByAgePrisonerListener());
        view.addSortByIdPrisonerListener(new SortByIdPrisonerListener());
        view.addResetSortPrisonerListener(new ResetSortPrisonerListener());
        view.addSearchByIdPrisonerListener(new SearchByIdPrisonerListener());
        view.addSearchByNamePrisonerListener(new SearchByNamePrisonerListener());
        view.addResetFilterPrisonerListener(new ResetFilterPrisonerListener());
        view.addVisitPrisonerListener(new VisitPrisonerListener());
        view.addListPrisonerSelectionListener(new ListPrisonerSelectionListener());
        view.addSaveChangeListener(new SaveChangeListener());
    }

    /**
     * In ra bảng thông tin tất cả các tù nhân trong nhà tù có id = this.id
     */
    public void showPrisonerList() {
        List<Prisoner> list = prisonerDao.getListPrisonersByPrisonId(this.id);
        prisonerView.setVisible(true);
        prisonerView.showListPrisoners(list);
    }

    /**
     * Thêm tù nhân
     */
    public class AddPrisonerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Prisoner prisoner = prisonerView.getPrisonerInfo();
            if (prisoner == null ) return;

            List<Prisoner> list_prisoner = prisonerStore.getListPrisoners();
            // kiểm tra xem có tồn tại tù nhân này chưa

            for (Prisoner p : list_prisoner) {
                if (p.getIdentity().equals(prisoner.getIdentity())) {
                    prisonerView.showMess("Tù nhân đã tồn tại!");
                    prisonerView.clearPrisonerInfo();
                    return;
                }
            }

            prisonerStore.addPrisoner(prisoner);
            if (list_prisoner == null) return;
            List<Prison> list_prison = prisonStore.getListPrisons();
            for (Prison prison : list_prison) {
                if (prison.getId() == id) {
                    prison.setNumberPrisoner(prison.getNumberPrisoner() + 1);
                }
            }
            prisonStore.setListPrisons(list_prison);
            prisonerView.showListPrisoners(prisonerStore.getListPrisonersByPrisonId(id));
        }
    }

    /**
     * Sửa tù nhân
     */
    public class EditPrisonerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            prisonerView.showPrisoner(prisoner);
            prisonerStore.editPrisoner(prisoner);
            prisonerView.showListPrisoners(prisonerStore.getListPrisonersByPrisonId(id));
            prisonerView.showMess("Sửa thành công!");
        }
    }
    /**
    * Xóa tù nhân
     */
    public class DeletePrisonerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            if (prisoner != null) {
                try {
                    // Xóa những người thăm nuôi của tù nhân này
                    List<Visitor> list_visitor = visitorStore.getVisitorList();
                    List<Visitor> draft = new ArrayList<>();
                    for (Visitor visitor : list_visitor) {
                        if (!visitor.getPrisonerID().equals(prisoner.getIdentity())) {
                            draft.add(visitor);
                        }
                    }
                    visitorStore.setVisitorList(draft);
                    // Xóa tù nhân
                    prisonerStore.removePrisoner(prisoner);
                    prisonerView.showListPrisoners(prisonerStore.getListPrisonersByPrisonId(id));
                    prisonerView.clearPrisonerInfo();
                    prisonerView.showMess("Xóa thành công!");
                } catch (Exception ex) {
                    prisonerView.showMess("Có lỗi xảy ra! Vui lòng thử lại!");
                }
            }
        }
    }
    /**
     * Xóa thông tin ở ô nhập
     */
    public class ClearPrisonerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prisonerView.clearPrisonerInfo();
        }
    }

    class ListPrisonerSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            prisonerView.fillPrisonerFromSelectedRow();
        }
    }
    class SaveChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.dispose();
            PrisonView prisonView = new PrisonView();
            PrisonController prisonController = new PrisonController(prisonView);
            prisonController.showPrisonView();
        }
    }
    class SortByNamePrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> Draft= prisonerDao.getListPrisonersByPrisonId(id);
            Draft.sort( (Prisoner p1, Prisoner p2) -> p1.getName().compareTo(p2.getName()));
            prisonerView.showListPrisoners(Draft);
            prisonerView.sortByIDButton.setEnabled(false);
            prisonerView.sortByAgeButton.setEnabled(false);
            prisonerView.searchByIdentityButton.setEnabled(false);
            prisonerView.searchByNameButton.setEnabled(false);
            prisonerView.resetFilterButton.setEnabled(false);
        }
    }

    class SortByAgePrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> Draft= prisonerDao.getListPrisonersByPrisonId(id);
            Draft.sort( (Prisoner p1, Prisoner p2) -> p1.getAge() - p2.getAge());
            prisonerView.showListPrisoners(Draft);
            prisonerView.sortByIDButton.setEnabled(false);
            prisonerView.sortByNameButton.setEnabled(false);
            prisonerView.searchByIdentityButton.setEnabled(false);
            prisonerView.searchByNameButton.setEnabled(false);
            prisonerView.resetFilterButton.setEnabled(false);
        }
    }

    class SortByIdPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> Draft= prisonerDao.getListPrisonersByPrisonId(id);
            Draft.sort( (Prisoner p1, Prisoner p2) -> p1.getIdentity().compareTo(p2.getIdentity()) );
            prisonerView.showListPrisoners(Draft);

            prisonerView.sortByNameButton.setEnabled(false);
            prisonerView.sortByAgeButton.setEnabled(false);

            prisonerView.searchByIdentityButton.setEnabled(false);
            prisonerView.searchByNameButton.setEnabled(false);
            prisonerView.resetFilterButton.setEnabled(false);

        }
    }
    class ResetSortPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.showListPrisoners(prisonerDao.getListPrisonersByPrisonId(id));
            prisonerView.sortByIDButton.setEnabled(true);
            prisonerView.sortByNameButton.setEnabled(true);
            prisonerView.sortByAgeButton.setEnabled(true);
            prisonerView.searchByIdentityButton.setEnabled(true);
            prisonerView.searchByNameButton.setEnabled(true);
            prisonerView.resetFilterButton.setEnabled(true);
        }
    }

    class SearchByIdPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> Result = new ArrayList<>();
            List<Prisoner> Draft = prisonerDao.getListPrisonersByPrisonId(id);
            for (Prisoner p : Draft) {
                if (p.getIdentity().equals(prisonerView.tfSearch.getText().trim())) {
                    Result.add(p);
                }
            }
            prisonerView.showListPrisoners(Result);

            prisonerView.sortByIDButton.setEnabled(false);
            prisonerView.sortByNameButton.setEnabled(false);
            prisonerView.sortByAgeButton.setEnabled(false);
            prisonerView.resetSortButton.setEnabled(false);
        }
    }

    class SearchByNamePrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> Result = new ArrayList<>();
            List<Prisoner> Draft = prisonerDao.getListPrisonersByPrisonId(id);
            for (Prisoner p : Draft) {
                if (p.getName().contains(prisonerView.tfSearch.getText().trim())) {
                    Result.add(p);
                }
            }
            prisonerView.showListPrisoners(Result);
            prisonerView.sortByIDButton.setEnabled(false);
            prisonerView.sortByNameButton.setEnabled(false);
            prisonerView.sortByAgeButton.setEnabled(false);
            prisonerView.resetSortButton.setEnabled(false);
        }
    }

    class ResetFilterPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.showListPrisoners(prisonerDao.getListPrisonersByPrisonId(id));
            prisonerView.sortByIDButton.setEnabled(true);
            prisonerView.sortByNameButton.setEnabled(true);
            prisonerView.sortByAgeButton.setEnabled(true);
            prisonerView.resetSortButton.setEnabled(true);

        }
    }

    class VisitPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            VisitorController visitorController = new VisitorController(prisoner, prisoner.getIdentity());
            visitorController.showVisitorView();
        }
    }
}
