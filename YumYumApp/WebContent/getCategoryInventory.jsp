<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yumyum.*" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="java.io.IOException" %>
<%
Iterator<Recipe> it=(Iterator<Recipe>)request.getAttribute("data");
List<String> list = new ArrayList<String>();
boolean isEmpty=true;
Category category=Category.valueOf(request.getHeader("category"));
try
{
	while (it.hasNext())
	{
		Recipe curr=it.next();
		if(curr.getCategory()==category)
		{
			list.add(curr.getCategory().toString());	
			list.add(curr.getName());	
			list.add(curr.getTitle());
			list.add(curr.getMaterials());	
			list.add(curr.getDescription());	
			isEmpty=false;
		}
	}
	if(isEmpty==false)
	{
	    JSONObject json = new JSONObject();
		json.accumulate("recipes", list);
		out.print(json.toString());
	}
	else
	{
		out.print("no");
	}
}
catch (JSONException e) 
{
e.printStackTrace();
}
catch (IOException e) 
{
e.printStackTrace();
}
%>