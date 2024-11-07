<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <form action="/finaltask/todo" method="post">
      <p>Welcome to Todo Page</p>
      <label for="todo">Enter Task Name:</label>
      <input type="text" id="todo" name="TaskName" />
      <input type="submit" value="Add Task">
    </form>

    <table border="1">
        <thead>
          <tr>
            <th style="width: 25%">task Id</th>
            <th style="width: 25%">Task Name</th>
           
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty ListOfTodo}">
              <c:forEach var="task" items="${ListOfTodo}">
                <tr>
                  <td>${task.taskId}</td>
                  <td>${task.taskName}</td>
                 
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="2" style="text-align: center">
                  No data available
                </td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>

  </body>
</html>
