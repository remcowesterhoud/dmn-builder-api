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

import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_ATTRIBUTE_ID;
import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_ELEMENT_STYLE;
import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_NS;

import org.camunda.bpm.model.dmn.impl.instance.DmnModelElementInstanceImpl;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.attribute.Attribute;
import org.camunda.community.dmn.builder.api.model.di.Style;

/**
 * The DI Style element
 *
 * @author Sebastian Menski
 */
public abstract class StyleImpl extends DmnModelElementInstanceImpl implements Style {

  protected static Attribute<String> idAttribute;

  public StyleImpl(final ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public static void registerType(final ModelBuilder modelBuilder) {
    final ModelElementTypeBuilder typeBuilder =
        modelBuilder.defineType(Style.class, DI_ELEMENT_STYLE).namespaceUri(DI_NS).abstractType();

    idAttribute = typeBuilder.stringAttribute(DI_ATTRIBUTE_ID).idAttribute().build();

    typeBuilder.build();
  }

  @Override
  public String getId() {
    return idAttribute.getValue(this);
  }

  @Override
  public void setId(final String id) {
    idAttribute.setValue(this, id);
  }
}
