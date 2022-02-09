
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.dellroad.stuff.vaadin22.fieldbuilder.FieldBuilder;

public class Person implements HasName {

    private String name;
    private LocalDate birthDate;
    private Vehicle vehicle;

    @Override
    @FieldBuilder.FormLayout(order = 1)
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @FieldBuilder.DatePicker
    @FieldBuilder.FormLayout(order = 2, label = "Birthday")
    @NotNull
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @FieldBuilder.FormLayout(order = 3)
    @FieldBuilder.NullifyCheckbox("Has a vehicle")
    @FieldBuilder.CustomField(implementation = VehicleCustomField.class)
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

// Object

    @Override
    public String toString() {
        return String.format("Person[name=\"%s\",birthDate=%s,vehicle=%s]", this.name, this.birthDate, this.vehicle);
    }
}
