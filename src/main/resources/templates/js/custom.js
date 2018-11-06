
//For search topic
  function setActionSearch( form ){
    $('#search-form').attr('action', '/topics/'+$('#Id').val());
    return true;
  }

//For update topic
  function setActionUpdate( form ){
    $('#update-form').attr('action', '/topics/'+$('#Id').val());
    data =convertToJsonData(form) ;
    var url=getBaseUrl() + $('#update-form').attr('action');
    sendJsonUsingAjax(url,'PUT',data);
    return false;
  }

//For delete topic
 function setActionDelete( form ){
    $('#delete-form').attr('action', '/topics/'+$('#Id').val());
    var url=getBaseUrl() + $('#delete-form').attr('action');
    sendJsonUsingAjax(url,"DELETE");
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
    document.getElementById('userDiv').style.display = "block";
    var url=getBaseUrl()+$('#switch-user').attr('action');
    alert(url);
    sendJsonUsingAjax(url,"GET");
    return false;
  }

//For Add operation
  function setAddDetails( form ){
    data =convertToJsonData(form) ;
    var url=getBaseUrl() + $('#add-form').attr('action');
    sendJsonUsingAjax(url,'POST',data);
    return false; // submit
  }

//to convert form data to json
  function convertToJsonData(form){
    var serialized = $(form).serializeArray();
            var s = '';
            var data = {};
            for(s in serialized){
                data[serialized[s]['name']] = serialized[s]['value'];
            }
    return JSON.stringify( data );
  }

//for Ajax call
  function sendJsonUsingAjax(url,methodType,data){
    $.ajax({
        url: url,
        data:data,
        type: methodType,
        contentType: 'application/json',
        success: function(result) {
              // alert('HII1 '+result);
            document.getElementById("demo1").innerHTML =result;
            //document.getElementById("demo1").appendChild(document.createElement("br"));
        },
        error: function(request,msg,error) {
            //alert('Bye '+error);
        }
    });
  }