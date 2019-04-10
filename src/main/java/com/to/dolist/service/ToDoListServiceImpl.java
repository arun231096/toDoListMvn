package com.to.dolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.to.dolist.dao.ToDoListDAO;
import com.to.dolist.model.ToDoList;
import com.to.dolist.utilities.AppException;
import com.to.dolist.utilities.ErrorCodes;

@Repository
@Service
public class ToDoListServiceImpl implements ToDoListService{

	@Autowired
	ToDoListDAO toDao;

	@Override
	public ToDoList create(ToDoList list) {
		validate(list);
		return toDao.create(list);
	}

	@Override
	public ToDoList update(ToDoList list) {
		validate(list);
		if (list.getId()!= 0) {			
			return toDao.update(list);
		} else {
			throw new AppException(ErrorCodes.ID_ERROR);
		}
	}

	@Override
	public ToDoList read(long id) {
		return toDao.read(id);
	}

	@Override
	public List<ToDoList> readAll() {
		return toDao.readAll();
	}

	@Override
	public boolean delete(long id) {
		
		if (toDao.delete(id) != 0) return true; else return false;
	}

	private void validate(ToDoList list) {

		List<ErrorCodes> execption = new ArrayList<>();
		if ("".equals(list.getTitle().trim())) {
			execption.add(ErrorCodes.TITLE_FILED_EMPTY);
		}
		if ("".equals(list.getMessgae().trim())) {
			execption.add(ErrorCodes.MESSAGE_ERROR);
		}
		if ("".equals(list.getStatus().trim())) {
			execption.add(ErrorCodes.STATUS_ERROR);
		}
		if ("".equals(list.getStartdate().toString().trim())) {
			execption.add(ErrorCodes.START_DATE_ERROR);
		}
		if ("".equals(list.getDuedate().toString().trim())) {
			execption.add(ErrorCodes.DUE_DATE_ERROR);
		}
		if (list.getEstimation() == 0) {
			execption.add(ErrorCodes.ESTIMATION_ERROR);
		}
		if (!execption.isEmpty()) {
			throw new AppException(execption);
		}
	}
}
