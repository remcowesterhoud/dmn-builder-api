package org.camunda.community.dmn.builder.api;

import org.camunda.bpm.model.dmn.Dmn;
import org.junit.jupiter.api.Test;

class DmnBuilderTest {

  @Test
  void createAndPrintProcess() {
    final var drg = DmnBuilder.createDrg("drgId").done();
    System.out.println(Dmn.convertToString(drg));
  }
}
