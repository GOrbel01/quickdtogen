package org.fsq.entity.test;

import org.fsq.processor.GeneratesDto;

@GeneratesDto
public class TestEntity {
    private String testName;
    private Integer testNumber;
    private String randomFieldNameA;
    private TestField testField;

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
}
