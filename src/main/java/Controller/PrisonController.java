package Controller;

import Entity.Prison;
import Entity.Prisoner;
import Entity.Visitor;
import Func.PrisonFunc;
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

public class PrisonController {
    private PrisonStore prisonStore;
    private PrisonFunc prisonDao;
    private PrisonerStore prisonerStore;
    private PrisonView prisonView;

    private VisitorStore visitorStore;
    public PrisonController(PrisonView view) {
        this.prisonDao = new PrisonFunc();
        this.prisonStore = new PrisonStore();
        this.prisonerStore = new PrisonerStore();
        this.prisonView = view;
        this.visitorStore = new VisitorStore();

        view.addAddPrisonListener(new AddPrisonListener());
        view.addEditPrisonListener(new EditPrisonListener());
        view.addDeletePrisonListener(new DeletePrisonListener());
        view.addClearPrisonListener(new ClearPrisonListener());
        view.addManagePrisonListener(new ManagePrisonListener());
        view.addListPrisonListener(new PrisonTableListener());
        view.addSearchByIDListener(new SearchByIDListener());
        view.addSearchByNameListener(new SearchByNameListener());
        view.addSortByIDListener(new SortByIDListener());
        view.addSortByNameListener(new SortByNameListener());
        view.addResetFilterListener(new ResetFilterListener());
        view.addResetSortListener(new ResetSortListener());
        view.addSortByNumberPrisonerListener(new SortByNumPrisonerListener());
        view.updateNumberPrison(prisonStore.getListPrisons().size());
        view.updateNumberPrisoner(prisonerStore.getListPrisoners().size());
        view.addShowVisitorListener(new ShowListVisitor());
    }
    public void showPrisonView() {
        List<Prison> prisons = prisonStore.getListPrisons();
        prisonView.setVisible(true);
        prisonView.showListPrisons(prisons);
    }

    /**
     * Add Entity.Prison
     */
    class AddPrisonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prison prison = prisonView.getPrisonInfo();
            if (prison != null) {
                // Kiểm tra ID đã tồn tại chưa
                List<Prison> prisons = prisonStore.getListPrisons();
                if (prisons == null) {
                    prisons = new ArrayList<Prison>();
                }
                for (Prison prison1 : prisons) {
                    if (prison1.getId() == prison.getId()) {
                        prisonView.showMess("ID đã tồn tại!");
                        prisonView.clearPrisonInfo();
                        return;
                    }
                }
                prisonStore.addPrison(prison);
                prisonView.clearPrisonInfo();
                prisonView.showListPrisons(prisonStore.getListPrisons());
                prisonView.showMess("Thêm thành công!");
                prisonView.updateAll(true,prisonStore.getListPrisons());
            }
        }
    }

    /**
     * Update Entity.Prison
     */
    class EditPrisonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prison prison = prisonView.getPrisonInfo();
            if (prison != null) {
                prisonStore.editPrison(prison);
                prisonView.showPrison(prison);
                prisonView.showListPrisons(prisonStore.getListPrisons());
                prisonView.showMess("Cập nhật thành công!");
            }
        }
    }

    /**
     * Delete Entity.Prison
     */
    class DeletePrisonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prison prison = prisonView.getPrisonInfo();
            if (prison != null) {
                // Xóa nhà tù kèm theo những tù nhân + thân nhân thăm gặp
                List<Prisoner> prisoners = prisonerStore.getListPrisonersByPrisonId(prison.getId());
                for (Prisoner prisoner : prisoners) {
                    List<Visitor> visitors = visitorStore.getVisitorList();
                    List<Visitor> draft = new ArrayList<Visitor>();
                    for (Visitor visitor : visitors) {
                        if (!visitor.getPrisonerID().equals(prisoner.getIdentity())) {
                            draft.add(visitor);
                        }
                    }
                    visitorStore.setVisitorList(draft);
                }
                for (Prisoner prisoner : prisoners) {
                    prisonerStore.removePrisoner(prisoner);
                }
                prisonStore.removePrison(prison);
                prisonView.showListPrisons(prisonStore.getListPrisons());
                prisonView.updateNumberPrisoner(prisonerStore.getListPrisoners().size());
                prisonView.updateNumberPrison(prisonStore.getListPrisons().size());
                prisonView.clearPrisonInfo();
                prisonView.showMess("Xóa thành công!");
            }
        }
    }

    /**
     * Manage Entity.Prison
     */
    class ManagePrisonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Prison prison = prisonView.getPrisonInfo();
            if (prison != null) {
                PrisonerView prisonerView = new PrisonerView();
                PrisonerController prisonerController = new PrisonerController(prisonerView, prison.getId());
                prisonerController.showPrisonerList();
                // kiểm tra xem có sự thay đổi nào không và cập nhật lại một cách trực tiếp
                prisonView.dispose();
            }
        }
    }
    /**
     * Reset Entity.Prison
     */
    class ClearPrisonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            prisonView.clearPrisonInfo();
        }
    }

    /**
     * Entity.Prisoner Table
     */
   class PrisonTableListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e) {
            prisonView.fillPrisonFromSelectedRow();
        }
    }

    /**
     * Search by ID
     */
    class SearchByIDListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Kiểm tra đã điền ID chưa
            if (prisonView.getSearchName().equals("")) {
                prisonView.showMess("Vui lòng điền ID!");
                return;
            }
            boolean check = false;
            List<Prison> prisons = prisonStore.getListPrisons();
            List<Prison> found  = new ArrayList<>();
            for (Prison prison : prisons) {
                if (prison.getId() == prisonView.getSearchID()) {
                    check = true;
                    found.add(prison);
                }
            }
            if (!check) {
                prisonView.showMess("Không tìm thấy!");
            } else {
                prisonView.showListPrisons(found);
            }
        }
    }

    /**
     * Search by Name
     */
    class SearchByNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Kiểm tra đã điền thanh search chưa
            if (prisonView.getSearchName().equals("")) {
                prisonView.showMess("Vui lòng điền ID!");
                return;
            }
            boolean check = false;
            List<Prison> prisons = prisonStore.getListPrisons();
            List<Prison> found  = new ArrayList<>();
            for (Prison prison : prisons) {
                if (prison.getName().equals(prisonView.getSearchName())) {
                    check = true;
                    found.add(prison);
                }
            }
            if (!check) {
                prisonView.showMess("Không tìm thấy!");
            } else {
                prisonView.showListPrisons(found);
            }
        }
    }

    /**
     * Sort by ID
     */
    class SortByIDListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prison> prisons = new ArrayList<>(prisonStore.getListPrisons());
            prisons.sort((o1, o2) -> o1.getId() - o2.getId());
            prisonView.showListPrisons(prisons);
        }
    }

    /**
     * Sort by Name
     */
    class SortByNameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prison> prisons = new ArrayList<>(prisonStore.getListPrisons());
            prisons.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            prisonView.showListPrisons(prisons);
        }
    }

    /**
     * Clear Filter
     */
    class ResetFilterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonView.showListPrisons(prisonStore.getListPrisons());
            prisonView.setSearch("");
        }
    }

    /**
     * Sort by Number of Prisoners
     */
    class SortByNumPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prison> prisons = new ArrayList<>(prisonStore.getListPrisons());
            prisons.sort((o1, o2) -> o2.getNumberPrisoner() - o1.getNumberPrisoner());
            prisonView.showListPrisons(prisons);
        }
    }
    /**
     * Clear Sort
     */
    class ResetSortListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonView.showListPrisons(prisonStore.getListPrisons());
        }
    }

    class ShowListVisitor implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            VisitorListController visitorListController = new VisitorListController();
            visitorListController.showVisitorListView();
        }
    }
}
