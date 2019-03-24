package Controller.Enitry;

import lombok.Data;
import lombok.ToString;

/**
 * @author 刘棋军
 * @date2019-03-11
 */

public class Address {
    private  String homeAddress;
    private  String schoolAddress;

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    @Override
    public String toString() {
        return homeAddress+schoolAddress;
    }
}
