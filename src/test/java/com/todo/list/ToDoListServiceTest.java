package com.todo.list;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.to.dolist.model.ToDoList;
import com.to.dolist.service.ToDoListServiceImpl;
import com.to.dolist.utilities.AppException;
import com.to.dolist.utilities.ErrorCodes;

public class ToDoListServiceTest {

	private static ToDoListServiceImpl service = mock(ToDoListServiceImpl.class);
	ToDoList listActual;
	ToDoList listExpected;

	@BeforeTest
	public void setup() {
		listActual = new ToDoList();
		listExpected = new ToDoList();
	}
	
	/*
	 *	Positive Test cases for ToDoList Service
	 */
	
	@Test(dataProvider="testCreateListPositive", priority = 1)
	public void testCreateListPositive(ToDoList listActual, ToDoList listExpected) {
		when(service.create(listActual)).thenReturn(listExpected);
		assertEquals(1, listExpected.getId());
		
	}
	@DataProvider(name= "testCreateListPositive")
	public Object[][] createToDO() {
		listActual.setTitle("Sample Title");
		listActual.setEstimation(12);
		listActual.setDuedate(Date.valueOf("2019-04-04"));
		listActual.setStartdate(Date.valueOf("2019-04-04"));
		listActual.setMessgae("sample msg");
		listActual.setStatus("started");
		listExpected.setId(1);
		listExpected.setTitle("Sample Title");
		listExpected.setEstimation(12);
		listExpected.setDuedate(Date.valueOf("2019-04-04"));
		listExpected.setStartdate(Date.valueOf("2019-04-04"));
		listExpected.setMessgae("sample msg");
		listExpected.setStatus("started");
		return new Object [][] {{listActual, listExpected}};
	}

	@Test(dataProvider = "testReadPositive", priority = 2)
	public void testReadListPositive(long id, ToDoList listExpected) {
		when(service.read(id)).thenReturn(listExpected);
		assertEquals(id, listExpected.getId());
	}
	@DataProvider(name = "testReadPositive")
	public Object[][] readToDo() {
		listExpected.setId(1);
		listExpected.setTitle("Sample Title");
		listExpected.setEstimation(12);
		listExpected.setDuedate(Date.valueOf("2019-04-04"));
		listExpected.setStartdate(Date.valueOf("2019-04-04"));
		listExpected.setMessgae("sample msg");
		listExpected.setStatus("started");
		return new Object [][] {{1, listExpected}};
	}

	@Test(dataProvider = "testReadAll", priority = 3)
	public void testReadAll(List<ToDoList> lists) {
		when(service.readAll()).thenReturn(lists);
		assertEquals(1, lists.size());
	}
	@DataProvider(name = "testReadAll")
	public Object[][] readAll() {
		List<ToDoList> lists = new ArrayList<>();
		listExpected.setId(1);
		listExpected.setTitle("Sample Title");
		listExpected.setEstimation(12);
		listExpected.setDuedate(Date.valueOf("2019-04-04"));
		listExpected.setStartdate(Date.valueOf("2019-04-04"));
		listExpected.setMessgae("sample msg");
		listExpected.setStatus("started");
		lists.add(listExpected);
		return new Object[][] {{lists}};
	}

	@Test(dataProvider = "testUpdatePositive", priority = 4)
	public void testUpdatePositive(ToDoList listActual, ToDoList listExpected) {
		when(service.update(listActual)).thenReturn(listExpected);
		assertEquals(listActual.getId(), listExpected.getId());
	}
	@DataProvider(name = "testUpdatePositive")
	public Object[][] updateToDo() {
		listActual.setId(1);
		listActual.setTitle("Sample Title");
		listActual.setEstimation(12);
		listActual.setDuedate(Date.valueOf("2019-04-04"));
		listActual.setStartdate(Date.valueOf("2019-04-04"));
		listActual.setMessgae("sample msg");
		listActual.setStatus("started");
		listExpected.setId(1);
		listExpected.setTitle("Sample Title");
		listExpected.setEstimation(12);
		listExpected.setDuedate(Date.valueOf("2019-04-04"));
		listExpected.setStartdate(Date.valueOf("2019-04-04"));
		listExpected.setMessgae("sample msg");
		listExpected.setStatus("started");
		return new Object [][] {{listActual, listExpected}};
	}

	@Test(dataProvider = "testDeletePositive", priority = 5)
	public void testDeletePositive(long id, boolean status) {
		when(service.delete(id)).thenReturn(status);
		assertEquals(true, status);
	}
	@DataProvider(name = "testDeletePositive") 
	public Object[][] deleteToDo() {
		return new Object[][] {{1, true}};
	}
	
	/*
	 *	Negative Test cases for ToDoList Service
	 */

	@Test(dataProvider = "testCreateNeg", expectedExceptions = AppException.class, priority =  6)
	public void testCreateNeg(ToDoList listActual) {
		when(service.create(any(ToDoList.class))).thenThrow(new AppException(ErrorCodes.TITLE_FILED_EMPTY));
		service.create(listActual);
	}
	@DataProvider(name = "testCreateNeg")
	public Object[][] createNeg() {
		listActual.setTitle(null);
		listActual.setEstimation(12);
		listActual.setDuedate(Date.valueOf("2019-04-04"));
		listActual.setStartdate(Date.valueOf("2019-04-04"));
		listActual.setMessgae("sample msg");
		listActual.setStatus("started");
		return new Object[][] {{listActual}};
	}

	@Test(dataProvider = "testUpdateNeg", expectedExceptions = AppException.class, priority =  7)
	public void testUpdateNeg(ToDoList listActual) {
		when(service.create(any(ToDoList.class))).thenThrow(new AppException(ErrorCodes.TITLE_FILED_EMPTY));
		service.create(listActual);
	}
	@DataProvider(name = "testUpdateNeg")
	public Object[][] updateNeg() {
		listActual.setId(1);
		listActual.setTitle(null);
		listActual.setEstimation(12);
		listActual.setDuedate(Date.valueOf("2019-04-04"));
		listActual.setStartdate(Date.valueOf("2019-04-04"));
		listActual.setMessgae("sample msg");
		listActual.setStatus("started");
		return new Object[][] {{listActual}};
	}

	@Test (expectedExceptions = AppException.class, priority = 8)
	public void readNegtive() {
		when(service.read(any(Long.class))).thenThrow(new AppException(ErrorCodes.ID_ERROR));
		service.read(0);
	}

	@Test (expectedExceptions = AppException.class, priority = 9)
	public void deleteNegtive() {
		when(service.delete(any(Long.class))).thenThrow(new AppException(ErrorCodes.ID_ERROR));
		service.delete(0);
	}
}
