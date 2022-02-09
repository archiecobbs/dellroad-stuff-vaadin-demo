
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.dellroad.stuff.vaadin22.fieldbuilder.FieldBuilder;

public class Vehicle implements HasName {

    private String name;
    private Country countryOfOrigin;

    @Override
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @FieldBuilder.EnumComboBox(
      minWidth = "150px",
      placeholder = "Choose...",
      required = true,
      requiredIndicatorVisible = true)
    @NotNull
    public Country getCountryOfOrigin() {
        return this.countryOfOrigin;
    }
    public void setCountryOfOrigin(final Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

// Object

    @Override
    public String toString() {
        return String.format("Vehicle[name=\"%s\",country=%s]",
          this.name, Optional.ofNullable(this.countryOfOrigin).map(Country::name).orElse(null));
    }
}
