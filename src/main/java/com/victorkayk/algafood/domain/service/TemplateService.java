package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public interface TemplateService {
    Template getTemplate(String templateName);

    StringWriter processTemplate(Map<String, Object> templateVariables, Template emailTemplate) throws IOException, TemplateException;

    String getProcessedTemplate(String templateId, Map<String, Object> templateVariables);
}
