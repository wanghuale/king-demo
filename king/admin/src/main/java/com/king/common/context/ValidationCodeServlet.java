package com.king.common.context;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.redis.service.JedisClient;
import com.king.utils.RandonCode;

@Service
public class ValidationCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 5126616339795936447L;

    public static final String VALIDATION_CODE="validationCode";

    public static final String VALIDATION_NAME="captcha";

    public ValidationCodeServlet() {
        super();
    }

    @Autowired
    JedisClient jedisClient;

    /**
     * Servlet销毁方法,负责销毁所使用资源.
     */

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RandonCode img = new RandonCode(4, RandonCode.CaptchaType.www);
    	HttpSession session = request.getSession(true);
        String code = img.getMd5RandonCode();
        session.setAttribute(ValidationCodeServlet.VALIDATION_CODE,code);
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(img.render(), "jpeg",sos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (sos != null)
                try {sos.close();} catch (IOException e) {e.printStackTrace();}
        }
    }

    public Boolean isCaptcha(HttpServletRequest request){
        if (request.getParameterMap().containsKey(ValidationCodeServlet.VALIDATION_NAME)) {
            String captcha = request.getParameter(ValidationCodeServlet.VALIDATION_NAME);
            HttpSession session = request.getSession(true);
            String validationCode = (String) session.getAttribute(ValidationCodeServlet.VALIDATION_CODE);
            //String validationCode = jedisClient.get(ValidationCodeServlet.VALIDATION_CODE+session.getId());
            if (!RandonCode.validate(validationCode,captcha)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Servlet初始化方法
     */

    public void init() throws ServletException {
    }



}    
