package com.to.dolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.to.dolist.mapper.ToDoListMapper;
import com.to.dolist.model.ToDoList;
import com.to.dolist.utilities.Statements;

@Repository
@Transactional
public class ToDoListDAO extends JdbcDaoSupport {

	@Autowired
	public ToDoListDAO (DataSource source) {
		this.setDataSource(source);
	}
	@Autowired
	ToDoListMapper mapper;
	
	public ToDoList create(ToDoList list) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement statement = arg0.prepareStatement(Statements.INSERT, java.sql.Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, list.getTitle());
				statement.setInt(2, list.getEstimation());
				statement.setDate(3, list.getStartdate());
				statement.setDate(4, list.getDuedate());
				statement.setString(5, list.getMessgae());
				statement.setString(6, list.getStatus());
				return statement;
				
			}
		}, holder);
		list.setId(holder.getKey().longValue());
		return list;	
	}
	
	public ToDoList update(ToDoList list) {
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement statement = arg0.prepareStatement(Statements.UPDATE);
				statement.setString(1, list.getTitle());
				statement.setInt(2, list.getEstimation());
				statement.setDate(3, list.getStartdate());
				statement.setDate(4, list.getDuedate());
				statement.setString(5, list.getMessgae());
				statement.setString(6, list.getStatus());
				statement.setLong(7, list.getId());
				return statement;
			}
		});
		return list;
	}
	
	public ToDoList read(long id) {
		Object[] parms = new Object[] {id};
		ToDoList list = this.getJdbcTemplate().queryForObject(Statements.READ,parms, mapper);
		return list;
	}
	
	public List<ToDoList> readAll() {
	
		return this.getJdbcTemplate().query(Statements.READ_ALL, mapper);
	}
	
	public int delete(long id) {
		Object[] parms = new Object[] {id};
		return this.getJdbcTemplate().update(Statements.DELETE,parms);
	}
	
	@Bean
	public ToDoListMapper getObj() {
		return new ToDoListMapper();
	}
}
