
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import org.dellroad.stuff.vaadin22.field.FieldBuilder;

@SuppressWarnings("serial")
public class VehicleField extends DemoCustomField<Vehicle> {

    public VehicleField() {
        super(Vehicle.class);
    }

    // Customize how we layout the sub-fields
    @Override
    protected void layoutComponents() {

        // Get sub-fields
        final FieldBuilder.BoundField nameField = this.fieldBuilder.getBoundFields().get("name");
        final FieldBuilder.BoundField countryField = this.fieldBuilder.getBoundFields().get("countryOfOrigin");
        final FieldBuilder.BoundField termField = this.fieldBuilder.getBoundFields().get("leaseTerm");

        // Layout sub-fields
        final HorizontalLayout layout = new HorizontalLayout();
        this.add(layout);
        layout.add(new Text("Name:"));
        layout.add(nameField.getComponent());
        layout.add(new Text("Origin:"));
        layout.add(countryField.getComponent());
        layout.add(new Text("Term:"));
        layout.add(termField.getComponent());
    }
}
