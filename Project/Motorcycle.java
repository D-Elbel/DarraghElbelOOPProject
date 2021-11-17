package Project;

import javax.swing.*;
import java.util.Arrays;

public class Motorcycle {

    private String vin;
    private String modelName;
    private String manufacturer;
    private int engineDisplacement;
    private String engineType;
    private int yearCode;
    private int odometer;
    private String vis;
    private String countryOfOrigin;
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

    public Motorcycle(String vin){
        setVin(vin);
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {

        if(odometer < 0){
            this.odometer = 0;
        }
        else{
            this.odometer = odometer;
        }


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

       this.vin = vin;

       String recognitionWarnings = "";



        //Manufacturer Codes
        char manufacturerCodes[] = {'S', 'Y', 'K', 'H'};
        String manufacturers[] = {"Suzuki", "Yamaha", "Kawasaki", "Honda"};

        //Model Name Information
        String suzukiModelCodes[] = {"BK1", "BK2", "BK3", "BK4", "BL", "BG", "BF", "BF", "BB", "AK1", "AJ1"};
        String suzukiModelNames[] = {"GS500", "GS500", "GS500", "GS500","GSX-R1000", "GSX-R600", "DR-Z400", "DR-Z400", "GSX750F", "GSX600F"};


        //VDS Information
        char suzukiDisplacementCodes[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int suzukiDisplacementValues[] = {49, 69, 79, 89, 99, 124, 149, 150, 249, 399, 499, 599, 699, 749, 849, 999, 1099,
        1199,1299,1399,1499, 1500};

        //Engine Configuration Information
        int suzukiEngineTypeCodes[] = {1, 2, 3, 4, 5, 7}; //Suzuki does not have an engine type with the designation '6'
        String suzukiEngineTypeValues[] = {"2-Stroke Single", "2-Stroke Twin", "2-Stroke Triple or Four", "4-Stroke Single",
        "4-Stroke Twin", "4-Stroke Four"};

        char yearCodes[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'};

        //Building warning message indicating which attributes could not be decoded from VIN


        if(getVin().charAt(0) != 'J'){
            recognitionWarnings += "Could Not Recognize Country Code";
        }

        //Finding Manufacturer
        for(int j = 0; j < manufacturerCodes.length; j++){
            if(getVin().charAt(1) == manufacturerCodes[j]){
                setManufacturer(manufacturers[j]);
            }
        }

        //Finding model name from VDS
        String modelCodeVds = getVin().substring(3,6);
        System.out.print(modelCodeVds);

        for(int j = 0; j < suzukiModelCodes.length; j++){
            if(modelCodeVds.equals(suzukiModelCodes[j])){
                setModelName(suzukiModelNames[j]);
            }
        }


/*
        //Handling numeric year code
        if(Character.isDigit(vin.charAt(9))){
            setYearCode(2000 + vin.charAt(9));
        }//Handling alphabetical year code, position in array + 10 + 2000;
        else{
            for(int i = 0; i < yearCodes.length; i++){
                if(vin.charAt(i) == yearCodes[i]){
                    setYearCode(2000 + i + 10);
                }
            }
        }

        if(!recognitionWarnings.equals("")){
            JOptionPane.showMessageDialog(null, "Your VIN may not have been full recognized, you fill need to manually enter the following fields:\n\n" + recognitionWarnings, "VIN Decode Failed",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        */
        //System.out.println("Setting Vin");



    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "vin='" + getVin() + '\'' +
                ", modelName='" + getModelName() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", engineDisplacement=" + getEngineDisplacement() +
                ", engineType='" + getEngineType() + '\'' +
                ", yearCode=" + getYearCode() +
                ", odometer=" + getOdometer() +
                ", vis='" + getVis() + '\'' +
               // ", serviceHistory=" + Arrays.toString(serviceHistory) +
               // ", partList=" + Arrays.toString(partList) +
                '}';
    }

    public boolean validateVIN(String vin){

        String visSerialCheck = "";

        //Verifying length
        if(vin.length() != 17){
            JOptionPane.showMessageDialog(null, "VINs must be 17 characters in length", "Invalid Length", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        //Verifying valid VIS format
        visSerialCheck = vin.substring(10,16);

        for(int i = 0; i < visSerialCheck.length(); i++){
            if(Character.isDigit(visSerialCheck.charAt(i)) == false){
                JOptionPane.showMessageDialog(null, "Motorcycle VIS be a numeric value six digits in length", "Invalid VIS Format", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;

    }

}
