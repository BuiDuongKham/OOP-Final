package Func;

import Entity.Prisoner;
import Entity.PrisonerXML;
import Utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class PrisonerFunc {
    private static final String PRISONER_FILE_NAME = "src/main/java/prisoner.xml";
    private List<Prisoner> listPrisoners;

    public PrisonerFunc() {
        if (listPrisoners == null) {
            listPrisoners = new ArrayList<Prisoner>();
        } else {
        this.listPrisoners = readListPrisoners();
        }
    }
    /**
     * Lưu các đối tượng prisoner vào file prisoner.xml
     *
     * @param prisoners
     */
    public void writeListPrisoners(List<Prisoner> prisoners) {
        PrisonerXML prisonerXML = new PrisonerXML();
        prisonerXML.setPrisoner(prisoners);
        FileUtils.writeXMLToFile(PRISONER_FILE_NAME, prisonerXML);
    }
    /**
     * Đọc các đối tượng prisoner từ file prisoner.xml
     *
     * @return list prisoner
     */
    public List<Prisoner> readListPrisoners() {
        List<Prisoner> list = new ArrayList<Prisoner>();
        PrisonerXML prisonerXML = (PrisonerXML) FileUtils.readXMLFile(
                PRISONER_FILE_NAME, PrisonerXML.class);
        if (prisonerXML != null) {
            list = prisonerXML.getPrisoner();
        }
        return list;
    }

    /**
     * Lấy danh sách các prisoner trong listPrisoners của 1 nhà tù với id cụ thể
     */
    public List<Prisoner> getListPrisonersByPrisonId(int prisonId) {
        List<Prisoner> list_filtered = new ArrayList<Prisoner>();
        List<Prisoner> list_unfiltered = readListPrisoners();
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
    /**
     * thêm prisoner vào listPrisoners và lưu listPrisoners vào file
     *
     * @param prisoner
     */
    public void add(Prisoner prisoner)
    {
        listPrisoners.add(prisoner);
        writeListPrisoners(listPrisoners);
    }

    /**
     * sửa prisoner trong listPrisoners và lưu listPrisoners vào file
     *
     * @param prisoner
     */
    public void edit(Prisoner prisoner)
    {
        for (int i = 0; i < listPrisoners.size(); i++) {
            if (listPrisoners.get(i).getIdentity().equals(prisoner.getIdentity())) {
                listPrisoners.set(i, prisoner);
                break;
            }
        }
        writeListPrisoners(listPrisoners);
    }

    /**
     * xóa prisoner trong listPrisoners và lưu listPrisoners vào file
     *
     * @param prisoner
     */
    public void delete(Prisoner prisoner)
    {
        for (int i = 0; i < listPrisoners.size(); i++) {
            if (listPrisoners.get(i).getIdentity().equals(prisoner.getIdentity())) {
                System.out.println("Del");
                listPrisoners.remove(i);
                break;
            }
        }
        System.out.println("Complete");
        System.out.println(listPrisoners.size());
        writeListPrisoners(listPrisoners);
    }

}
