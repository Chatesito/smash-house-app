package com.house.smash.smash_house.controller;

import com.house.smash.smash_house.service.PlayerRankingService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PlayerRankingController {

    private final PlayerRankingService playerRankingService;
    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;

    @GetMapping("/ranking")
    public String showRanking(Model model) {
        model.addAttribute("players", playerRankingService.getAllRankings());
        return "ranking";
    }

    @GetMapping("/player/{id}")
    public String showPlayerDetails(@PathVariable Long id, Model model) {
        model.addAttribute("player", playerRankingService.getPlayerById(id));
        return "player-details";
    }

    @GetMapping("/ranking/report")
    public void generateRankingReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"ranking_report.pdf\"");

        JasperReport report = JasperCompileManager
                .compileReport(resourceLoader.getResource("classpath:reports/ranking_report.jrxml").getInputStream());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("REPORT_TITLE", "Reporte del Ranking");
        parameters.put("FECHA_GENERACION", new Date());

        try(Connection connection = dataSource.getConnection()){
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);

            OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new Exception("Error al generar el reporte: " + e.getMessage());
        }
    }
}
