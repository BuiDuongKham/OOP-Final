package Entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prisoners")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrisonerXML {
   private List<Prisoner> prisoner;

    public List<Prisoner> getPrisoner() {
         return prisoner;
    }

    public void setPrisoner(List<Prisoner> prisoner) {
         this.prisoner = prisoner;
    }

    public static void main(String[] args) {

    }
}
