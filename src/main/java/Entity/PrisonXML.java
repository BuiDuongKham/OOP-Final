package Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prisons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrisonXML {
    private List<Prison> prison;

     public List<Prison> getPrison() {
            return prison;
     }

     public void setPrison(List<Prison> prison) {
            this.prison = prison;
     }
}
