<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${board} list</title>

	<c:import url="../temp/css.jsp"></c:import>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

		<c:import url="../temp/sidebar.jsp"></c:import>

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

	    	<c:import url="../temp/topbar.jsp"></c:import>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${board}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row  Real Body  -->
                    <div clas="row">
                    <h1>Board List</h1>
                        <table class="table table-hover"> 
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Title</th>
                                    <th>Writer</th>
                                    <th>Date</th>
                                    <th>Hit</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="vo">
                                <tr>
                                	<td>${vo.noticeNum}</td>
                                	<td><a href="./detail?noticeNum=${vo.noticeNum}">${vo.noticeHead}</a></td>
                                	<td>${vo.noticeWriter}</td>
                                	<td>${vo.noticeDate}</td>
                                	<td>${vo.noticeViews}</td>
                                </tr>
                                </c:forEach>

                            </tbody>

                        </table>
                    </div>

                    <div class="row">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                            <c:if test="${!start}">
                              <li class="page-item"><a class="page-link" href="./list?pager=${pager.lastNum-1}&kind=${kind}&search=${search}" aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a></li>
                            </c:if>
                             
                              <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
                              <li class="page-item"><a class="page-link" href="./list?pager=${i}&kind=${kind}&search=${search}">${i}</a></li>
                              </c:forEach>
                             
                             <c:if test="${!last}">
                              <li class="page-item"><a class="page-link" href="./list?pager=${pager.lastNum+1}&kind=${kind}&search=${search}" aria-label="Next"><span aria-hidden="true">&raquo;</span>
                                </a></li>
                              </c:if>
                            </ul>
                          </nav>
                          <div class="pl-4">
                            <a href="add" class="btn btn-primary">글쓰기</a>

                          </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

			<c:import url="../temp/footer.jsp"></c:import>
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    

	<c:import url="../temp/script.jsp"></c:import>

</body>

</html>