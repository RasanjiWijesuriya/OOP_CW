package lk.iit.oop.manage;

import lk.iit.oop.driver.Formula1Driver;

public interface ChampionshipManager {
    void addDriver(Formula1Driver formula1Driver);
    //pass driver id for delete option
    void deleteDriver(String driverId);

}
