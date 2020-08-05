package org.fsq;

import org.fsq.processor.GeneratesDto;
import org.fsq.randompackage.ExtraField;

import java.util.List;

@GeneratesDto
public class Car {
    private String carName;
    private String carBrand;
    private Integer milesCovered;
    private ExtraField extraField;
    private List<String> testList;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getMilesCovered() {
        return milesCovered;
    }

    public void setMilesCovered(Integer milesCovered) {
        this.milesCovered = milesCovered;
    }

    public ExtraField getExtraField() {
        return extraField;
    }

    public void setExtraField(ExtraField extraField) {
        this.extraField = extraField;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }
}
