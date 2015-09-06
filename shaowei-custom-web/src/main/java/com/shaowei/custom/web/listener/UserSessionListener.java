package com.shaowei.custom.web.listener;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;

public class UserSessionListener extends HttpSessionEventPublisher {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);

		/** 用户Session失效操作 **/
	}
}
