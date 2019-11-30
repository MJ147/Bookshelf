package com.mja;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;

public class BookshelfApp extends NanoHTTPD {

    public BookshelfApp(int port) throws IOException {
        super(port);
        start(5000, false);
        System.out.println("Server has been started");
    }

    public static void main(String[] args) {
        try {
            new BookshelfApp(8080);
        } catch (IOException e) {
            System.err.println("Server can't started becouse of error: \n" + e);
        }

    }
}
