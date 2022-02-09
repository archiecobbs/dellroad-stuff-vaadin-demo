
/*
 * Copyright (C) 2022 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.stuff.vaadin22.demo;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Meta(name = "description", content = "Demonstration of DellRoad Stuff Vaadin classes")
@PageTitle("DellRoad Stuff Demo")
@Push
@SuppressWarnings("serial")
@Theme(themeClass = Lumo.class)
public class DemoAppShellConfigurator implements AppShellConfigurator {
}
