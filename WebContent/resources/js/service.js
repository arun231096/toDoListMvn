DoServ = function(url, method) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	document.getElementById("content").innerHTML = this.responseText;
	    } 
	    if (this.status == 400) {
	    	document.getElementById("content").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open(method, url, false);
	  xhttp.send();
}