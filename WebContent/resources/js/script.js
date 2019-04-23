(function ToDoList (url) {
	this.url = url;
	this.id;
	this.title;
	this.message;
	this.estimation;
	this.status;
	this.startdate;
	this.duedate;

	this.initalData = function() {
		document.getElementById("title_search").value = "";
		DoServ(url + "readAll", "GET");
	}

	this.create = function() {
		DoServ(url + "todo/create", "GET");
	}

	this.search = function() {
		var title  = document.getElementById("title_search").value;
		DoServ(url + "search?title=" + title, "GET");
	}

	this.readData = function(id) {
		DoServ(url + "read?id=" + id, "GET");
	}

	createCall = function() {
		this.title = document.getElementById("title").value;
		this.message = document.getElementById("message").value;
		this.estimation = document.getElementById("estimation").value;
		this.status = document.getElementById("status").value;
		this.startdate = document.getElementById("startdate").value;
		this.duedate = document.getElementById("duedate").value;
		validate(title, message, estimation, status, startdate, duedate);
		DoServ(url + "create?"
				  +"title="+title
			  	  +"&message="+message
			  	  +"&estimation="+estimation
			  	  +"&status="+status
			  	  +"&startdate="+startdate
			  	  +"&duedate="+duedate,"POST")
	}

}("/ToDoList/"));

	