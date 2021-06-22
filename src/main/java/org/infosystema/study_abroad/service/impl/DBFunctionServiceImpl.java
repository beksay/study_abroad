package org.infosystema.study_abroad.service.impl;

import javax.ejb.*;

import org.infosystema.study_abroad.service.DBFunctionService;

@Stateless
@Local(DBFunctionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DBFunctionServiceImpl extends FunctionServiceImpl implements DBFunctionService {

}