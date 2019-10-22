function listPrint() {
  var x = document.getElementById('t1').value;
  var y = document.getElementById('t2').value;// X, Z and Y VALUES ARE USED FOR THE MODAL GENERATION
  var z = document.getElementById('t3').value;
  var tasks = document.getElementsByTagName('input'); // used for the list print

  if(document.getElementsByTagName('li').length < 6) {
      if (z == '' && x == '' && y == '') {//IF THE FORM IS SUBMITTED EMPTY
          // Get the modal
          var modal = document.getElementById("modal");
          var span = document.getElementsByClassName("close")[0];
          modal.style.display = "block";

          span.onclick = function() {
            modal.style.display = "none"; // When the user clicks on <span> (x), close the modal
          }

          window.onclick = function(event) {
            if (event.target == modal) {
              modal.style.display = "none"; // When the user clicks anywhere outside of the modal, close the modal
            }
          }
        }
        else {
          for(var i = 0; i < tasks.length-1;i++) { //tasks.length-1 because 'submit' considers itself as input, and we dont want to print that to the site
            var listElement = document.createElement("li"); //Creating an 'li' element
            listElement.onclick = function(e) {this.parentNode.removeChild(this)}; //Creating a function for every 'li' element, that removes it by mouseclick
            var listContent = document.createTextNode(tasks[i].value); //Creating a text node, that can be given to the 'li' element
              if(tasks[i].value == ''){
                console.log('Empty values are not printed'); //if 'tasks[i].value' is empty, it is not printed to the site
              }
              else {
                listElement.appendChild(listContent);
                document.getElementById('list').appendChild(listElement); //Else, it is printed on the site
              }
          }
        }
        document.getElementById('t1').value = '';
        document.getElementById('t2').value = ''; //Used to empty the form after submission
        document.getElementById('t3').value = '';
        hideInfo(); //Brings out the 'info' div
        document.getElementById('list').style.opacity = '1.0';
  }
  else {
    alert("The list can only hold 6 items in it");//Can go over 6 if 2/3 elements are added, when list length is 5
  }
}


function show(){
  document.getElementById('who').style.opacity = '1.0'; //Hovering picture makes the text appear
}
function hide() {
  document.getElementById('who').style.opacity = '0.0'; //On mouseout, the text is hidden again
}


function hideInfo() {
  if(document.getElementsByTagName('li').length <= 0) { // if there are no 'li' elements on the website, 'info' div is hidden
    document.getElementById('info').style.opacity = '0.0';
  }
  else {
    document.getElementById('info').style.opacity = '1.0';
  }
}

function dropDown() {
  document.getElementById("dropdown").classList.toggle("show"); //Dropdown content is shown, when the button is pressed
  window.onclick = function(event) { // Close the dropdown if the user clicks outside of it
    if (!event.target.matches('.dropbtn')) { //If click doesnt happen on the button
      var dropdowns = document.getElementsByClassName("dropdown-content"); // Table the content within the class
      for (var i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) { //If table content[i] is affected by 'show' css property,
          openDropdown.classList.remove('show'); //Remove the connection
        }
      }
    }
  }
}
