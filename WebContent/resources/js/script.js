(function ToDoList () {
	this.id;
	this.title;
	this.message;
	this.estimation;
	this.status;
	this.startdate;
	this.duedate;

	this.initalData = function() {
		document.getElementById("title_search").value = "";
		DoServ("readAll", "GET");
	}

	this.create = function() {
		DoServ("todo/create", "GET");
	}

	this.search = function() {
		var title  = document.getElementById("title_search").value;
		DoServ("search?title=" + title, "GET");
	}

	this.readData = function(id) {
		DoServ("read?id=" + id, "GET");
	}

	createCall = function() {
		this.title = document.getElementById("title").value;
		this.message = document.getElementById("message").value;
		this.estimation = document.getElementById("estimation").value;
		this.status = document.getElementById("status").value;
		this.startdate = document.getElementById("startdate").value;
		this.duedate = document.getElementById("duedate").value;
		validate(title, message, estimation, status, startdate, duedate);
		DoServ("create?"
				  +"title="+title
			  	  +"&message="+message
			  	  +"&estimation="+estimation
			  	  +"&status="+status
			  	  +"&startdate="+startdate
			  	  +"&duedate="+duedate,"POST")
	}

}());

	