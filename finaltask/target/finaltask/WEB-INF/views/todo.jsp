<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored =
"false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>To-Do List</title>
    <link href="<c:url value='/css/todo.css' />" rel="stylesheet" />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <style>
      .Deletebutton,
      .editbutton,.done {
        width: 10%;
        height: 100%;
        background: none;
        border: none;
      }
    </style>
  </head>
  <body>
    <div class="Container">
      <div class="heading"><h1>MY To-Do LIST!</h1></div>
      <form  method="post" class="taskForm" onsubmit="handleAdd(event)">
        <div class="input">
          <input type="hidden" id="taskId" value="" name="editTaskId">
          <input
            type="text"
            maxlength="150"
            id="inputfield"
            class="inputfield"
            placeholder="Enter task..."
            name="TaskName"
          />
          <button type="button" class="addbutton" onclick="handleAdd(event)">Add</button>
        </div>
      </form>

      <p class="events">Hidden</p>
      <div class="taskicon">
        <button class="buttons" id="allTasksCount">All (${ListOfTodo.size()})</button>
        <button class="buttons" id="assignedTasksCount">Assigned (0)</button>
        <button class="buttons" id="completedTasksCount">Completed (0)</button>
      </div>
      <div class="TaskList">
        <ol id="taskList" class="listoftasks">
            <c:choose>
                <c:when test="${not empty ListOfTodo}">
                    <c:forEach var="task" items="${ListOfTodo}">
                        <li>
                            <div class="Listelements ">
                                <div class="taskname ">
                                    ${task.taskName}
                                </div>
    
                                <button class="done " 
                                         >
                                         <i class="fa-solid fa-circle-check fa-2xl" style="color: #3cdd6c;"></i></i>                 
                                </button>

                                <button class="editbutton"   onclick="handleEdit('${task.taskId}', '${task.taskName}')">
                                    <i class="fa-regular fa-pen-to-square fa-2xl" style="color: #0e78c8;"></i>
                                </button>
                                <form action="delete">
                                  <input type="hidden" value="${task.taskId}" name="deleteId">
                                    <button class="Deletebutton" >
                                      <i class="fa-solid fa-trash fa-2xl" style="color: #f23131;"></i>
                                    </button>
                              </form>
                            </div>
                        </li>
                    </c:forEach>
                </c:when>
            </c:choose>
        </ol>
    </div>
    
    <c:choose>
        <c:when test="${empty ListOfTodo}">
            <div class="Notaskimage" style="display: flex">
                <img src="todo.jpg" alt="No tasks image" />
                
            </div>
        </c:when>
    </c:choose>
    
    <div class="clearbuttondiv">
        <button class="clearbutton" id="clearAllButton">Clear</button>
    </div>
    <div id="confirmationModal" class="modal" style="display: none">
      <div class="modal-content">
        <p id="modalText"></p>
        <div class="modal-buttons">
          <button id="confirmButton"><h2>Yes</h2></button>
          <button id="cancelButton"><h2>No</h2></button>
        </div>
      </div>
    </div>
    <script src="<c:url value='/javascript/Todo.js' />"></script>

  </body>
</html>
