package org.fsq.entity;

import org.fsq.annotation.GeneratesDto;
import org.fsq.annotation.ReferencesDto;
import org.fsq.entity.alt.OtherEntity;

import java.util.List;

@GeneratesDto
public class TestEntity {
    private String testName;
    private Integer testNumber;
    private String randomFieldNameA;
    private TestField testField;
    private List<String> list;
    @ReferencesDto
    private OtherEntity otherEntity;

    public TestEntity() {

    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }

    public String getRandomFieldNameA() {
        return randomFieldNameA;
    }

    public void setRandomFieldNameA(String randomFieldNameA) {
        this.randomFieldNameA = randomFieldNameA;
    }

    public TestField getTestField() {
        return testField;
    }

    public void setTestField(TestField testField) {
        this.testField = testField;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public OtherEntity getOtherEntity() {
        return otherEntity;
    }

    public void setOtherEntity(OtherEntity otherEntity) {
        this.otherEntity = otherEntity;
    }
}
