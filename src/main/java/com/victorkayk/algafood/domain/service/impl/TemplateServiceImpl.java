package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.enums.ErrorEnum;
import com.victorkayk.algafood.domain.exception.ApiException;
import com.victorkayk.algafood.domain.model.Template;
import com.victorkayk.algafood.domain.repository.TemplateRepository;
import com.victorkayk.algafood.domain.service.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class TemplateServiceImpl implements TemplateService {
    public static final String CONFIGURATION_VERSION = "2.3.31";

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public String getProcessedTemplate(String templateId, Map<String, Object> templateVariables) {
        try {
            Template template = getTemplate(templateId);
            return processTemplate(templateVariables, template).toString();
        } catch (Exception e) {
            log.error("Error while processing template {}", templateId, e);
            throw new ApiException(ErrorEnum.TEMPLATE_PROCESSING_ERROR);
        }
    }

    public StringWriter processTemplate(Map<String, Object> templateVariables, Template emailTemplate) throws IOException, TemplateException {
        Configuration cfg = new Configuration(new Version(CONFIGURATION_VERSION));
        StringReader reader = new StringReader(emailTemplate.getBody());
        freemarker.template.Template template = new freemarker.template.Template(emailTemplate.getName(), reader, cfg);
        StringWriter writer = new StringWriter();

        template.process(templateVariables, writer);

        return writer;
    }

    public Template getTemplate(String templateName) {
        Optional<Template> template = templateRepository.findByName(templateName);
        if (template.isEmpty()) {
            log.error("Template {} not found", templateName);
            throw new ApiException(ErrorEnum.TEMPLATE_NOT_FOUND);
        }

        return template.get();
    }
}

