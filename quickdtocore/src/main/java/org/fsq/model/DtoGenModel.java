package org.fsq.model;

import com.github.mustachejava.util.DecoratedCollection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DtoGenModel {
    private ZonedDateTime genDate;
    private String packageName;
    private String sourceClassName;
    private String targetClassName;
    private String comments;
    private List<GenItem> items = new ArrayList<>();

    public String getGenDate() {
        return genDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public void setGenDate(ZonedDateTime genDate) {
        this.genDate = genDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSourceClassName() {
        return sourceClassName;
    }

    public void setSourceClassName(String sourceClassName) {
        this.sourceClassName = sourceClassName;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public String getFullClassNameAndPath() {
        return packageName + "." + targetClassName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DecoratedCollection<GenItem> getItems() {
        return new DecoratedCollection<>(items);
    }

    public void setItems(List<GenItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "DtoGenModel{" +
                "date=" + genDate +
                ", packageName='" + packageName + '\'' +
                ", sourceClassName='" + sourceClassName + '\'' +
                ", targetClassName='" + targetClassName + '\'' +
                ", items=" + items +
                '}';
    }
}
