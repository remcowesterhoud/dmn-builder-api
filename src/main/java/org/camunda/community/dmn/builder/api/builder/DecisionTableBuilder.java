package org.camunda.community.dmn.builder.api.builder;

import org.camunda.bpm.model.dmn.DmnModelException;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.instance.DecisionTable;
import org.camunda.bpm.model.dmn.instance.Input;
import org.camunda.bpm.model.dmn.instance.InputEntry;
import org.camunda.bpm.model.dmn.instance.InputExpression;
import org.camunda.bpm.model.dmn.instance.Output;
import org.camunda.bpm.model.dmn.instance.OutputEntry;
import org.camunda.bpm.model.dmn.instance.Rule;
import org.camunda.bpm.model.dmn.instance.Text;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.community.dmn.builder.api.model.VariableType;

public class DecisionTableBuilder {

  private final DecisionTable decisionTable;
  private final DmnModelInstance modelInstance;
  private int inputCount = 0;
  private int outputCount = 0;
  private boolean ruleAdded = false;

  public DecisionTableBuilder(
      final DecisionTable decisionTable, final DmnModelInstance modelInstance) {
    this.decisionTable = decisionTable;
    this.modelInstance = modelInstance;
  }

  public DecisionTableBuilder input(final String expression, final VariableType variableType) {
    if (ruleAdded) {
      throw new DmnModelException("Can not create input after a rule has been created");
    }

    final var input = modelInstance.newInstance(Input.class);
    final var inputExpression = modelInstance.newInstance(InputExpression.class);
    final var text = modelInstance.newInstance(Text.class);
    text.setTextContent(expression);
    inputExpression.setText(text);
    inputExpression.setTypeRef(variableType.getTypeRef());
    input.setInputExpression(inputExpression);
    decisionTable.addChildElement(input);
    inputCount++;
    return this;
  }

  public DecisionTableBuilder output(final String name, final VariableType variableType) {
    if (ruleAdded) {
      throw new DmnModelException("Can not create output after a rule has been created");
    }

    final var output = modelInstance.newInstance(Output.class);
    output.setName(name);
    output.setTypeRef(variableType.getTypeRef());
    decisionTable.addChildElement(output);
    outputCount++;
    return this;
  }

  public DecisionTableBuilder rule(final String... inputsAndOutputs) {
    if (inputCount == 0 || outputCount == 0) {
      throw new DmnModelException("Can not create rule before in- and outputs have been created");
    }
    if (inputsAndOutputs.length != inputCount + outputCount) {
      throw new DmnModelException(
          "Expected to receive %d in-/outputs, but received %d"
              .formatted(inputCount + outputCount, inputsAndOutputs.length));
    }

    final var rule = modelInstance.newInstance(Rule.class);
    int inputsCreated = 0;
    int outputsCreated = 0;
    for (final String inputOrOutput : inputsAndOutputs) {
      if (inputsCreated != inputCount) {
        rule.addChildElement(createRuleEntry(InputEntry.class, inputOrOutput));
        inputsCreated++;
      } else if (outputsCreated != outputCount) {
        rule.addChildElement(createRuleEntry(OutputEntry.class, inputOrOutput));
        outputsCreated++;
      }
    }
    decisionTable.addChildElement(rule);
    ruleAdded = true;
    return this;
  }

  private <T extends ModelElementInstance> T createRuleEntry(
      final Class<T> entryClass, final String textContent) {
    final var entry = modelInstance.newInstance(entryClass);
    final var text = modelInstance.newInstance(Text.class);
    text.setTextContent(textContent);
    entry.addChildElement(text);
    return entry;
  }
}
