
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.field;

import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

import org.dellroad.stuff.vaadin22.demo.context.SessionSingleton;
import org.dellroad.stuff.vaadin22.field.FieldBuilder;
import org.dellroad.stuff.vaadin22.field.FieldBuilderCustomField;
import org.dellroad.stuff.vaadin22.util.VaadinUtil;
import org.dellroad.stuff.vaadin22.util.WholeBeanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@SuppressWarnings("serial")
public class DemoCustomField<T> extends FieldBuilderCustomField<T> {

    private final WholeBeanValidator beanValidator;

    @Autowired
    private SessionSingleton sessionSingleton;

    public DemoCustomField(Class<T> modelType) {
        super(modelType);
        this.beanValidator = new WholeBeanValidator(this.modelType);
    }

    // Defer initialization to avoid NPE in createFieldBuilder()
    @Override
    protected void initialize() {
        VaadinUtil.accessSession(() -> super.initialize());
    }

    // Use our per-VaadinSession FieldBuilder cache for efficiency
    @Override
    protected FieldBuilder<T> createFieldBuilder() {
        return this.sessionSingleton.newFieldBuilder(this.modelType);
    }

    // Enable JSR 303 bean validation for PROPERTIES of the bean in the INNER binder
    @Override
    protected BeanValidationBinder<T> createBinder() {
        return new BeanValidationBinder<>(this.modelType);
    }

// ValidatingField

    // Enable JSR 303 bean validation for THE BEAN ITSELF in the OUTER binder
    @Override
    public ValidationResult validate(T bean, ValueContext context) {
        return bean != null ? this.beanValidator.apply(bean, context) : ValidationResult.ok();
    }
}
