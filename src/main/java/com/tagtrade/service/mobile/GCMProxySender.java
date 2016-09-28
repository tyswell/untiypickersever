package com.tagtrade.service.mobile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.google.android.gcm.server.Sender;

public class GCMProxySender extends Sender {

	public GCMProxySender(String key) {
		super(key);
	}

	@Override
	protected HttpURLConnection getConnection(String url) throws IOException {

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
				"192.168.5.24", 3128));

		HttpURLConnection conn = (HttpURLConnection) new URL(url)
				.openConnection(proxy);

		return conn;

	}
}