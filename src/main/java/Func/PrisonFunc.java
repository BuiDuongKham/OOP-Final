package Func;

import Entity.Prison;
import Entity.PrisonXML;
import Entity.Prisoner;
import Utils.FileUtils;

import java.util.List;
import java.util.ArrayList;
public class PrisonFunc {
    public static final String PRISON_FILE_NAME = "src/main/java/prison.xml";
    private List<Prison> listPrisons;
    public PrisonFunc(){this.listPrisons = readListPrisons();}
        /**
         * Lưu các đối tượng prison vào file prison.xml
          */
    public void writeListPrisons(List<Prison> prisons) {
        PrisonXML prisonXML = new PrisonXML();
        prisonXML.setPrison(prisons);
        FileUtils.writeXMLToFile(PRISON_FILE_NAME, prisonXML);
    }
        /**
         * Đọc các đối tượng prison từ file prison.xml
         */
    public List<Prison> readListPrisons() {
        List<Prison> list = new ArrayList<Prison>();
        PrisonXML prisonXML = (PrisonXML) FileUtils.readXMLFile(
                PRISON_FILE_NAME, PrisonXML.class);
        if (prisonXML != null) {
            list = prisonXML.getPrison();
        }
        if (list == null) {
            list = new ArrayList<Prison>();
        }
        // kiểm tra lại trong file prisoner và ghi đè lên numberOfPrisoner
        PrisonerFunc prisonerFunc = new PrisonerFunc();
        for (Prison prison : list) {
            List<Prisoner> prisoners = prisonerFunc.getListPrisonersByPrisonId(prison.getId());
            if (prisoners == null) {
                prison.setNumberPrisoner(0);
            } else {
                prison.setNumberPrisoner(prisoners.size());
            }
        }
        this.writeListPrisons(list);
        if (prisonXML != null) {
            list = prisonXML.getPrison();
        }
        return list;
    }

    /**
     * thêm prison vào listPrisons và lưu listPrisons vào file
     *
     * @param prison
     */

    public void add(Prison prison) {

        System.out.println(prison.getPrisoners().size());
        if (listPrisons == null) {
            listPrisons = new ArrayList<Prison>();
        }

        // Kiểm tra xem id đã tồn tại chưa
        for (Prison p : listPrisons) {
            if (p.getId() == prison.getId()) {
                return;
            }
        }
        listPrisons.add(prison);
        writeListPrisons(listPrisons);
    }

    /**
     * cập nhật prison vào listPrisons và lưu listPrisons vào file
     *
     * @param prison
     */

    public void edit(Prison prison)
    {
        int size = listPrisons.size();
        for (int i = 0; i < size; i++) {
            if (listPrisons.get(i).getId() == prison.getId()) {
                listPrisons.set(i, prison);
                break;
            }
        }

        writeListPrisons(listPrisons);
    }

    /**
     * xóa prison khỏi listPrisons và lưu listPrisons vào file
     *
     * @param prison
     */
    public void delete(Prison prison)
    {
        int size = listPrisons.size();
        for (int i = 0; i < size; i++) {
            if (listPrisons.get(i).getId() == prison.getId()) {
                listPrisons.remove(i);
                break;
            }
        }
        writeListPrisons(listPrisons);
    }

    public List<Prison> getListPrisons() {
        return listPrisons;
    }

    public void setListPrisons(List<Prison> listPrisons) {
        this.listPrisons = listPrisons;
    }
}
