package Project;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Part implements Serializable {

    private String name;
    private String description;
    private GregorianCalendar installDate;

    public Part(String name, String description, GregorianCalendar installDate) {
        setName(name);
        setDescription(description);
        setInstallDate(installDate);
    }

    public Part() {
        setName("Name not set");
        setDescription("Description not set");
        setInstallDate(new GregorianCalendar(2000, 1, 1));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String afterMarket) {
        this.description = afterMarket;
    }

    public GregorianCalendar getInstallDate() {
        return installDate;
    }

    public void setInstallDate(GregorianCalendar installDate) {
        this.installDate = installDate;
    }

    public String toString(){

        //https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html Found gregorian calendar methods in java docs
        return "Name: " + getName() + "\n" + getDescription() + "\nInstalled: " + getInstallDate().get(Calendar.DAY_OF_MONTH) + " " + getInstallDate().get(Calendar.MONTH) + " " + getInstallDate().get(Calendar.YEAR);
    }
}
