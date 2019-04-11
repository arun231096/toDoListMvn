package com.to.dolist.controller;

import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.to.dolist.model.ToDoList;
import com.to.dolist.service.ToDoListServiceImpl;
import com.to.dolist.utilities.AppException;

@Controller
public class ToDoListController {

	@Autowired
	ToDoListServiceImpl service;
	ToDoList list = getObj();
	Logger logger = Logger.getLogger(ToDoListController.class);
	
	@RequestMapping(value="/todo/{var}", method =  RequestMethod.GET)
	public String redirect(@PathVariable String var) {
		return var;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(Locale locale,Model view,
			@RequestParam String title,
			@RequestParam String message,
			@RequestParam int estimation,
			@RequestParam String status,
			@RequestParam Date startdate,
			@RequestParam Date duedate
			) {
		list.setDuedate(duedate);
		list.setEstimation(estimation);
		list.setMessgae(message);
		list.setStartdate(startdate);
		list.setStatus(status);
		list.setTitle(title);
		ModelAndView model;
		try {
			list = service.create(list);
			model = new ModelAndView("redirect:/");
			model.setStatus(HttpStatus.OK);
			return model;
		} catch (Exception e) {
			model = new ModelAndView("create");
			model.setStatus(HttpStatus.BAD_REQUEST);
			if (e instanceof AppException) {
				logger.error(((AppException) e).getErrorCodes());
			} else { logger.error(e.getMessage());}
			view.addAttribute("error", "OOPS ERROR WHILE CREATING TODO FOR YOU PLEASE ENTER ALL FILEDS");
			return model;
		}
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update (Locale locale,Model view,
			@RequestParam long id,
			@RequestParam String title,
			@RequestParam String message,
			@RequestParam int estimation,
			@RequestParam String status,
			@RequestParam Date startdate,
			@RequestParam Date duedate
	) {
		list.setId(id);
		list.setDuedate(duedate);
		list.setEstimation(estimation);
		list.setMessgae(message);
		list.setStartdate(startdate);
		list.setStatus(status);
		list.setTitle(title);
		ModelAndView model;
		try {
			list = service.update(list);
			model = new ModelAndView("redirect:/");
			return model;
		} catch (Exception e) {
			if (e instanceof AppException) {
				logger.error(((AppException) e).getErrorCodes());
			} else { logger.error(e.getMessage()); }
			model = new ModelAndView("redirect:/read");
			model.addObject("error", "OOPS ERROR WHILE UPDATING TODO FOR YOU");
			model.setStatus(HttpStatus.BAD_REQUEST);
			view.addAttribute("id", list.getId());
			return model;
		}
	}
	
	@RequestMapping(value="/read", method =  RequestMethod.GET)
	public String read(Locale locale, Model model, @RequestParam long id) {
		try {
			list = service.read(id);
			model.addAttribute("id", list.getId());
			model.addAttribute("title", list.getTitle());
			model.addAttribute("estimation", list.getEstimation());
			model.addAttribute("startdate", list.getStartdate());
			model.addAttribute("duedate", list.getDuedate());
			model.addAttribute("message", list.getMessgae());
			model.addAttribute("status", list.getStatus());
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("error", "OOPS DATA NOT FOUND");
		}
		return "edit";
	}
	
	@RequestMapping(value="/", method =  RequestMethod.GET)
	public ModelAndView readAll(Model view){
		ModelAndView model = new ModelAndView("home");
		try {
			List<ToDoList> list = service.readAll();
			model.addObject("lists", list);
			return model;
		} catch (Exception e) {
			logger.error(e.getMessage());
			view.addAttribute("error", "OOPS DATA NOT FOUND");
			model.setStatus(HttpStatus.BAD_REQUEST);
			return model;
		}
		
	}
	
	@RequestMapping(value="/delete", method =  RequestMethod.GET)
	public ModelAndView delete(Locale locale,Model view, @RequestParam long id) {
		
		ModelAndView model = new ModelAndView("redirect:/");
		try {
			if (service.delete(id)) {
				return model;		
			} else {
				model.addObject("error", "OOPS DATA NOT FOUND");
				model.setStatus(HttpStatus.BAD_REQUEST);
				return model;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addObject("error", "INTERNAL SERVER ERROR");
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return model;
		}
	}

	@Bean
	public ToDoList getObj() {
		return new ToDoList();
	}
}