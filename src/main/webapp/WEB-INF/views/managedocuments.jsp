<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload/Download/Delete Documents</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
         
         var numbers;
            function sayHello(id)
			{       
			   
			   //var x=document.getElementById(id).checked;
			   if(document.getElementById(id).checked)
			   {
			     numbers=id;
			   }
			   /*else{
			   numbers.splice(numbers.indexOf(id),1);
			   }*/
			    
            }
		 
		 $(document).ready(function(){
         $("#myBtn").click(function(){
         $("#myModal").modal();
		var mymodal=$('#myModal');
		//mymodal.find('.modal-body').text(numbers);
		});
	});
	<!--  submit json-->
	var id;
	function sendData() {
		var e=document.getElementById('ct');
    $.ajax({
        url: 'http://localhost:8080/Spring4MVCFileUploadDownloadWithHibernate/deviceLocation?cityName="'+e.options[e.selectedIndex].value+'"',
        type: 'POST',
		data:'',
        dataType: 'json',
		success:function(response){
							   for(var i=0;i<response.length;i++)
							   {
							   $('#location').append($('<option id='+response[i].id+'>'+response[i].devceLocationName+'</option>'));
							   
							   }
							   console.log(response);
                            }
    });
	
	
}

function validateCheckbox() 
{
  if ($('input[name=test]:checked').length <= 0)
  {
    alert("Please select any one Image/video before proceed...")
  }
	else 
	{	
		//$('#myModal').modal('data-target="#myModal"');
		$('#schedule').attr('data-target','#myModal');
	}
}

function getDevices() {
	var e=document.getElementById('location');
	
	var fld = document.getElementById('location');
	var values = [];
	for (var i = 0; i < fld.options.length; i++) {
			if (fld.options[i].selected) {
				values.push(fld.options[i].id);
			}
	}
	//alert(values);
	//var obj = {'1','2'};

    $.ajax({
        url: 'http://localhost:8080/Spring4MVCFileUploadDownloadWithHibernate/deviceByLocations', //+ e.options[e.selectedIndex].id,
        type: 'POST',
		contentType:'application/json',
		data:'['+values+']',
        dataType: 'json',
		success:function(response){		      
                             $('#devices').empty();
							 for(var i=0;i<response.length;i++)
							 {
								for(var j=0;j<1;j++)
								{
									//console.log(response[i][j].devceLocationName);
									//console.log('<option id='+response[i][j].id+'>'+response[i][j].deviceLocation.devceLocationName+' - '+response[i][j].deviceName+' - '+response[i][j].deviceCategory.category+'</option>');
									$('#devices').append($('<option id='+response[i][j].id+'>'+response[i][j].deviceLocation.devceLocationName+' - '+response[i][j].deviceName+' - '+response[i][j].deviceCategory.category+'</option>'));
								}							  }
							// console.log(response);							
                            }
    });
}

function postJson() {
	if(document.getElementById("ct").value=="")
	{
		alert("Please Choose City")
	}
	else if(document.getElementById("location").value=="")
	{
		alert("Please Choose Locations")
	}
	else if(document.getElementById("devices").value=="")
	{
		alert("Please Choose Devices")
	}
	else if(document.getElementById("scdate").value=="")
	{
		alert("Please Choose Start Date")
	}
	else if(document.getElementById("edate").value=="")
	{
		alert("Please Choose End Date")
	}
    else
	{
		var scdate=document.getElementById('scdate');
		var edate=document.getElementById('edate');
		var did=document.getElementById('devices');
		var price=document.getElementById('price');
		var deviceIds = $('#devices option:selected').map( function(i,el){
		var result = [el.id];
		//result[ el.id ] = $(el).val();
		return result;	
		}).get();
		var markers = { "startDate": document.getElementById('scdate').value,"endDate": document.getElementById('edate').value, "deviceId": deviceIds,"contnetId":numbers,"price":price.value};

		$.ajax({
			url: 'http://localhost:8080/Spring4MVCFileUploadDownloadWithHibernate/saveContents',
			type: 'POST',
			contentType:'application/json',
			data:JSON.stringify(markers),
			dataType: 'json',
			success: function(data)
			{
				$(function(e)
				{
					alert(response.status)
					$('#myModal').modal('toggle'); //or  $('#IDModal').modal('hide');
					$("#successModal").modal();
					var mymodal1=$('#successModal');
					return false;
				});
				console.log(data);
			},
			error: function (response)
			 {
				console.log(response);
				if(response.status==404)
				{
					alert("Somthing wrong ... Please try again");
				}
			 },
			failure: function(e) 
			{
				alert("sdfdsf");
				console.log(data);
				$('#myModal').modal('hide');
			}
		});
		$('#myModal').modal('hide');
	}
}
function getPrice() {
	var scdate=document.getElementById('scdate');
    if(document.getElementById('scdate').value<gettodaydate())
	{
		alert("Please Choose Correct Start date")
		document.getElementById('scdate').value="";
	}
	else if(document.getElementById('edate').value<gettodaydate())
	{
		alert("Please Choose Correct End date")
		document.getElementById('edate').value="";
	}
	else if(document.getElementById('scdate').value>document.getElementById('edate').value)
	{
		alert("Start Date should be Greater than End Date")
		document.getElementById('scdate').value="";
		document.getElementById('edate').value="";
	}
	var edate=document.getElementById('edate');
	var did=document.getElementById('devices');
	var deviceIds = $('#devices option:selected').map( function(i,el){
    var result = [el.id];
    //result[ el.id ] = $(el).val();
    return result;	
}).get();
//console.log(y);
	var markers = { "startDate": scdate.value,"endDate": edate.value, "deviceId": deviceIds};
    $.ajax({
        url: 'http://localhost:8080/Spring4MVCFileUploadDownloadWithHibernate/price',
        type: 'POST',
		contentType:'application/json',
		data:JSON.stringify(markers),
        dataType: 'json',
		success:function(response){ 
                                document.getElementById("price").value = response;
							//console.log(response);
                            }
    });
}

// generic functions

function todaysDate()
{
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	var x = parseInt("1");
	var y = parseInt(day);
	var tomorrow = now.getFullYear()+"-"+(month)+"-"+(x + y) ;
	$('#scdate').val(tomorrow);
	//$('#edate').val(tomorrow);

}
function gettodaydate()
{
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	return today;
}
</script>
  
    
</head>

<body>
	<div class="generic-container">
	<%@include file="authheader.jsp" %>
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Documents </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>No.</th>
					        <th>File Name</th>
					        <th>Type</th>
					        <th>Description</th>
					        <th width="100"></th>
					        <th width="100"></th>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${documents}" var="doc" varStatus="counter">
						<tr>
							<td>${counter.index + 1}</td>
							<td>${doc.name}</td>
							<td>${doc.type}</td>
							<td>${doc.description}</td>
							<td><input type="radio" id="${doc.id}" name="test" onclick="sayHello(${doc.id})"></td>
							<td><a href="<c:url value='/download-document-${user.id}-${doc.id}' />" class="btn btn-success custom-width">download</a></td>
							<td><a href="<c:url value='/delete-document-${user.id}-${doc.id}' />" class="btn btn-danger custom-width">delete</a></td>
						</tr>
					</c:forEach>
					</tbody>
		    	</table>
		    </div>
			 
			<div class="container">
  
  <!-- Trigger the modal with a button -->
  <div>
 <div align="right" class="tablecontainer">
  <button type="button" id="schedule" class="btn btn-info btn-lg" data-toggle="modal" onclick="validateCheckbox();todaysDate();">schedule Campaign</button>
<div>
  </div>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog" data-refresh="true">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" align="center" >schedule Campaign</h4>
        </div>
		<div class="tablecontainer">
		<div class="modal-body">
		<table cellspacing="10" border="0" width="100%" class="table table-hover">
  <tr>
    <th>City</th>
    <th>Places</th>
	<th>Devices</th>
  </tr>
  <tr>
    <td>
		<select id="ct" onChange="sendData();">
		<option value="" disabled selected hidden>Please Choose City</option>
		<option value="Bangalore">BANGALORE</option>
		</select>
	</td>
	<td> 
		<select id="location" onChange="getDevices();" multiple>
		<option value="" disabled selected hidden>Please Choose Location</option>
	    </select>
	</td>
	<td> 
		<select id="devices" multiple>
		</select>
	</td>
	</tr>
	<tr>
<td><b>Start Date: </b><input type="date" name="startdate" id="scdate" onChange="getPrice();"></td>
<td><b>End Date:</b> <input type="date" name="enddate" id="edate" onChange="getPrice();"></td>
<td></td>
</tr>
<tr><td><b>Note :</b><i>Device Categoty - A will be played for 30 seconds 80times a day</i> </td>
<td><i><b>Note :</b>	 Device Categoty - B will be played for 20 seconds 60times a day <i></td>
<td><b>Total Cost:</b> <input class="noborder" type="input" name="price" id="price" readonly></td>

  </tr>
</table>
</div>
        
          
        </div>
        <div class="modal-footer">
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" onclick="postJson();">Schedule Campaign</button>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</div>
			
		</div>
		<div class="panel panel-default">
			
			<div class="panel-heading"><span class="lead">Upload New Document</span></div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
			
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Upload a document</label>
							<div class="col-md-7">
								<form:input type="file" path="file" id="file" class="form-control input-sm"/>
								<div class="has-error">
									<form:errors path="file" class="help-inline"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Description</label>
							<div class="col-md-7">
								<form:input type="text" path="description" id="description" class="form-control input-sm"/>
							</div>
							
						</div>
					</div>
			
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload" class="btn btn-primary btn-sm">
						</div>
					</div>
	
				</form:form>
				</div>
		</div>
	 	<div class="well">
	 		Go to <a href="<c:url value='/list' />">Users List</a>
	 	</div>
   	</div>
	
	<script type="text/javascript">
        $('#myModal').on('hidden.bs.modal', function () {
		$('#ct').append('<option value="" disabled selected hidden>Please Choose city</option>');	
        $('#location').empty();
		$('#location').append('<option value="" disabled selected hidden>Please Choose Location</option>');
		$('#devices').empty();
		//$('#scdate').empty();
		//$('#edate').empty();
		document.getElementById('scdate').value="";
		document.getElementById('edate').value="";
		document.getElementById('price').value="";
});
    </script>
</body>
</html>