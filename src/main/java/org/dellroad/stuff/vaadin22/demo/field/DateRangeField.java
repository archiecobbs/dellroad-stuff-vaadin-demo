
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.field;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import org.dellroad.stuff.vaadin22.demo.model.DateRange;
import org.dellroad.stuff.vaadin22.field.FieldBuilderContext;
import org.dellroad.stuff.vaadin22.util.VaadinUtil;

@SuppressWarnings("serial")
public class DateRangeField extends DemoCustomField<DateRange> {

    private final Direction direction;

    public DateRangeField(FieldBuilderContext ctx) {
        this(Optional.ofNullable(ctx.getMethod().getAnnotation(Layout.class))
          .map(Layout::direction)
          .orElse(Direction.HORIZONTAL));
    }

    public DateRangeField(Direction direction) {
        super(DateRange.class);
        if (direction == null)
            throw new IllegalArgumentException("null direction");
        this.direction = direction;
    }

    // Avoid constructor ordering issue
    @Override
    protected void initialize() {
        VaadinUtil.accessSession(() -> super.initialize());
    }

    // Customize how we layout the sub-fields
    @Override
    protected void layoutComponents() {

        // Get sub-field components
        final Component startDate = this.fieldBuilder.getFieldComponents().get("startDate").getComponent();
        final Component endDate = this.fieldBuilder.getFieldComponents().get("endDate").getComponent();

        // Build sub-field layout
        final Component layout;
        switch (this.direction) {
        case VERTICAL:
            final FormLayout formLayout = new FormLayout();
            formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("1px", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
            formLayout.addFormItem(startDate, "Starting:");
            formLayout.addFormItem(endDate, "Ending:");
            this.add(formLayout);
            break;
        case HORIZONTAL:
            this.add(new HorizontalLayout(startDate, new Text("-"), endDate));
            break;
        default:
            throw new RuntimeException();
        }
    }

// Layout

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Documented
    public @interface Layout {
        Direction direction();
    }

// Direction

    public enum Direction {
        HORIZONTAL,
        VERTICAL;
    }
}
