package it.vitalegi.workshifts.controller;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RestErrorController extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
		errorAttributes.put("locale", webRequest.getLocale().toString());
		errorAttributes.remove("error");
		errorAttributes.remove("trace");
		errorAttributes.remove("timestamp");
		errorAttributes.remove("path");
		return errorAttributes;
	}
}
