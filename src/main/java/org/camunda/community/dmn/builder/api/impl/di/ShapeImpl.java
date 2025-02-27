/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.community.dmn.builder.api.impl.di;

import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_ELEMENT_SHAPE;
import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_NS;

import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.child.ChildElement;
import org.camunda.bpm.model.xml.type.child.SequenceBuilder;
import org.camunda.community.dmn.builder.api.model.dc.Bounds;
import org.camunda.community.dmn.builder.api.model.di.Node;
import org.camunda.community.dmn.builder.api.model.di.Shape;

/**
 * The DI Shape element
 *
 * @author Sebastian Menski
 */
public abstract class ShapeImpl extends NodeImpl implements Shape {

  protected static ChildElement<Bounds> boundsChild;

  public ShapeImpl(final ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public static void registerType(final ModelBuilder modelBuilder) {
    final ModelElementTypeBuilder typeBuilder =
        modelBuilder
            .defineType(Shape.class, DI_ELEMENT_SHAPE)
            .namespaceUri(DI_NS)
            .extendsType(Node.class)
            .abstractType();

    final SequenceBuilder sequenceBuilder = typeBuilder.sequence();

    boundsChild = sequenceBuilder.element(Bounds.class).required().build();

    typeBuilder.build();
  }

  @Override
  public Bounds getBounds() {
    return boundsChild.getChild(this);
  }

  @Override
  public void setBounds(final Bounds bounds) {
    boundsChild.setChild(this, bounds);
  }
}
