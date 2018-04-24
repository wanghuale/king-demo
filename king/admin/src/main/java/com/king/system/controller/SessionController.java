package com.king.system.controller;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * session 管理
 * @author wanghuaying
 *
 */
@Controller
@RequestMapping("/sessions")
public class SessionController {
	
	@Autowired
	private SessionDAO redisShiroSessionDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		Collection<Session> sessions =  redisShiroSessionDAO.getActiveSessions();  
        model.addAttribute("sessions", sessions);  
        model.addAttribute("sesessionCount", sessions.size());  
        for(Session session:sessions){

        	System.out.println("登录ip:"+session.getHost());
        	System.out.println("退出标识"+session.getAttribute("session.force.logout"));
        	//System.out.println("登录用户"+session.getAttribute(DefaultWebContext.PRINCIPALS_SESSION_KEY));

        	System.out.println("最后操作日期:"+session.getLastAccessTime());

        	}
        
        return "/session/list";
	}

	 @RequestMapping("/{sessionId}/forceLogout")
    public String forceLogout(
            @PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
        try {
            Session session = redisShiroSessionDAO.readSession(sessionId);
            System.out.println(session.getId()+"------");
            if(session != null) {
                session.setAttribute("session.force.logout", Boolean.TRUE);
                redisShiroSessionDAO.update(session);
            }
        } catch (Exception e) {/*ignore*/}
        redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
        return "redirect:/sessions";
    }
}
