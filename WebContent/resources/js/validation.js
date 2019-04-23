validate = function() {
	var arrayerror = [];
	var field = ["title", "messge", "estimation", "status", "start date", "due date"];
	for(i = 0; i < arguments.length; i++) {
	    if (arguments[i].length == 0) {
	    	arrayerror.push(field[i] + " is empty");
	    }
	  }
	if (arrayerror.length > 0) {
		for(i = 0; i < arrayerror.length; i++) {
			window.alert(arrayerror[i]);
		}
		throw 500;
	}
}