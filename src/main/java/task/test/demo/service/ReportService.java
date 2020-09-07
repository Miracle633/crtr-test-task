package task.test.demo.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import task.test.demo.model.dto.ReportDTO;
import task.test.demo.repository.CategoryRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final CategoryRepository categoryRepository;

    public ReportService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void exportReport(HttpServletResponse response) throws IOException, JRException {
        List<ReportDTO> reportData = convertToDTO();
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        response.setContentType("application/pdf");
        response.setHeader("X-Frame-Options", "Goforit");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
        response.flushBuffer();
    }

    private List<ReportDTO> convertToDTO() {
        List<ReportDTO> rowList = new ArrayList<>();
        List<Object[]> dataList = categoryRepository.countTotalUsersOfCategory();
        for (Object[] ob : dataList){
            String name = (String)ob[0];
            Long count = (Long)ob[1];
            ReportDTO row = new ReportDTO(name, count);
            rowList.add(row);
        }
        return rowList;
    }
}
