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

package org.camunda.community.dmn.builder.api.model.di;

import org.camunda.bpm.model.dmn.instance.DmnModelElementInstance;

/**
 * The DI DiagramElement element
 *
 * @author Sebastian Menski
 */
public interface DiagramElement extends DmnModelElementInstance {

  String getId();

  void setId(String id);

  Extension getExtension();

  void setExtension(Extension extension);
}
