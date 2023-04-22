package Func;

import Entity.Visitor;
import Entity.VisitorXML;
import Utils.FileUtils;

import java.util.List;
import java.util.ArrayList;
public class VisitorFunc {
    public static final String Visitor_FILE_NAME = "src/main/java/visitor.xml";
    private List<Visitor> listVisitors;
    public VisitorFunc(){this.listVisitors = readListVisitors();}
    /**
     * Lưu các đối tượng prison vào file prison.xml
     */
    public void writeListVisitors(List<Visitor> visitors) {
        List<Visitor> list = new ArrayList<>(visitors);
        list.sort((o1, o2) -> o1.getRegister().compareTo(o2.getRegister()));
        VisitorXML visitorXML = new VisitorXML();
        visitorXML.setVisitor(list);
        FileUtils.writeXMLToFile(Visitor_FILE_NAME, visitorXML);
    }
    /**
     * Đọc các đối tượng visitor từ file visitor.xml
     */
    public List<Visitor> readListVisitors() {
        List<Visitor> list = new ArrayList<>();
        VisitorXML visitorXML = (VisitorXML) FileUtils.readXMLFile(
                Visitor_FILE_NAME, VisitorXML.class);
        if (visitorXML != null) {
            list = visitorXML.getVisitor();
        }
        return list;
    }


    public void addVisitor(Visitor visitor) {
        // 1 ngày 1 người tù chỉ được gặp 1 người
        List<Visitor> list = readListVisitors();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(visitor);
        writeListVisitors(list);
    }

    public static void main(String[] args) {
        VisitorFunc visitorFunc = new VisitorFunc();
        List<Visitor> list = visitorFunc.readListVisitors();
        for (Visitor visitor : list) {
            System.out.println(visitor);
        }
    }


}
