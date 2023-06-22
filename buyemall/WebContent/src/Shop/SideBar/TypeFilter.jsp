<%@page import="it.unisa.bean.Tipo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Type Start -->

<%
    	List<String> type=(List<String>) request.getAttribute("type");
		List<Integer> countType=(List<Integer>) request.getAttribute("countType");
		Integer count1=(Integer) request.getAttribute("count");
		
    	if(type==null){
    		type=new LinkedList<>();
    	}
    	if(countType==null){
    		countType=new LinkedList<>();
    	}
    	if(count1==null){
    		count1=0;}
    	
    %>
                <div class="border-bottom mb-4 pb-4">
                    <h5 class="font-weight-semi-bold mb-4">Filtra per Tipo</h5>
                    <form action="javascript:void(0)">
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" onchange="typeFilter(this)" name="typeForm" value="null" class="custom-control-input" checked id="color-all">
                            <label class="custom-control-label" for="color-all">All Type</label>
                            <span class="badge border font-weight-normal"><%= count1 %></span>
                        </div>
                        <%for(int i=0;i< type.size();i++){ %>
                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                            <input type="checkbox" onchange="typeFilter(this)" name="typeForm" value=<%= type.get(i) %> class="custom-control-input" id="color-<%=type.get(i)%>">
                            <label class="custom-control-label" for="color-<%=type.get(i)%>"><%= type.get(i) %></label>
                            <span class="badge border font-weight-normal"><%= countType.get(i) %></span>
                        </div>
                        <%} %>
                        
                    </form>
                </div>
                
                
                <script src="js/TypeFilter.js">
                
                </script>
                <!-- Color End -->