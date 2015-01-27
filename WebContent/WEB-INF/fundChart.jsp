<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.Fund_Price_History_Bean"%>
<%@ page language="java" import="java.util.*"%>
<jsp:include page="template-top.jsp" />

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
    		<div class="col-lg-12">
				<h1 class="page-header">History Price for <c:out value="${fundName}" /></h1>
<%
	if (request.getAttribute("beans") != null) {
%>
<script type="text/javascript" src="https://www.google.com/jsapi"?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>
 <script type="text/javascript">
 		google.load('visualization', '1', {packages: ['corechart']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
	      var data = new google.visualization.DataTable();
	      var jsArr = new Array();  
	  	  var jsArr2 = new Array();
	  	    
	  	    <%
		  	  ArrayList<Fund_Price_History_Bean> fps= (ArrayList<Fund_Price_History_Bean>)request.getAttribute("beans");
		  	    for (int i=0; i < fps.size(); i++) {  
		  	    %>  
	  	    jsArr[<%= i %>] = '<%=fps.get(i).getPrice_date() %>'; 
	  	    <%}%>
	  	    <%  
		  	    for (int i=0; i < fps.size(); i++) {  
		  	    %>  
	  	    jsArr2[<%= i %>] = '<%=fps.get(i).getPrice() %>'; 
	  	    <%}%>
	    	  data.addColumn('string','Date');
	    	  data.addColumn('number', 'Price');
	    	  data.addRows(jsArr.length);
	    	  //alert(jsArr.length);
	    	  <%  
		    	    for (int i=0; i < fps.size(); i++) {
		    	    	if(i==0 || i==fps.size()-1){
		    	    %>
	    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	    <%}else{%>
	    	    data.setValue(<%= i %>,0, "");
	    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
	    	    <%}
		    	    }
		    	  %>
	    	  
	        var options = {
	          title: 'Price History'
	        };
	
	        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
      }
    </script>
    <%
	}
%>
	<body>
	<div id="chart_div" style="width: 900px; height: 500px"></div>
	</body>
</div>
</div>
</div>
</div>
<jsp:include page="template-bottom.jsp" />

