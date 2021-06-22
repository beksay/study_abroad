package org.infosystema.iselect.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.enums.ScopeConstants;
import org.infosystema.iselect.util.web.FacesScopeQualifier;
import org.infosystema.iselect.util.web.ScopeQualifier;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * 
 *
 */

@Named
@RequestScoped
@Logged
public class JasperViewerController extends BaseReportController {

	public static final String REPORT_NAME = "org.infosystema.iselect.jasperprint";
	
	public void view(JasperPrint print) {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(REPORT_NAME, print, ScopeConstants.SESSION_SCOPE);
		
		Map<String,Object> options = new HashMap<String, Object>();
    	options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("contentWidth", 1024);
        options.put("contentHeight", 500);

        PrimeFaces.current().dialog().openDynamic("/view/jasper_viewer.xhtml", options, null);
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

