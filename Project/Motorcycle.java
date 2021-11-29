package Project;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;

public class Motorcycle implements Serializable {

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

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Motorcycle(String vin, Part partList[]){
        setVin(vin);
        setPartList(partList);
    }

    public Motorcycle(){
        setVin("00000000000000000");
        setPartList(null);
        setServiceHistory(null);
        setEngineType("null");
        setModelName("null");
        setManufacturer("null");
        setEngineDisplacement(0);
        setYearCode(0);
        setVis("null");
        setCountryOfOrigin("null");

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

        if(engineDisplacement < 0){
            JOptionPane.showMessageDialog(null, "Engine displacement must be greater than 0", "Invalid Displacement",
                     JOptionPane.WARNING_MESSAGE);
        }
        else{
            this.engineDisplacement = engineDisplacement;
        }

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

        if(vin.equals("00000000000000000")){
            this.vin = "No Vin Provided";
        }
        else{
            this.vin = vin;

            boolean manufacturerSet = false;
            boolean vdsSet = false;
            boolean modelSet = false;
            boolean yearSet = false;
            boolean displacementSet = false;
            boolean engineTypeSet = false;

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
            char suzukiEngineTypeCodes[] = {'1', '2', '3', '4', '5', '7'}; //Suzuki does not have an engine type with the designation
            String suzukiEngineTypeValues[] = {"2-Stroke Single", "2-Stroke Twin", "2-Stroke Triple or Four", "4-Stroke Single",
                    "4-Stroke Twin", "4-Stroke Four"};

            char yearCodes[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'};

            if(getVin().charAt(0) != 'J'){
                recognitionWarnings += "Could Not Recognize Country Code";
            }
            else{
                setCountryOfOrigin("Japan");
            }

            //Finding Manufacturer
            for(int j = 0; j < manufacturerCodes.length; j++){
                if(getVin().charAt(1) == manufacturerCodes[j]){
                    setManufacturer(manufacturers[j]);
                    manufacturerSet = true;
                }
            }

            if(manufacturerSet == false){
                recognitionWarnings+="Could not recognize manufacturer\n";
            }


            //Finding model name from VDS
            String modelCodeVds = getVin().substring(3,6);
            System.out.print(modelCodeVds);

            for(int j = 0; j < suzukiModelCodes.length; j++){
                if(modelCodeVds.equals(suzukiModelCodes[j])){
                    setModelName(suzukiModelNames[j]);
                    modelSet = true;
                }
            }

            if(modelSet == false){
                recognitionWarnings+="Could not recognize model\n";
            }

            //Handling numeric year code
            //Don't actually -48, bug somewhere
            if(Character.isDigit(vin.charAt(9))){
                setYearCode(2000 + vin.charAt(9) -48);
                yearSet = true;
            }//Handling alphabetical year code, position in array + 10 + 2000;
            else{
                for(int i = 0; i < yearCodes.length; i++){
                    if(vin.charAt(9) == yearCodes[i]){
                        setYearCode(2000 + i + 10);
                        yearSet = true;
                    }
                }
            }
            if(yearSet == false){
                recognitionWarnings+="Could not recognize year\n";
            }

            //Engine Displacement
            for(int z = 0; z < suzukiDisplacementCodes.length; z++){
                if(getVin().charAt(4) == suzukiDisplacementCodes[z]){
                    setEngineDisplacement(suzukiDisplacementValues[z]);
                    displacementSet = true;
                }
            }

            if(displacementSet == false){
                recognitionWarnings+="Could not recognize engine displacement\n";
            }

            //Engine Type
            for(int z = 0; z < suzukiEngineTypeCodes.length; z++){
                if(getVin().charAt(5) == suzukiEngineTypeCodes[z]){
                    setEngineType(suzukiEngineTypeValues[z]);
                    engineTypeSet = true;
                }
            }
            if(engineTypeSet == false){
                recognitionWarnings+="Could not recognize engine type\n";
            }

            //Set VIS
            setVis(vin.substring(10,16));


            //Building warning message indicating which attributes could not be decoded from VIN
            if(!recognitionWarnings.equals("")){
                JOptionPane.showMessageDialog(null, "Your VIN may not have been full recognized, you fill need to manually enter the following fields:\n\n" + recognitionWarnings, "VIN Decode Failed",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }


    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void addPart(Part partToAdd){
        for(int j = 0; j < getPartList().length; j++){
            if(getPartList()[j] == null){
                getPartList()[j] = partToAdd;
                j = getPartList().length;
            }
        }
    }

    public void addService(Service serviceToAdd){
        for(int j = 0; j < getServiceHistory().length; j++){
            if(getServiceHistory()[j] == null){
                getServiceHistory()[j] = serviceToAdd;
                j = getServiceHistory().length;
            }
        }
    }

    @Override
    public String toString() {

        String partsOutput = "";
        //Avoid null prints
        for(int i = 0; i < partList.length; i++){
            if(partList[i] != null){
                partsOutput+= partList[i].toString();
            }
            else{
                //Terminating loop
                i = partList.length;
            }
        }

        String servicesOutput = "";
        //Avoid null prints
        for(int i = 0; i < serviceHistory.length; i++){
            if(serviceHistory[i] != null){
                servicesOutput+= serviceHistory[i].toString();
            }
            else{
                //Terminating loop
                i = serviceHistory.length;
            }
        }


        return "Motorcycle{" +
                "vin='" + getVin() + '\'' +
                ", modelName='" + getModelName() + '\'' +
                ", manufacturer='" + getManufacturer() + '\'' +
                ", engineDisplacement=" + getEngineDisplacement() +
                ", engineType='" + getEngineType() + '\'' +
                ", yearCode=" + getYearCode() +
                ", odometer=" + getOdometer() +
                ", vis='" + getVis() + '\'' +
                ", country of origin=" + getCountryOfOrigin() +
                ", serviceHistory=" + servicesOutput +
               ", partList=" + partsOutput +
                '}';
    }



}
