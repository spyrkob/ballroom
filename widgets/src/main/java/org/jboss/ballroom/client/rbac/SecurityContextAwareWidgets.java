/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.jboss.ballroom.client.rbac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Harald Pehl
 */
public final class SecurityContextAwareWidgets {

    private SecurityContextAwareWidgets() {}

    public static void reset(final Widget widget, final SecurityContext securityContext) {
        List<SecurityContextAware> bag = new ArrayList<SecurityContextAware>();
        collect(widget, bag);
        for (SecurityContextAware sca : bag) {
            sca.reset(securityContext);
        }
    }

    public static void update(final Widget widget, final SecurityContext securityContext) {
        List<SecurityContextAware> bag = new ArrayList<SecurityContextAware>();
        collect(widget, bag);
        for (SecurityContextAware sca : bag) {
            sca.update(securityContext);
        }
    }

    private static final void collect(final Widget widget, Collection<SecurityContextAware> bag) {
        if (widget instanceof SecurityContextAware) {
            bag.add((SecurityContextAware) widget);
        }
        if (widget instanceof HasWidgets) {
            HasWidgets widgets = (HasWidgets) widget;
            for (Widget nested : widgets) {
                collect(nested, bag);
            }
        }
    }
}
