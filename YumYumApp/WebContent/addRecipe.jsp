<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yumyum.*" %>
<%@ page import="java.io.IOException" %>
<%
if(request.getAttribute("fault").equals("exception"))
{
out.print("The recipe was adeed succesfuly!");
}
else
{
out.print("exception");	
}
%>