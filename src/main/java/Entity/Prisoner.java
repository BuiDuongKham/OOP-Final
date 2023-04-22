package Entity;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prisoner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prisoner implements Serializable {
    private static Set<String> identitySet = new HashSet<String>();
    private String name;
    private int age;
    private String address;
    private String identity;
    private String crime;
    private int punishment;
    private String dateIn;
    private int prisonId;

    public Prisoner() {
        super();
    }

    public Prisoner(String name, int age, String address, String identity, String crime, int punishment, String dateIn, int prisonId) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.identity = identity;
        this.crime = crime;
        this.punishment = punishment;
        this.dateIn = dateIn;
        this.prisonId = prisonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        identitySet.remove(this.identity);
        this.identity = identity;
        identitySet.add(identity);
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public int getPunishment() {
        return punishment;
    }

    public void setPunishment(int punishment) {
        this.punishment = punishment;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public int getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(int id) {
    }

    @Override
    public String toString() {
        return "Entity.Prisoner [name=" + name + ", identity=" + identity + ", crime=" + crime + ", punishment=" + punishment
                + ", dateIn=" + dateIn + ", prisonId=" + prisonId + "]";
    }
}
