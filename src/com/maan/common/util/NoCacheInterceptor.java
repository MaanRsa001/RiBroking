package com.maan.common.util;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.StrutsStatics;

public class NoCacheInterceptor implements Interceptor {

	private static final long serialVersionUID = 1321L;
	//private static final Logger logger = LogUtil.getLogger(NoCacheInterceptor.class);
    
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest)context.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) context.get(StrutsStatics.HTTP_RESPONSE);
        HttpSession ses = request.getSession(false);
        //logger.info("Session Before Action Invoke => "+ses);        
        String sessionid = "";
        String result = null;
        if(ses!=null){
            sessionid = ses.getId();                        
        }else 
        	result="SessionExpired";
        if(response!=null){
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly");
            response.setHeader("X-Frame-Options", "deny");
        }
        result = (result==null?actionInvocation.invoke():result);
        //logger.info("Session after Action Invoke => "+request.getSession(false));
        return result;
    }

    public void destroy() {
        
    }

    public void init() {
        
    }
}
