
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.model;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import org.dellroad.stuff.vaadin22.field.FieldBuilder;

public enum Country {
    FRANCE("fr", "France"),
    GERMANY("de", "Germany"),
    GREECE("gr", "Greece"),
    ITALY("it", "Italy"),
    NEW_ZEALAND("nz", "New Zealand"),
    SPAIN("es", "Spain"),
    UNITED_KINGDOM("gb", "UK"),
    UNITED_STATES("us", "USA");

    private final String code;
    private final String name;

    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public Image getIcon() {
        final Image image = new Image("../images/flags/" + this.code + ".png", this.name);
        image.setWidth(16, Unit.PIXELS);
        image.setHeight(11, Unit.PIXELS);
        return image;
    }

// Defaults for FieldBuilder fields

    @FieldBuilder.FieldDefault("itemLabelGenerator")
    private static ItemLabelGenerator<Country> itemLabelGenerator() {
        return Country::getName;
    }

    @FieldBuilder.FieldDefault("renderer")
    private static ComponentRenderer<HorizontalLayout, Country> renderer() {
        return new ComponentRenderer<HorizontalLayout, Country>(country -> {
            final HorizontalLayout layout = new HorizontalLayout();
            layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
            layout.add(country.getIcon());
            layout.add(new Text(country.getName()));
            return layout;
        });
    }
}
