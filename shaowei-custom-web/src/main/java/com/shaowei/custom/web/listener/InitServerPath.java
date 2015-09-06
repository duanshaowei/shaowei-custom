package com.shaowei.custom.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import com.shaowei.custom.web.utilities.NetWorkAddressUtils;

public class InitServerPath implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		NetWorkAddressUtils.setServerPath(arg0.getServletRequest());

	}

}
