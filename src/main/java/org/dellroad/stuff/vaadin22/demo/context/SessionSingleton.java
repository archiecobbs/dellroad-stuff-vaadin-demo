
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.context;

import com.google.common.base.Preconditions;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.scopes.VaadinSessionScope;

import java.util.HashMap;

import org.dellroad.stuff.vaadin22.field.FieldBuilder;
import org.dellroad.stuff.vaadin22.util.VaadinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

/**
 * An example of a Vaadin session scoped singleton.
 */
@Component
@Scope(VaadinSessionScope.VAADIN_SESSION_SCOPE_NAME)
public class SessionSingleton {

    private final VaadinSession session = VaadinUtil.getCurrentSession();
    private final HashMap<Class<?>, FieldBuilder<?>> fieldBuilderMap = new HashMap<>();

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     * Get the {@link VaadinSession} with which this instance is associated.
     */
    public VaadinSession getVaadinSession() {
        return this.session;
    }

    /**
     * Get the application-wide {@link AsyncTaskExecutor}.
     */
    public AsyncTaskExecutor getTaskExecutor() {
        return this.taskExecutor;
    }

    /**
     * Get the application-wide {@link TaskScheduler}.
     */
    public TaskScheduler getTaskScheduler() {
        return this.taskScheduler;
    }

    /**
     * Create a new {@link FieldBuilder}, duplicating a cached instance if available.
     *
     * @param modelType model type for fields
     * @throws IllegalArgumentException if {@code modelType} is null
     */
    @SuppressWarnings("unchecked")
    public <T> FieldBuilder<T> newFieldBuilder(Class<T> modelType) {
        Preconditions.checkArgument(modelType != null, "null modelType");
        VaadinUtil.assertCurrentSession(this.session);
        final FieldBuilder<T> template = (FieldBuilder<T>)this.fieldBuilderMap.computeIfAbsent(modelType, FieldBuilder::new);
        return new FieldBuilder<>(template);
    }
}
