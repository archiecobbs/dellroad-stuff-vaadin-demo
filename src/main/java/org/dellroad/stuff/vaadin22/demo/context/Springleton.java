
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo.context;

import com.vaadin.flow.spring.annotation.EnableVaadin;

@EnableVaadin
public class Springleton extends org.dellroad.stuff.spring.Springleton {

    public static Springleton getInstance() {
        return (Springleton)org.dellroad.stuff.spring.Springleton.getInstance();
    }
}
