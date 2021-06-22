package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.app.Inspection;

@Local
public interface InspectionService extends GenericService<Inspection, Integer> {

}
