package com.mja;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;

public class SimpleBlogApp extends NanoHTTPD {

    RequestUrlMapper requestUrlMapper = new RequestUrlMapper();

    public SimpleBlogApp(int port) throws IOException {
        super(port);
        start(5000, false);
        System.out.println("Server has been started");
    }

    public static void main(String[] args) {
        try {
            new SimpleBlogApp(8080);
        } catch (IOException e) {
            System.err.println("Server can't started becouse of error: \n" + e);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        return requestUrlMapper.delegateRequest(session);
    }
}
