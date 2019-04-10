package com.to.dolist.service;

import java.util.List;

import com.to.dolist.model.ToDoList;

public interface ToDoListService {

	public ToDoList create(ToDoList list);
	public ToDoList update(ToDoList list);
	public ToDoList read(long id);
	public List<ToDoList> readAll();
	public boolean delete(long id);
}
