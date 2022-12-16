package org.camunda.community.dmn.builder.api;

import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.community.dmn.builder.api.model.VariableType;
import org.junit.jupiter.api.Test;

class DmnBuilderTest {

  @Test
  void createAndPrintProcess() {
    final var drg =
        DmnBuilder.createDrg("drgId")
            .decision(
                "decision1",
                d ->
                    d.literalExpression("calendar.getSeason(date)")
                        .variable("season", VariableType.STRING))
            .decision("decision2", d -> {})
            .decision("decision3", d -> {})
            .decision("decision4", d -> {})
            .decision("decision5", d -> {})
            .done();
    System.out.println(Dmn.convertToString(drg));
  }
}
