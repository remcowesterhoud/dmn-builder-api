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

import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_ELEMENT_EXTENSION;
import static org.camunda.community.dmn.builder.api.impl.DmndiModelConstants.DI_NS;

import org.camunda.bpm.model.dmn.impl.instance.DmnModelElementInstanceImpl;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;
import org.camunda.community.dmn.builder.api.model.di.Extension;

/**
 * The DI extension element of the DI DiagramElement type
 *
 * @author Sebastian Menski
 */
public class ExtensionImpl extends DmnModelElementInstanceImpl implements Extension {

  public ExtensionImpl(final ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public static void registerType(final ModelBuilder modelBuilder) {
    final ModelElementTypeBuilder typeBuilder =
        modelBuilder
            .defineType(Extension.class, DI_ELEMENT_EXTENSION)
            .namespaceUri(DI_NS)
            .instanceProvider(
                new ModelTypeInstanceProvider<Extension>() {
                  @Override
                  public Extension newInstance(final ModelTypeInstanceContext instanceContext) {
                    return new ExtensionImpl(instanceContext);
                  }
                });

    typeBuilder.build();
  }
}
