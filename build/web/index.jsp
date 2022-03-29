<%-- 
    Document   : index
    Created on : Mar 21, 2022, 10:23:06 PM
    Author     : A
--%>
<%@page import="model.UserModel"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@ page isErrorPage="true"%>
<%
    UserDao dao = new UserDao();
    List<UserModel> list = dao.getAllData();
    System.out.println("list = " + list);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crud</title>

        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300i,400,700&display=swap" rel="stylesheet">
        

    </head>
    <body>
        <div class="container mt-3">
            <h2>Add User</h2>
            <form method="POST" action="/Crud/user-api">
                <div class="mb-3 mt-3">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
                </div>

                <div class="mb-3 mt-3">
                    <label for="mobile">Mobile:</label>
                    <input type="tel" class="form-control" id="mobile" placeholder="Enter mobile number" name="mobile">
                </div>

                <div class="mb-3 mt-3">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

            <!--begin table-->
            <div class="row pt-3 pb-5">
                <%for (UserModel model : list) {%>
                <div class="col-lg-3 col-md-6 mt-sm-20">
                    <div class="card" >
                        <img  src="/Crud/image-api?id=<%= model.getId() %>"  width="100" height="100">
                        <div class="card-body">
                            <h5 class="card-title"><%= model.getName()%></h5>
                            <div class="row pb-4 pt-2">
                                <div class="col">
                                    <h5>$ 20</h5>
                                </div>
                                <div class="col text-right">
                                    <h5><s class="text-danger">$ 35</s></h5>
                                </div>
                            </div>
                            <a href="#" class="btn btn-block">Add to cart</a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
            <!--end table-->
        </div>




        <script type="text/javascript">
            function deleteRow(id) {
                //alert(id)
                $.ajax({
                    url: "/Crud/delete-api",
                    type: "post",
                    data: {
                        id: id.toString(),

                        success: function (data) {
                            //alert(data);
                            window.location.reload()
                        }
                    }
                });
            }
        </script>
    </body>
</html>
