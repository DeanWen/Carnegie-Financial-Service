<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Request Check</h1>
                        <div class="panel panel-default">
                        <div class="panel-heading">
                           
                        </div>
                        <!-- /.panel-heading -->
						
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                            	<table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <tbody>
                                     <form action="requestCheck.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Available Balance </th>
                                            <td > ${customer.getCash()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Cash Balance </th>
                                            <td > ${customer.getTotal()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Amount</th>
                                            <td class="center">
                                                <input type="text" class="input-block-level" placeholder="Amount" name="requestAmount" value="${form.getRequestAmount()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Confirm Amount</th>
                                            <td class="center">
                                                <input type="text" class="input-block-level" placeholder="Amount" name="requestCfmAmount" value="${form.getRequestCfmAmount()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <input class="btn btn-lg btn-success btn-block" id = "requestCheck" type = "submit" name = "action" value = "Done"/>
                                            </td>
                                        </tr>
 									</form>
                                   </tbody>
                                </table>                                    
                            </div>       
                            <c:if test="${check}">
                            <div class="alert alert-success">
                            	Congratulations! Request Check successfully!    
                            </div> 
                            </c:if>
                            <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}.
                            	</div> 
							</c:forEach>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
             </div>
            <!-- /.container-fluid -->
        </div>
	
<jsp:include page="template-bottom.jsp" />
