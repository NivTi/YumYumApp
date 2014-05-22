<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yumyum.*" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="java.io.IOException" %>
<%
Iterator<Recipe> it=(Iterator<Recipe>)request.getAttribute("data");
//List<String> list = new ArrayList<String>();
/*int Id=(int)request.getAttribute("id");
int Id=(int)request.getSession().getAttribute(IDusr);*/
//int Id=(int)session.getValue("IDusr");
int Id=Integer.parseInt(response.getHeader("userIDD"));
List<String> list = new ArrayList<String>();
while (it.hasNext())
{
	Recipe curr=it.next();
	if(curr.getUser().getUserId()==Id)
	{
		list.add(curr.getCategory().toString());
		list.add(curr.getTitle());
		list.add(curr.getName());	
		list.add(curr.getMaterials());	
		list.add(curr.getDescription());	
		list.add(String.valueOf(curr.getId()));
	}
}     
JSONObject json = new JSONObject();
json.accumulate("recipes", list);
response.getWriter().print(json.toString());
/*
catch (JSONException e1) 
    	{
			e1.printStackTrace();
		}
    	catch (IOException e) 
 		{
			e.printStackTrace();
		} 
*/
%>
