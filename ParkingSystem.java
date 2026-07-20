public class ParkingSystem {
    
    private int bigSlots;
    private int mediumSlots;
    private int smallSlots;

    public ParkingSystem(int big, int medium, int small) {
        this.bigSlots = big;
        this.mediumSlots = medium;
        this.smallSlots = small;
    }
    
    public boolean addCar(int carType) {
        
        if (carType == 1) {
            if (this.bigSlots > 0) {
                this.bigSlots--; // Take a spot
                return true;
            } else {
                return false;
            }
        } 
        else if (carType == 2) {
            if (this.mediumSlots > 0) {
                this.mediumSlots--; // Take a spot
                return true;
            } else {
                return false;
            }
        } 
        else if (carType == 3) {
            if (this.smallSlots > 0) {
                this.smallSlots--; // Take a spot
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
}