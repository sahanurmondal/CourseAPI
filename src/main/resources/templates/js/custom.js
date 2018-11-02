
//For search topic
  function setActionSearch( form ){
$('#search-form').attr('action', '/topics/'+$('#Id').val());
//alert("HIIIII  "+$('#searchId').val());
//alert($('#search-form').attr('action'));
return true;
}

//For update topic
  function setActionUpdate( form ){
$('#update-form').attr('action', '/topics/'+$('#Id').val());
//alert("HIIIII  "+$('#searchId').val());
//alert($('#search-form').attr('action'));
return true;
}

//For delete topic
function setActionDelete( form ){
$('#delete-form').attr('action', '/topics/'+$('#Id').val());
//alert("HIIIII  "+$('#searchId').val());
//alert($('#delete-form').attr('action'));
var url=getBaseUrl() + $('#delete-form').attr('action');
//loadDoc(url,"DELETE");
//alert(url);
$.ajax({
    url: url,
    type: 'DELETE',
    //contentType: 'application/json',
    success: function(result) {
          // alert('HII '+result);
        document.getElementById("demo1").innerHTML =result;
        //document.getElementById("demo1").appendChild(document.createElement("br"));
    },
    error: function(request,msg,error) {
        // handle failure
    }
});
return false;
}

// to get base url of the request
function getBaseUrl(){
var loc = window.location;
var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "");
return baseUrl;
}
//for switch to user
  function showDiv( form ){
//document.getElementById('userDiv').style.display = "block";

document.getElementById('userDiv').setAttribute('style','display:block');
var loc = window.location;
var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "");
var url=baseUrl+$('#switch-user').attr('action');
//alert('HIII ' + url);
loadDoc(url,"GET");
return false;
}

//to show response in the same page
function loadDoc(url,methodType) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo1").innerHTML =
      this.responseText;
    }
  };
  //alert(methodType);
  xhttp.open(methodType, url, true);
  //alert(methodType);
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