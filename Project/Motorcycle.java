package Project;

import javax.swing.*;

public class Motorcycle {

    private String vin;
    private String modelName;
    private String manufacturer;
    private int engineDisplacement;
    private String engineType;
    private int yearCode;
    private static int motorcycleSystemNumber = 0;
    private Service[] serviceHistory;
    private Part[] partList;

    public Motorcycle(String vin, String modelName, String manufacturer, int engineDisplacement, String engineType, int yearCode, Service[] serviceHistory, Part[] partList) {

        motorcycleSystemNumber++;
        setVin(vin);
        setModelName(modelName);
        setManufacturer(manufacturer);
        setEngineDisplacement(engineDisplacement);
        setEngineType(engineType);
        setYearCode(yearCode);
        setServiceHistory(serviceHistory);
        setPartList(partList);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getYearCode() {
        return yearCode;
    }

    public void setYearCode(int yearCode) {
        this.yearCode = yearCode;
    }

    public Service[] getServiceHistory() {
        return serviceHistory;
    }

    public void setServiceHistory(Service[] serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public Part[] getPartList() {
        return partList;
    }

    public void setPartList(Part[] partList) {
        this.partList = partList;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {

        String recognitionWarnings = "";
        String suzukiModelCodes[] = {"BK", "BL", "BG", "BF", "BF", "BB", "AK1", "AJ1"};
        String suzukiModelNames[] = {"GS500", "GSX-R1000", "GSX-R600", "DR-Z400", "DR-Z400", "GSX750F", "GSX600F"};


        if(vin.charAt(0) != 'J'){
            recognitionWarnings += "Could Not Recognize Country Code";
        }

        if(!recognitionWarnings.equals("")){
            JOptionPane.showMessageDialog(null, "Your VIN may not have been full recognized, you fill need to manually enter the following fields:\n\n" + recognitionWarnings, "VIN Decode Failed",
                    JOptionPane.INFORMATION_MESSAGE);
        }


        this.vin = vin;
    }

    public boolean validateVIN(String vin){

        if(vin.length() != 17){
            JOptionPane.showMessageDialog(null, "VINs must be 17 characters in length", "Invalid Length", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;

    }

}
