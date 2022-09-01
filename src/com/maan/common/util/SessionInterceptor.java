package com.maan.common.util;
/**
 * @author Raja.K
 *
 * Common Login Template
 */
import com.maan.common.util.LogUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;
import org.slf4j.Logger;
public class SessionInterceptor  implements Interceptor {
    
    private static final Logger logger = LogUtil.getLogger(SessionInterceptor.class);
       
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        logger.debug(" User Id - {}",(String)session.get("UserId"));
        String result;
        if(session.get("UserId") == null) {
             result = "SessionExpired";
        }else{
            result = actionInvocation.invoke();
        }
        return result;
    }

    public void destroy() {
        
    }

    public void init() {
        
    }
} 
