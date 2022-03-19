
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.field;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import org.dellroad.stuff.vaadin22.demo.model.Vehicle;
import org.dellroad.stuff.vaadin22.field.FieldComponent;

@SuppressWarnings("serial")
public class VehicleField extends DemoCustomField<Vehicle> {

    public VehicleField() {
        super(Vehicle.class);
    }

    // Customize how we layout the sub-fields
    @Override
    protected void layoutComponents() {

        // Get sub-fields
        final FieldComponent<?> nameField = this.fieldBuilder.getFieldComponents().get("name");
        final FieldComponent<?> countryField = this.fieldBuilder.getFieldComponents().get("countryOfOrigin");
        final FieldComponent<?> termField = this.fieldBuilder.getFieldComponents().get("leaseTerm");

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
