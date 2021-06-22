package org.infosystema.iselect.service.impl;

import javax.ejb.*;

import org.infosystema.iselect.service.DBFunctionService;

@Stateless
@Local(DBFunctionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DBFunctionServiceImpl extends FunctionServiceImpl implements DBFunctionService {

}