
//For search topic
  function setAction( form ){
$('#search-form').attr('action', '/topics/'+$('#Id').val());
//alert("HIIIII  "+$('#searchId').val());
//alert($('#search-form').attr('action'));
return true;
}

//For update topic
  function setActionForUpdate( form ){
$('#search-form').attr('action', '/topics/'+$('#Id').val());
//alert("HIIIII  "+$('#searchId').val());
//alert($('#search-form').attr('action'));
return true;
}

//for switch to user
  function showDiv( form ){
document.getElementById('userDiv').style.display = "block";
return false;
}

//to show response in the same page
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo1").innerHTML =
      this.responseText;
    }
  };
  xhttp.open("GET", "ajax_info.txt", true);
  xhttp.send();
}


//to convert form data to json
function onSubmit( form ){
  var serialized = $(form).serializeArray();
        var s = '';
        var data = {};
        for(s in serialized){
            data[serialized[s]['name']] = serialized[s]['value'];
        }
   data = JSON.stringify( data );
    alert(data);
  console.log( data );
  return true; // submit


}