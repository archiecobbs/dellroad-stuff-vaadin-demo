
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.model;

import javax.validation.constraints.NotNull;

import org.dellroad.stuff.vaadin22.data.EnumDataProvider;
import org.dellroad.stuff.vaadin22.demo.field.DateRangeField;
import org.dellroad.stuff.vaadin22.field.FieldBuilder;

public class Vehicle implements HasName {

    private String name;
    private Country countryOfOrigin;
    private DateRange leaseTerm;

    @FieldBuilder.FormLayout(order = 0, colspan = 1)
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @FieldBuilder.FormLayout(order = 1, colspan = 2)
    @FieldBuilder.ComboBox(
      items = EnumDataProvider.class,
      minWidth = "150px",
      placeholder = "Choose...",
      required = true,
      requiredIndicatorVisible = true)
    @NotNull(message = "Country is required")
    public Country getCountryOfOrigin() {
        return this.countryOfOrigin;
    }
    public void setCountryOfOrigin(final Country countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @FieldBuilder.FormLayout(order = 2, colspan = 1)
    @FieldBuilder.CustomField(implementation = DateRangeField.class)
    @NotNull(message = "Lease term is required")
    public DateRange getLeaseTerm() {
        return this.leaseTerm;
    }
    public void setLeaseTerm(final DateRange leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

// Object

    @Override
    public String toString() {
        return String.format("Vehicle[name=\"%s\",country=%s,term=%s]",
          this.name, this.countryOfOrigin, this.leaseTerm);
    }
}
