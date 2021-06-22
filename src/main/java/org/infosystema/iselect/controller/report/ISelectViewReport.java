package org.infosystema.iselect.controller.report;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JasperPrint;

@RequestScoped
@Named
public class ISelectViewReport extends BaseReportController1 {

	@Inject
	private JasperViewerController jasperViewerController;

	public void generate(Integer Id) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map.put("ids", Id);
		System.out.println("=====ids=====" + Id);
		JasperPrint jasperPrint = null;

		jasperPrint = generateJasperPrint(map, "iselect_report_1.jasper");

		System.out.println(jasperPrint);

		jasperViewerController.view(jasperPrint);
	}	 
	public void generateIns(Integer Id) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map.put("id", Id);
		System.out.println("=====ids=====" + Id);
		JasperPrint jasperPrint = null;

		jasperPrint = generateJasperPrint(map, "act_dosmotra.jasper");

		System.out.println(jasperPrint);

		jasperViewerController.view(jasperPrint);
	}	 
	 
	public void generateDesinfection(Integer Id) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map.put("ids", Id);
		System.out.println("=====ids=====" + Id);
		JasperPrint jasperPrint = null;
	
		jasperPrint = generateJasperPrint(map, "iselect_report_3.jasper");
	
		System.out.println(jasperPrint);
	
		jasperViewerController.view(jasperPrint);
	} 
	
	public void generateCertificate(Integer Id) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map.put("ids", Id);
		System.out.println("=====ids=====" + Id);
		JasperPrint jasperPrint = null;
	
		jasperPrint = generateJasperPrint(map, "iselect_report_print.jasper");
	
		System.out.println(jasperPrint);
	
		jasperViewerController.view(jasperPrint);
	} 
	 
	public void generateExpertise(Integer Id) {
		System.out.println("generate");
		Map<String, Object> map = new HashMap<>();
		map.put("ids", Id);
		System.out.println("=====ids====444=" + Id);
		JasperPrint jasperPrint = null;
	
		jasperPrint = generateJasperPrint(map, "iselect_report_4.jasper");
	
		System.out.println(jasperPrint);
	
		jasperViewerController.view(jasperPrint);
	}	 
}
