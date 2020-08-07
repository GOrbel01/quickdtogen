package org.fsq;

import org.fsq.annotation.GeneratesDto;

@GeneratesDto
public class RandomEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
