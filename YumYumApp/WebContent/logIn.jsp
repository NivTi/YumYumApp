<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yumyum.*" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="java.io.IOException" %>
<%if (request.getAttribute("isLoggedIn")=="true"){out.print("You're Logged In");}
else{out.print("no");}%>

