<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page isErrorPage="true" isELIgnored="false"%>
<html>
    <head>
        <title><s:text name="Application.Title" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
    </head>
    <body>
        <table width="60%" align="center">
            <tr>	
                <td align="center">                     
                    <font color="red" style="font-family: Algerian; font-size: 30pt; font-weight: bold;" > Session Expired !!! </font>
                </td>
            </tr>                      
            <tr>	
                <td align="center">                     
                    <p><font color="black" style="font-family: Calibri; font-size: 20pt; font-weight: bold;" > 
                        Your session has expired. 
                    </font></p>
                    <p>
                        Browser will redirect you to login page in  <span id="time">5</span> second(s).
                        <br />
                        If not Please <a href="${pageContext.request.contextPath}/Loginout.action">Click Here</a> to continue.
                        <%  
                            Logger logger = LoggerFactory.getLogger("ErrorMsg.jsp");
                            logger.error("User Name ::  ", (session != null ? session.getAttribute("UserId") : "Session is Null"));
                            logger.info("Browser :: {}", (String) request.getHeader("User-Agent"));
                            logger.error("Exception  ", exception);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String errorTime = sdf.format(new Date());
                        %> 
                        <br />
                        Log Time: <%=errorTime %>
                        </p>
                </td>
            </tr>
        </table>
        <script type="text/javascript">
            var sec = 5;
            var timerId = setInterval("UpdateTimer()", 1000);
            function UpdateTimer(){
                sec = sec-1;
                document.getElementById("time").innerHTML = sec;
                if(sec == 0){
                    window.clearInterval(timerId); 
                }
            }
                
            setInterval("PageRedirection()", 5000);
            function PageRedirection(){
                window.location.href = "${pageContext.request.contextPath}/Loginout.action";
            }
        </script>  
    </body>
</html>