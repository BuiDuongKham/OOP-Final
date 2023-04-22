package Entity;

import java.util.ArrayList;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "prison")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prison implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;

    private int numberPrisoner;
    private ArrayList<Prisoner> prisoners;

    public Prison(int id,String name, String address, String phone) {
        this.id= id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.prisoners = new ArrayList<Prisoner>();
        this.numberPrisoner = 0;
    }

    public Prison() {
    }

    public int getNumberPrisoner() {
        return numberPrisoner;
    }

    public void setNumberPrisoner(int numberPrisoner) {
        this.numberPrisoner = numberPrisoner;
    }

    public void setPrisoners(ArrayList<Prisoner> prisoners) {
        this.prisoners = prisoners;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Prisoner> getPrisoners() {
        if (prisoners == null) {
            prisoners = new ArrayList<Prisoner>();
        }
        return prisoners;
    }

    public int countPrisoners() {
        return 0;
//        return prisoners.size();
    }
    public int getPrisonerIndex(String identity) {
        for (int i = 0; i < prisoners.size(); i++) {
            if (prisoners.get(i).getIdentity() == identity) {
                return i;
            }
        }
        return -1;
    }
    public boolean hasPrisoner(String identity) {
        return getPrisonerIndex(identity) != -1;
    }

    @Override
    public String toString() {
        return "Entity.Prison{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", prisoners=" + this.countPrisoners() +
                '}';
    }
}
