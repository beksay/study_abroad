package org.infosystema.iselect.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@WebServlet(urlPatterns="/reindex")
public class ReIndexServlet extends HttpServlet {
//	@EJB 
//	private ReceiptGoodsHeaderService service;
	
	private static final long serialVersionUID = -5212725445670118808L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
		    String key = request.getParameter("key");
			
			if(key != null){
				System.out.println(key);
				//service.reindex(key);
			}
		} catch(Exception e){
			e.printStackTrace();
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
