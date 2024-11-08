function handleAdd(event) {
    const taskId = document.querySelector("#taskId").value;
    const form = document.querySelector(".taskForm");
    const inputfield = document.querySelector("#inputfield");
    const error_msg = document.querySelector(".events");
  
    if (!inputfield.value.trim()) {
      error_msg.innerText = "Input Field is Empty!";
      error_msg.style.color ="red";
      error_msg.style.visibility = "visible";
      inputfield.focus();
  
      setTimeout(() => {
        error_msg.style.visibility = "hidden";
      }, 3000);
  
      if (event) event.preventDefault();
      return false;
    } else {
      form.action = taskId ? "/finaltask/editTask" : "/finaltask/addTodo";
      form.submit();
    }
  }
  

function handleEdit(editId, taskName) {
    document.querySelector("#taskId").value = editId;
    document.querySelector("#inputfield").value = taskName;
    document.querySelector(".addbutton").innerText = "Update";

}