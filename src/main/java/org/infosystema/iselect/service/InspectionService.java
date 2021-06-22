package org.infosystema.iselect.service;

import javax.ejb.Local;

import org.infosystema.iselect.model.app.Inspection;

@Local
public interface InspectionService extends GenericService<Inspection, Integer> {

}
