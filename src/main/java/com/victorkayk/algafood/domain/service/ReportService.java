package com.victorkayk.algafood.domain.service;

import com.victorkayk.algafood.domain.model.Template;
import net.sf.jasperreports.engine.JasperReport;

import java.util.Map;

public interface ReportService {
    byte[] generatePdfReport(Template template, Map<String, Object> parameters);

    JasperReport compileReport(Template template);
}
