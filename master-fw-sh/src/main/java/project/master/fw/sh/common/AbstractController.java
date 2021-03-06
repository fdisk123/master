package project.master.fw.sh.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController extends ObjectFill {
	protected final Map<String, Object> getParams(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		String name;
		while (en.hasMoreElements()) {
			name = en.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}

	protected Map<String, Object> checkPageAndSize(HttpServletRequest request) {
		Map<String, Object> map = getParams(request);
		if (!map.containsKey("page")) {
			map.put("page", -1);
			map.put("size", -1);
		}
		return map;
	}

	protected JsonMsgModel fail(String msg, Throwable e) {
		if (null != e)
			e.printStackTrace();
		return new JsonMsgModel(-1, msg);
	}

	protected JsonMsgModel fail(Throwable e) {
		e.printStackTrace();
		return new JsonMsgModel(-1, "操作失败");
	}

	protected JsonMsgModel fail() {
		return new JsonMsgModel(-1);
	}

	protected JsonMsgModel success(String msg, Object data, Object attachment) {
		return new JsonMsgModel(1, msg, data, attachment);
	}

	protected JsonMsgModel success(Object data) {
		return success(null, data, null);
	}

	protected JsonMsgModel success() {
		return success(null, null, null);
	}

	protected ModelAndView exceptionPage(Throwable e) {
		return exceptionPage("操作失败", e);
	}

	protected ModelAndView exceptionPage(String msg, Throwable e) {
		if (null != e)
			e.printStackTrace();
		return new ModelAndView("forward:/error");
	}
}
