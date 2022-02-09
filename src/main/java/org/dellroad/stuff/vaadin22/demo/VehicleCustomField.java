
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import org.dellroad.stuff.vaadin22.fieldbuilder.FieldBuilder;
import org.dellroad.stuff.vaadin22.fieldbuilder.GeneratedCustomField;

@SuppressWarnings("serial")
public class VehicleCustomField extends GeneratedCustomField<Vehicle> {

    public VehicleCustomField() {
        super(Vehicle.class);
    }

    // Use our FieldBuilder cache for efficiency
    @Override
    protected FieldBuilder<Vehicle> createFieldBuilder() {
        return SessionSingleton.getInstance().newFieldBuilder(Vehicle.class);
    }

    // Customize how we layout the sub-fields
    @Override
    protected void layoutComponents() {

        // Get sub-fields
        final FieldBuilder.BoundField nameField = this.fieldBuilder.getBoundFields().get("name");
        final FieldBuilder.BoundField countryField = this.fieldBuilder.getBoundFields().get("countryOfOrigin");

        // Layout sub-fields horizontally
        final HorizontalLayout layout = new HorizontalLayout();
        this.add(layout);
        layout.add(new Text("Name:"));
        layout.add(nameField.getComponent());
        layout.add(new Text("Origin:"));
        layout.add(countryField.getComponent());
    }
}
