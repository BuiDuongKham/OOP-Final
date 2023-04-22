package Entity;

import java.util.ArrayList;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "visitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Visitor implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String register;
    private String prisonerName;
    private String prisonerID;

    public Visitor() {
    }

    public Visitor(String name, String address, String phone, String register, String prisonerName, String prisonerID) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.register = register;
        this.prisonerName = prisonerName;
        this.prisonerID = prisonerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegister() {
        return register;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public String getPrisonerID() {
        return prisonerID;
    }

    @Override
    public String toString() {
        return "Entity.Visitor{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", register='" + register + '\'' +
                ", prisonerName='" + prisonerName + '\'' +
                ", prisonerID='" + prisonerID + '\'' +
                '}';
    }
}
