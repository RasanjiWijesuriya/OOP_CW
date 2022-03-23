package lk.iit.oop.driver;

public class CarTeam {
    private String carId;
    private String carName;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public CarTeam() {
    }

    public CarTeam(String carId, String carName) {
        this.carId = carId;
        this.carName = carName;
    }

    @Override
    public String toString() {
        return "CarTeam{" +
                "carId='" + carId + '\'' +
                ", carName='" + carName + '\'' +
                '}';
    }
}
