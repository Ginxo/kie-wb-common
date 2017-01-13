/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.shapes.client.view;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.safehtml.shared.SafeUri;
import org.kie.workbench.common.stunner.shapes.client.factory.PictureProvidersManager;
import org.kie.workbench.common.stunner.shapes.client.view.icon.dynamics.DynamicIconShapeView;
import org.kie.workbench.common.stunner.shapes.client.view.icon.statics.StaticIconShapeView;
import org.kie.workbench.common.stunner.shapes.def.icon.dynamics.Icons;

import static org.uberfire.commons.validation.PortablePreconditions.checkNotNull;

@ApplicationScoped
public class ShapeViewFactory {

    private final PictureProvidersManager pictureProvidersManager;

    protected ShapeViewFactory() {
        this(null);
    }

    @Inject
    public ShapeViewFactory(PictureProvidersManager pictureProvidersManager) {
        this.pictureProvidersManager = pictureProvidersManager;
    }

    public RectangleView rectangle(final double width,
                                   final double height,
                                   final double corner_radius) {
        return new RectangleView(width,
                                 height,
                                 corner_radius);
    }

    public DynamicIconShapeView dynamicIcon(final Icons icon,
                                            final double width,
                                            final double height) {
        return new DynamicIconShapeView(icon,
                                        width,
                                        height);
    }

    public StaticIconShapeView staticIcon(final org.kie.workbench.common.stunner.shapes.def.icon.statics.Icons icon) {
        return new StaticIconShapeView(icon);
    }

    public PictureShapeView picture(final Object source,
                                    final double width,
                                    final double height) {
        checkNotNull("source",
                     source);
        final SafeUri uri = pictureProvidersManager.getUri(source);
        return new PictureShapeView(uri.asString(),
                                    width,
                                    height);
    }

    public CircleView circle(final double radius) {
        return new CircleView(radius);
    }

    public RingView ring(final double outer) {
        return new RingView(outer);
    }

    public PolygonView polygon(final double radius,
                               final String fillColor) {
        return new PolygonView(radius,
                               fillColor);
    }

    public ConnectorView connector(final double... points) {
        return new ConnectorView(points);
    }
}
