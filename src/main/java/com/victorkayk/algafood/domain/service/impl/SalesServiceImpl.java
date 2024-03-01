package com.victorkayk.algafood.domain.service.impl;

import com.victorkayk.algafood.domain.dto.SalesDTO;
import com.victorkayk.algafood.domain.dto.SalesFilterDTO;
import com.victorkayk.algafood.domain.model.Template;
import com.victorkayk.algafood.domain.repository.SalesRepository;
import com.victorkayk.algafood.domain.service.ReportService;
import com.victorkayk.algafood.domain.service.SalesService;
import com.victorkayk.algafood.domain.service.TemplateService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService {
    public static final String SALES_REPORT = "sales.report";

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ReportService reportService;

    @Override
    public List<SalesDTO> listSales(SalesFilterDTO dto) {
        return salesRepository.list(dto);
    }

    @Override
    public byte[] exportSales(SalesFilterDTO dto, HttpServletResponse response) {
        List<SalesDTO> sales = listSales(dto);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sales", sales);

        Template assocaiteTemplate = templateService.getTemplate(SALES_REPORT);
        byte[] report = reportService.generatePdfReport(assocaiteTemplate, parameters);

        String fileName = "sales-report.pdf";
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=" + fileName
        );

        return report;
    }
}
