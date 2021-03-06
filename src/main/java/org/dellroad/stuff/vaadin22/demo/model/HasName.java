
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.dellroad.stuff.vaadin22.field.FieldBuilder;

/**
 * Implemented by classes that have a name.
 *
 * <p>
 * The name is mandatory and must consist of one or more alphabetic words separated by a single space.
 */
public interface HasName {

    String PATTERN = "^[\\p{Alpha}]+( [\\p{Alpha}]+)*$";
    int MAX_LENGTH = 12;

    @FieldBuilder.Binding(nullRepresentation = "")
    @FieldBuilder.FormLayout(order = 0)
    @FieldBuilder.TextField(
      placeholder = "Name...",
      maxLength = MAX_LENGTH,
      pattern = PATTERN,
      required = true,
      requiredIndicatorVisible = true)
    @NotNull(message = "Name is required")
    @Pattern(regexp = PATTERN, message = "Alphabetic words only")
    @Size(max = MAX_LENGTH, message = "At most 12 characters")
    String getName();
}
