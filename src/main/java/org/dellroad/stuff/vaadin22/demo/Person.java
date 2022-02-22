
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.dellroad.stuff.vaadin22.data.EnumDataProvider;
import org.dellroad.stuff.vaadin22.field.FieldBuilder;

public class Person implements HasName {

    private String name;
    private LocalDate birthDate;
    private Vehicle vehicle;
    private DateRange contract;
    private Set<Country> favoriteCountries;

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
    @FieldBuilder.NullifyCheckbox("Has birthday:")
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @FieldBuilder.FormLayout(order = 3, label = "Vehicle")
    @FieldBuilder.CustomField(implementation = VehicleField.class)
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @FieldBuilder.FormLayout(order = 4, label = "Term")
    @FieldBuilder.CustomField(implementation = DateRangeField.class)
    @DateRangeField.Layout(direction = DateRangeField.Direction.VERTICAL)
    @NotNull(message = "Term is required")
    public DateRange getContract() {
        return this.contract;
    }
    public void setContract(final DateRange contract) {
        this.contract = contract;
    }

    @FieldBuilder.FormLayout(order = 2.5, label = "Favorite Countries")
    @FieldBuilder.GridMultiSelect(
      width = "300px",
      height = "200px",
      items = EnumDataProvider.class)
    @NotNull(message = "Required")
    public Set<Country> getFavoriteCountries() {
        return this.favoriteCountries;
    }
    public void setFavoriteCountries(final Set<Country> favoriteCountries) {
        this.favoriteCountries = favoriteCountries;
    }

// Object

    @Override
    public String toString() {
        return String.format("Person[name=\"%s\",birthDate=%s,vehicle=%s,contract=%s,favoriteCountries=%s]",
          this.name, this.birthDate, this.vehicle, this.contract, this.favoriteCountries);
    }
}
