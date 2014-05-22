function myFunc()
{
var name=$('#fullName').val();
var title=$('#title').val();
var materials=$('#materials').val();
var description=$('#description').val();
var category= $("input[name=radio]:checked").val();
var result=moveToJava.addRecipe(name,title,materials,description,category);
alert(result);
$('#fullName').val(new String());
$('#title').val(new String());
$('#materials').val(new String());
$('#description').val(new String());
$( '#oneUpdate' ).prop( 'checked', true );
$.mobile.changePage("#home");
}

function login()
{
var user=$('#user').val();
var password=$('#password').val();

var g=moveToJava.login(user,password);
if(g=="no")
{
	alert("Sorry, username/password incorrect, please try again");
	
}
else
{
	alert(g);
	$.mobile.changePage("#home");
}	
	
}


function signUps()
{
var user=$('#userS').val();
var password=$('#passwordS').val();
var g=moveToJava.signUp(user,password);
alert(g);
$.mobile.changePage("#loginSignUp");

}

function personalInventory()
{
$('.hello').empty();
var g=moveToJava.personalInventory();
var ob=$.parseJSON(g);
for (var i=0;i<ob.recipes.length;i=i+6)
{	
	$('#new').append('<div id=Recipe'+i+' style="font-family:verdana;padding:20px;border-radius:20px;border:10px solid #0E94ED;background-color:#C1D1F5;color:black;">'+'Category: '+'<div id="myCatagory" style="display:inline-block;">'+ob.recipes[i]+'</div><br>'+'Title: '+'<div id="myTitle" style="display:inline-block;">'+ob.recipes[i+1]+'</div><br>'+'Recipe Author: '+'<div id="myName" style="display:inline-block;">'+ob.recipes[i+2]+'</div><br>'+'The meterials you need are: '+'<div id="myMaterials" style="display:inline-block;">'+ob.recipes[i+3]+'</div><br>'+'Please follow this: '+'<div id="myDescription" style="display:inline-block;">'+ob.recipes[i+4]+'</div><br>'+'<div id="myCouponId" style="display:none">'+ob.recipes[i+5]+'</div>'+'<input type="submit" id="edit'+i+'" data-inline="true" data-role="button" value="Edit" onclick="editRecipe('+i+')">'+'<input type="submit" id="delete'+i+'" data-inline="true" data-role="button" value="Delete" onclick="deleteRecipe('+i+')">'+'</div>');
    //$('#new').append('<div style="opacity:0.3;position:absolute;left:120px;width:100px;height:200px;background-color:#8AC007;">');
	$('#edit'+i).button();
	$('#delete'+i).button();
	$('#new').append('<br>');  
}
$.mobile.changePage("#all");		
}

function editRecipe(myId)
{
var category=$('#Recipe'+myId).children('#myCatagory').text();
switch(category)
{
case "Pasta":
	$( '#oneUpdate' ).prop( 'checked', true );
	break;
case "Meat":
	$( '#twoUpdate' ).prop( 'checked', true );
	break;
case "Cakes":
	$( '#threeUpdate' ).prop( 'checked', true );
	break;
case "Drinks":
	$( '#fourUpdate' ).prop( 'checked', true );
	break;
}

$('#fullNameUpdate').val($('#Recipe'+myId).children('#myName').text());
$('#titleUpdate').val($('#Recipe'+myId).children('#myTitle').text());
$('#materialsUpdate').val($('#Recipe'+myId).children('#myMaterials').text());
$('#descriptionUpdate').val($('#Recipe'+myId).children('#myDescription').text());
$('#CouponId').text($('#Recipe'+myId).children('#myCouponId').text());
$.mobile.changePage("#UpdateRecipe");	
}

function deleteRecipe(myId)
{
$('#CouponId').text($('#Recipe'+myId).children('#myCouponId').text());
var cpId=$('#CouponId').text();
var result=moveToJava.deleteRecipe(cpId);
alert(result);
$.mobile.changePage("#home");
}

function UpdateRecipe()
{
var name=$('#fullNameUpdate').val();
var title=$('#titleUpdate').val();
var materials=$('#materialsUpdate').val();
var description=$('#descriptionUpdate').val();
var category= $("input[name=update]:checked").val();
var cpId=$('#CouponId').text();
var result=moveToJava.updateRecipe(name,title,materials,description,category,cpId);
alert(result);
$.mobile.changePage("#home");
}


function logOut()
{
var result=moveToJava.logOut();
alert(result);
$.mobile.changePage("#loginSignUp");
}


function showInvCategory()
{
$('.hello').empty();
var category= $("input[name=categoryInv]:checked").val();

var g=moveToJava.getInvCategory(category);
if(g!="no")
{
	var ob=$.parseJSON(g);
		for (var i=0;i<ob.recipes.length;i=i+5)
		{			
			//$('#new').append('<div style="opacity:0.3;position:relative; top:60px; left:200px; width:100px;height:30px; background-image:url("/6.png");">bla</div>');
			$('#new').append('<div id=Recipe'+i+' style="font-family:verdana;padding:20px;border-radius:20px;border:10px solid #0E94ED;background-color:#C1D1F5;color:black;">'+'Category: '+'<div id="myCatagory" style="display:inline-block;">'+ob.recipes[i]+'</div><br>'+'Title: '+'<div id="myTitle" style="display:inline-block;">'+ob.recipes[i+1]+'</div><br>'+'Recipe Author: '+'<div id="myName" style="display:inline-block;">'+ob.recipes[i+2]+'</div><br>'+'The meterails you need are: '+'<div id="myMaterials" style="display:inline-block;">'+ob.recipes[i+3]+'</div><br>'+'Please follow this: '+'<div id="myDescription" style="display:inline-block;">'+ob.recipes[i+4]+'</div></div>');	
			$('#new').append('<br>');  
		}
	$.mobile.changePage("#all");
}
else
{
	alert("no recipes to show!");
}
}
