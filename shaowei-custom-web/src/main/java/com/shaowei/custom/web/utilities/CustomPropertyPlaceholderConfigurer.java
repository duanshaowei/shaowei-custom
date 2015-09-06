package com.shaowei.custom.web.utilities;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.shaowei.custom.common.utilities.encode.DESCoder;
import com.shaowei.custom.common.utilities.encode.KeyContainer;
import com.shaowei.custom.common.utilities.encode.keyContainerImp;

/**
 * 加密外部服务配置，key长度需要大于8位
 * 
 * @author Shaowei Duan
 * @since 0.1
 */
public class CustomPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private KeyContainer keyContainer = new keyContainerImp();

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {

		String userName = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		if (null != userName && userName.length() > 0) {
			props.setProperty("jdbc.username", decodeBuffer(userName));
		}
		if (null != password && userName.length() > 0) {
			props.setProperty("jdbc.password", decodeBuffer(password));
		}
		super.processProperties(beanFactoryToProcess, props);
	}

	private String decodeBuffer(String plainText) {
		try {
			return new String(
					DESCoder.decrypt(plainText, keyContainer.getKey()));
		} catch (Exception e) {
			return null;
		}
	}

	public String encodeBuffer(String plainText, String key) {
		try {
			return new String(DESCoder.encrypt(plainText.getBytes(), key));
		} catch (Exception e) {
			return null;
		}
	}

	public KeyContainer getKeyContainer() {
		return keyContainer;
	}

	public void setKeyContainer(KeyContainer keyContainer) {
		this.keyContainer = keyContainer;
	}
}
