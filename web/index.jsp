<%-- 
    Document   : index
    Created on : Mar 21, 2022, 10:23:06 PM
    Author     : A
--%>
<%@page import="model.UserModel"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
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

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (UserModel model : list) {%>
                    <tr>
                        <td><%= model.getId()%></td>
                        <td><%= model.getName()%></td>
                        <td><%= model.getMobile()%></td>
                        <td><%= model.getEmail()%></td>
                        <td><button type="button" class="btn btn-outline-info">Edit</button></td>
                        <td><button  class="btn btn-outline-danger" onclick="deleteRow(<%= model.getId()%>)">Delete</button></td>
                    </tr>
                    <%}%>


                </tbody>
            </table>
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
