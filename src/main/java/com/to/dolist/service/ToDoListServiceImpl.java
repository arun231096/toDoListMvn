package com.to.dolist.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.to.dolist.dao.ToDoListDAO;
import com.to.dolist.model.ToDoList;
import com.to.dolist.utilities.AppException;
import com.to.dolist.utilities.ErrorCodes;

@Repository
public class ToDoListServiceImpl implements ToDoListService{

	@Autowired
	ToDoListDAO toDao;
	private List<ErrorCodes> execption = new ArrayList<>();
	private static List<ToDoList> searchResult = new ArrayList<>();

	@Override
	public ToDoList create(ToDoList list) {
		validate(list);
		findDuplicate(list);
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
		searchResult.removeIf(td -> td.getId() == id);
		if (toDao.delete(id) != 0) return true; else return false;
	}

	private void validate(ToDoList list) {

		Date cuurentDate = Date.from(Instant.now());
		if ("".equals(list.getTitle().trim())) {
			execption.add(ErrorCodes.TITLE_FILED_EMPTY);
		}
		if ("".equals(list.getMessgae().trim())) {
			execption.add(ErrorCodes.MESSAGE_ERROR);
		}
		if ("".equals(list.getStatus().trim())) {
			execption.add(ErrorCodes.STATUS_ERROR);
		}
		if (cuurentDate.compareTo(new Date(list.getStartdate().getTime())) > 1) {
			execption.add(ErrorCodes.START_DATE_ERROR);
		}
		if (cuurentDate.compareTo(new Date(list.getDuedate().getTime())) > 1) {
			execption.add(ErrorCodes.DUE_DATE_ERROR);
		} 
		if (!execption.isEmpty()) {
			throw new AppException(execption);
		}
	}
	
	private void findDuplicate(ToDoList list) {
		if (toDao.findDuplicate(list.getTitle(), list.getMessgae())) {
			execption.add(ErrorCodes.DUPLICATE_ENTRY);
		}
		if (!execption.isEmpty()) {
			throw new AppException(execption);
		}
	}

	@Override
	public List<ToDoList> search(String title) {
		if (!searchResult.isEmpty()) {
			List<ToDoList> result = searchResult.stream().filter(tdl -> tdl.getTitle().indexOf(title) > 0 )
														 .collect(Collectors.toList());
			if (result.isEmpty()) {
				result = toDao.search(title);
				if (result.isEmpty()) {
					throw new AppException(ErrorCodes.SEARCH_NOT_FOUND); 
				}
				searchResult.addAll(result);
			}
			return result;
		}
		searchResult =  toDao.search(title);
		if (searchResult.isEmpty()) {
			throw new AppException(ErrorCodes.SEARCH_NOT_FOUND); 
		}
		return searchResult;
	}
}
