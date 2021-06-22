package org.infosystema.study_abroad.controller.report;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.enums.ScopeConstants;
import org.infosystema.study_abroad.util.web.FacesScopeQualifier;
import org.infosystema.study_abroad.util.web.ScopeQualifier;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * 
 *
 */

@RequestScoped
@Logged
public class JasperViewerController extends BaseReportController1 {

	public static final String REPORT_NAME = "org.infosystema.study_abroad.jasperprint";
	
	public void view(JasperPrint print) {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(REPORT_NAME, print, ScopeConstants.SESSION_SCOPE);
		
		Map<String,Object> options = new HashMap<>();
    	options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 1024); 
        options.put("contentHeight", 600);

        PrimeFaces.current().dialog().openDynamic("/view/jasper_viewer.xhtml", options, null);
    	System.out.println(PrimeFaces.current() + " 88888888888888888888888888888");
	}
	
	@Override
	public StreamedContent getStream(JasperPrint jasperPrint) {
		return super.getStream(jasperPrint);
	}
	
	public JasperPrint getCurrentJasperPrint() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		return qualifier.getValue(REPORT_NAME, ScopeConstants.SESSION_SCOPE);
	}
	
	public String getContextPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		return request.getContextPath();
	}
	
}
