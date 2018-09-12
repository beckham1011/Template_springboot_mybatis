package cn.bjjoy.bms.setting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.bjjoy.bms.util.JsonUtils;
import cn.bjjoy.bms.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger("controller");

    @Autowired
    public HttpSession session;

    @Autowired
    public HttpServletRequest request;
    
    public ModelMap modelMap;

    public HttpServletResponse response;
    
    public JSONObject json = JsonUtils.setSuccess();

    @ModelAttribute
    public void init(HttpServletResponse response, ModelMap modelMap) {
        this.response = response;
        this.modelMap = modelMap;
    }
    
    public JSONObject outSuccess() {
        JSONObject sJson = new JSONObject();
        sJson.put(JsonUtils.STATUS_STR, JsonUtils.SUCCESS_STR);
        sJson.put(JsonUtils.DESCRIPTION_STR, StringUtils.EMPTY);
        return sJson;
    }
    
    public JSONObject outSuccess(String msg) {
        JSONObject sJson = new JSONObject();
        sJson.put(JsonUtils.STATUS_STR, JsonUtils.SUCCESS_STR);
        sJson.put(JsonUtils.DESCRIPTION_STR, msg);
        return sJson;
    }
    
    public JSONObject outError() {
        JSONObject sJson = new JSONObject();
        sJson.put(JsonUtils.STATUS_STR, JsonUtils.ERROR_STR);
        sJson.put(JsonUtils.DESCRIPTION_STR, StringUtils.EMPTY);
        return sJson;
    }
    
    public JSONObject outError(String msg) {
        JSONObject sJson = new JSONObject();
        sJson.put(JsonUtils.STATUS_STR, JsonUtils.ERROR_STR);
        sJson.put(JsonUtils.DESCRIPTION_STR, msg);
        return sJson;
    }
    public String redirect(String str) {
        return "redirect:" + str;
    }

    public String forward(String str) {
        return "forward:" + str;
    }
    
    public Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
    
}