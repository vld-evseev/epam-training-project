package com.epam.training.lawAndSocial.web.servlet.model;

import java.util.*;

public class FormValidation {

    private final Map<String, FieldValidation> fields = new HashMap<>();
    private final Map<String, Boolean> errors = new HashMap<>();

    public Map<String, FieldValidation> getFields() {
        return fields;
    }

    public Map<String, Boolean> getErrors() {
        return errors;
    }

    public boolean isValid() {
        return fields.isEmpty() && errors.isEmpty();
    }

    public List<String> messages() {
        final ArrayList<String> messages = new ArrayList<>();

        if (!fields.isEmpty()) {
            final Set<Map.Entry<String, FieldValidation>> fieldEntries = fields.entrySet();
            for (Map.Entry<String, FieldValidation> fieldEntry : fieldEntries) {
                messages.add(fieldEntry.getKey() + " : " + fieldEntry.getValue().toString());
            }
        }

        if (!errors.isEmpty()) {
            final Set<Map.Entry<String, Boolean>> errorsEntries = errors.entrySet();
            for (Map.Entry<String, Boolean> errorsEntry : errorsEntries) {
                messages.add(errorsEntry.getKey() + " : " + errorsEntry.getValue());
            }
        }

        return messages;
    }
}
