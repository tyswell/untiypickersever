package com.tagtrade.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.DefaultWebRequestor;
import com.restfb.Version;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class DefaultFacebookGraphClient extends DefaultFacebookClient {

    // Override default web requestor to add read timeout parameter
    public DefaultFacebookGraphClient(String accessToken, final Integer timeout, final String proxyHost, final Integer proxyPort, final Version version) {
        super(accessToken,
                new DefaultWebRequestor() {
                    @Override
                    protected void customizeConnection(HttpURLConnection connection) {
                        connection.setReadTimeout(timeout);
                    }

                    @Override
                    protected HttpURLConnection openConnection(URL url) throws IOException {
                        if (proxyHost != null && proxyPort != null) {
                            InetSocketAddress proxyLocation = new InetSocketAddress(proxyHost, proxyPort);
                            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyLocation);
                            return (HttpURLConnection) url.openConnection(proxy);
                        } else {
                            return (HttpURLConnection) url.openConnection();
                        }
                    }
                },
                new DefaultJsonMapper(),
                version);
    }

}