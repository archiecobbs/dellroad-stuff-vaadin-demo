
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.model;

import java.time.LocalDate;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

import org.dellroad.stuff.vaadin22.field.FieldBuilder;
import org.dellroad.stuff.validation.SelfValidates;
import org.dellroad.stuff.validation.SelfValidating;
import org.dellroad.stuff.validation.SelfValidationException;

@SelfValidates
public class DateRange implements SelfValidating {

    private LocalDate startDate;
    private LocalDate endDate;

    @FieldBuilder.DatePicker(width = "120px", placeholder = "Starting")
    @FieldBuilder.FormLayout(order = 1)
    @NotNull(message = "Required")
    public LocalDate getStartDate() {
        return this.startDate;
    }
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    @FieldBuilder.DatePicker(width = "120px", placeholder = "Ending")
    @FieldBuilder.FormLayout(order = 2)
    @NotNull(message = "Required")
    public LocalDate getEndDate() {
        return this.endDate;
    }
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

// Validation

    @Override
    public void checkValid(ConstraintValidatorContext context) throws SelfValidationException {
        if (this.startDate != null && this.endDate != null && this.endDate.isBefore(this.startDate))
            throw new SelfValidationException("Out-of-order dates");
    }

// Object

    @Override
    public String toString() {
        return String.format("DateRange[start=%s,end=%s]", this.startDate, this.endDate);
    }
}
