
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import com.google.common.base.Preconditions;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dellroad.stuff.vaadin22.demo.context.SessionSingleton;
import org.dellroad.stuff.vaadin22.demo.model.Person;
import org.dellroad.stuff.vaadin22.field.FieldBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
@CssImport("./styles/demo.css")
@PreserveOnRefresh
@Route("")
@SuppressWarnings("serial")
@Uses(com.vaadin.flow.component.checkbox.Checkbox.class)                // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.checkbox.CheckboxGroup.class)           // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.combobox.ComboBox.class)                // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.customfield.CustomField.class)          // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.datepicker.DatePicker.class)            // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.datetimepicker.DateTimePicker.class)    // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.html.Input.class)                       // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.listbox.ListBox.class)                  // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.listbox.MultiSelectListBox.class)       // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.radiobutton.RadioButtonGroup.class)     // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.select.Select.class)                    // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.BigDecimalField.class)        // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.EmailField.class)             // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.IntegerField.class)           // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.NumberField.class)            // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.PasswordField.class)          // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.TextArea.class)               // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.textfield.TextField.class)              // https://github.com/vaadin/flow-components/issues/2902
@Uses(com.vaadin.flow.component.timepicker.TimePicker.class)            // https://github.com/vaadin/flow-components/issues/2902
public class DemoView extends VerticalLayout {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected SessionSingleton sessionSingleton;

    private BeanValidationBinder<Person> binder;
    private Notification errorNotification;

    public DemoView() {

        // Add title
        this.add(new Text("DellRoad Stuff Demo"));

        // Setup binder
        final FieldBuilder<Person> fieldBuilder = this.sessionSingleton.newFieldBuilder(Person.class);
        this.binder = new BeanValidationBinder<>(Person.class);
        fieldBuilder.bindFields(this.binder);

        // Build form
        final FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("1px", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));

        fieldBuilder.addFieldComponents(formLayout);
        this.add(formLayout);

        // Add buttons
        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(new Button("Reset", e -> this.resetForm()));
        buttonLayout.add(new Button("Submit", e -> this.submitForm()));
        this.add(buttonLayout);
    }

    private void resetForm() {
        this.log.info("DemoView: form reset");
        this.resetErrorNotification();
        this.binder.readBean(new Person());
    }

    private void submitForm() {
        this.log.info("DemoView: form submitted");
        this.resetErrorNotification();
        final Person person = new Person();
        try {
            this.binder.writeBean(person);
        } catch (ValidationException e) {
            this.log.error("DemoView: validation errors:\n    {}",
              Stream.of(this.toStrings(e)).collect(Collectors.joining("\n    ")));
            this.errorNotification = this.notify(NotificationVariant.LUMO_ERROR,
              Notification.Position.MIDDLE, 0, "Validation Error", this.toStrings(e));
            return;
        }
        this.notify(NotificationVariant.LUMO_SUCCESS, Notification.Position.BOTTOM_END, 3000, "Successful Submission");
        this.log.info("DemoView: validation successful; result: {}", person);
    }

    private void resetErrorNotification() {
        if (this.errorNotification != null) {
            this.errorNotification.close();
            this.errorNotification = null;
        }
    }

    private String[] toStrings(ValidationException e) {
        return e.getValidationErrors().stream()
          .map(ValidationResult::getErrorMessage)
          .toArray(String[]::new);
    }

    /**
     * Show a notification.
     *
     * @param variant type of message
     * @param position position on screen
     * @param duration how long to stay on screen, or zero for infinity
     * @param message main message
     * @param details extra message(s), or null
     * @throws IllegalArgumentException if any parameter other than {@code details} is null
     */
    private Notification notify(NotificationVariant variant,
      Notification.Position position, int duration, String message, String... details) {

        // Sanity check
        Preconditions.checkArgument(variant != null, "null variant");
        Preconditions.checkArgument(position != null, "null position");
        Preconditions.checkArgument(message != null, "null message");

        // Build notification
        final Notification notification = new Notification();
        notification.addThemeVariants(variant);
        notification.setPosition(position);
        notification.setDuration(duration);

        // Add top row
        final HorizontalLayout topRow = new HorizontalLayout();
        topRow.setAlignItems(FlexComponent.Alignment.CENTER);
        topRow.add(new Div(new Text(message)));
        final Button closeButton = new Button(new Icon("lumo", "cross"), e -> notification.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute(ElementConstants.ARIA_LABEL_PROPERTY_NAME, "Close");
        topRow.add(closeButton);
        notification.add(topRow);

        // Add subsequent rows, if any
        if (details != null) {
            final List<Div> extraDivs = Stream.of(details)
              .filter(Objects::nonNull)
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(Text::new)
              .map(Div::new)
              .collect(Collectors.toList());
            if (!extraDivs.isEmpty()) {
                final VerticalLayout extraLayout = new VerticalLayout();
                extraLayout.setAlignItems(FlexComponent.Alignment.START);
                extraDivs.forEach(extraLayout::add);
                notification.add(extraLayout);
            }
        }

        // Display notification
        notification.open();
        return notification;
    }
}
