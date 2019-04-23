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
import com.to.dolist.utilities.ErrorCodes;

@Controller
public class ToDoListController {

	@Autowired
	ToDoListServiceImpl service;
	ToDoList list = getObj();
	Logger logger = Logger.getLogger(ToDoListController.class);
	
	@RequestMapping(value="/todo/{var}", method =  RequestMethod.GET)
	public ModelAndView redirect(@PathVariable String var) {
		ModelAndView model = new ModelAndView(var);
		return model;
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {
		return "home";
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
		list = service.create(list);
		model = new ModelAndView("redirect:/readAll");
		model.setStatus(HttpStatus.OK);
		return model;
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
		list = service.update(list);
		model = new ModelAndView("redirect:/");
		return model;
	}
	
	@RequestMapping(value="/read", method =  RequestMethod.GET)
	public String read(Locale locale, Model model, @RequestParam long id) {
		list = service.read(id);
		model.addAttribute("id", list.getId());
		model.addAttribute("title", list.getTitle());
		model.addAttribute("estimation", list.getEstimation());
		model.addAttribute("startdate", list.getStartdate());
		model.addAttribute("duedate", list.getDuedate());
		model.addAttribute("message", list.getMessgae());
		model.addAttribute("status", list.getStatus());
		return "edit";
	}
	
	@RequestMapping(value="/readAll", method =  RequestMethod.GET)
	public ModelAndView readAll(Model view){
		ModelAndView model = new ModelAndView("list");
		List<ToDoList> list = service.readAll();
		model.addObject("lists", list);
		return model;
	}
	
	@RequestMapping(value="/delete", method =  RequestMethod.GET)
	public ModelAndView delete(Locale locale,Model view, @RequestParam long id) {
		
		ModelAndView model = new ModelAndView("redirect:/");
		if (service.delete(id)) {
			return model;		
		} else {
			throw new AppException(ErrorCodes.NOT_FOUND);
		}
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String title) {
		ModelAndView model = new ModelAndView("searchlist");
		List<ToDoList> list = service.search(title);
		model.addObject("lists", list);
		return model;
	}
	
	@Bean
	public ToDoList getObj() {
		return new ToDoList();
	}
}