package Project;

import java.util.GregorianCalendar;

public class Part{

    private String name;
    private String afterMarket;
    private GregorianCalendar installDate;

    public Part(String name, String afterMarket, GregorianCalendar installDate) {
        setName(name);
        setAfterMarket(afterMarket);
        setInstallDate(installDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAfterMarket() {
        return afterMarket;
    }

    public void setAfterMarket(String afterMarket) {
        this.afterMarket = afterMarket;
    }

    public GregorianCalendar getInstallDate() {
        return installDate;
    }

    public void setInstallDate(GregorianCalendar installDate) {
        this.installDate = installDate;
    }
}
