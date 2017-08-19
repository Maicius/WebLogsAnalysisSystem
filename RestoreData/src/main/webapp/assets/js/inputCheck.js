"use strict"
$(document).ready(function () {
    $("#calendarBegin").blur(function () {
        checkData();
    })
    $("#courtSelect").change(function () {
        //alert("court:" + $("#courtSelect").val());
        checkData();
    })
});
function checkData(){
    let courtData = $("#courtSelect").val();
    let dateBegin = $("#calendarBegin").val();
    // alert("data:" + courtData);
    if(dateBegin !== "" && courtData != ""){
        //alert("court:" + $("#courtSelect").val());
        $("#conditionQuery").removeAttr("disabled");
    }
    else{
        $("#conditionQuery").attr("disabled", true);
    }
}