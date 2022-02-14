
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import org.dellroad.stuff.vaadin22.fieldbuilder.FieldBuilder;

@SuppressWarnings("serial")
public class DateRangeField extends DemoCustomField<DateRange> {

    public DateRangeField() {
        super(DateRange.class);
    }

    // Customize how we layout the sub-fields
    @Override
    protected void layoutComponents() {

        // Get sub-fields
        final FieldBuilder.BoundField startDateField = this.fieldBuilder.getBoundFields().get("startDate");
        final FieldBuilder.BoundField endDateField = this.fieldBuilder.getBoundFields().get("endDate");

        // Layout sub-fields
        final HorizontalLayout layout = new HorizontalLayout();
        this.add(layout);
        layout.add(startDateField.getComponent());
        layout.add(new Text("-"));
        layout.add(endDateField.getComponent());
    }
}
