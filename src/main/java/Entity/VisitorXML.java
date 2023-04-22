package Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visitors")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorXML {
    private List<Visitor> visitor;

    public List<Visitor> getVisitor() {
        return visitor;
    }

    public void setVisitor(List<Visitor> visitor) {
        this.visitor = visitor;
    }

    public static void main(String[] args) {

    }
}
