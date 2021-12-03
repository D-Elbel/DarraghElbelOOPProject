package Project;

import javax.swing.*;
import java.io.Serializable;

//Motorcycle.java

/**
 * Instantiable class defining a Motorcycle object. This class contains methods allowing for the generation
 * of an object via Vehicle Identification Numbers
 * @author Darragh Elbel
 */

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
    private Service[] serviceHistory;
    private Part[] partList;

    /**
     * Motorcycle single argument constructor, this constructor calls the setVin() method which generates values
     * for the object.
     */

    public Motorcycle(String vin){
        setVin(vin);
    }

    /**
     * Motorcycle no-argument constructor, this constructor generates default values for a new motorcycle object.
     * @param vin is the Vehicle Identification Number of the Motorcycle
     * @param modelName is the model name of the Motorcycle
     * @param manufacturer is the manufacturer of the Motorcycle
     * @param engineDisplacement is the engine displacement of the Motorcycle in ccs
     * @param engineType is the engine type of the Motorcycle
     * @param yearcode is the year of registration of the Motorcycle
     * @param odometer is the odometer reading of the Motorcycle
     * @param vis is the Vehicle Identifier Section of the VIN of the Motorcycle
     * @param countryOfOrigin is the country of manufacture of the Motorcycle
     * @param serviceHistory is the array of services associated with the Motorcycle
     * @param partList is the array of parts associated with the Motorcycle
     * @param
     */
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

    /**
     * Method to get the value of attribute countryOfOrigin
     * @return a string indicating the motorcycle's country of origin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Method to set the value of attribute countryOfOrigin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Method to get the value of attribute odometer
     * @return an integer value specifying the current odometer reading
     */
    public int getOdometer() {
        return odometer;
    }

    /**
     * Method to set the value of attribute countryOfOrigin, setting odometer to 0 if invalid value is entered
     */
    public void setOdometer(int odometer) {

        if(odometer < 0){
            this.odometer = 0;
        }
        else{
            this.odometer = odometer;
        }
    }

    /**
     * Method getting the motorcycle's model name
     * @return a String containing the model name
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Method to set the value of attribute modelname
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return a String containing the manufacturer's name
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Method to set the value of attribute manufacturer
     */
    public void setManufacturer(String manufacturer) {

        this.manufacturer = manufacturer;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return an int containing the engine's displacement value in ccs the
     */
    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    /**
     * Method to set the value of attribute engine displacement
     * this method prevents a negative value being entered and displays
     * a warning message if this is attempted
     */
    public void setEngineDisplacement(int engineDisplacement) {

        if(engineDisplacement < 0){
            JOptionPane.showMessageDialog(null, "Engine displacement must be greater than 0", "Invalid Displacement",
                     JOptionPane.WARNING_MESSAGE);
        }
        else{
            this.engineDisplacement = engineDisplacement;
        }

    }

    /**
     * Method to get the type of engine
     * @return a String containing the motorcycle's engine type
     */
    public String getEngineType() {
        return engineType;
    }

    /**
     * Method to set the value of attribute engineType
     */
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return an int containing the registration year of the motorcycle
     */
    public int getYearCode() {
        return yearCode;
    }

    /**
     * Method to set the value of attribute yearCode
     */
    public void setYearCode(int yearCode) {
        this.yearCode = yearCode;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return an array of Service objects
     */
    public Service[] getServiceHistory() {
        return serviceHistory;
    }

    /**
     * Method to set the array of Service objects
     */
    public void setServiceHistory(Service[] serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return an array of Part objects
     */
    public Part[] getPartList() {
        return partList;
    }

    /**
     * Method to set the array of Part objects
     */
    public void setPartList(Part[] partList) {
        this.partList = partList;
    }

    /**
     * Method to get the value of attribute manufacturer
     * @return an int containing the engine's displacement value in ccs the
     */
    public String getVin() {
        return vin;
    }


    /**
     * Method to facilitate the creation of a motorcycle object via a VIN
     * This method decodes a 17 character long VIN and populates a Motorcycle object's
     * attributes with the information it decodes.
     */
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
            String suzukiModelCodes[] = {"BK1", "BK2", "BK3", "BK4", "BL", "BG", "BF", "BF", "BB", "AK1", "AJ1", "AV1", "AV2"};
            String suzukiModelNames[] = {"GS500", "GS500", "GS500", "GS500","GSX-R1000", "GSX-R600", "DR-Z400", "DR-Z400", "GSX750F", "GSX600F", "SV650", "SV650"};


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
            if(Character.isDigit(vin.charAt(9))){
                String testYear = vin.substring(8,9);
                //Help from Deirdre Lee solving issue parsing character
                setYearCode(2000 + Integer.parseInt(testYear));
                System.out.println(getYearCode());
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

    /**
     * Method to add a new part to the Motorcycle's Part array
     * New parts are added to the first null slot in the Part array.
     */
    public void addPart(Part partToAdd){
        for(int j = 0; j < getPartList().length; j++){
            if(getPartList()[j] == null){
                getPartList()[j] = partToAdd;
                j = getPartList().length;
            }
        }
    }

    /**
     * Method to add a new part to the Motorcycle's Service array
     * New parts are added to the first null slot in the Service array.
     */
    public void addService(Service serviceToAdd){
        for(int j = 0; j < getServiceHistory().length; j++){
            if(getServiceHistory()[j] == null){
                getServiceHistory()[j] = serviceToAdd;
                j = getServiceHistory().length;
            }
        }
    }

    /**
     * Method to return the state of a Motorcycle object
     * This method performs null checking on the Part and Service arrays
     * in order to produce a clean toString() output
     * @return A String value specificying the state of a Book object
     */
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
