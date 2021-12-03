package Project;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Service implements Serializable {

    private GregorianCalendar serviceDate;
    private String dealerOrSelf;
    private String serviceNotes;

    public Service(GregorianCalendar serviceDate, String dealerOrSelf, String serviceNotes) {
        setServiceDate(serviceDate);
        setDealerOrSelf(dealerOrSelf);
        setServiceNotes(serviceNotes);
    }

    public Service() {

    }

    public GregorianCalendar getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(GregorianCalendar serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getDealerOrSelf() {
        return dealerOrSelf;
    }

    public void setDealerOrSelf(String dealerOrSelf) {
        this.dealerOrSelf = dealerOrSelf;
    }

    public String getServiceNotes() {
        return serviceNotes;
    }

    public void setServiceNotes(String serviceNotes) {
        this.serviceNotes = serviceNotes;
    }

    public String toString(){
        return "Service Type: " + getDealerOrSelf() + "\nService Date: " + getServiceDate().get(Calendar.DAY_OF_MONTH) + " "
                + getServiceDate().get(Calendar.MONTH) + " " + getServiceDate().get(Calendar.YEAR) + "\nService Notes: " + getServiceNotes() + "\n\n";
    }
}
