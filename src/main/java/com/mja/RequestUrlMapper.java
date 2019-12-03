package com.mja;

import com.mja.controller.BlogPostController;
import fi.iki.elonen.NanoHTTPD;

import static fi.iki.elonen.NanoHTTPD.Method.GET;
import static fi.iki.elonen.NanoHTTPD.Method.POST;
import static fi.iki.elonen.NanoHTTPD.Response.Status.NOT_FOUND;

public class RequestUrlMapper {

    private final static String ADD_BOOK_URL = "/blog/add";
    private final static String GET_BOOK_URL = "/blog/get";
    private final static String GET_ALL_BOOK_URL = "/blog/getAll";

    BlogPostController blogPostController = new BlogPostController();

    public NanoHTTPD.Response delegateRequest(NanoHTTPD.IHTTPSession session) {
        if (GET.equals(session.getMethod()) && GET_BOOK_URL.equals(session.getUri())) {

            return blogPostController.serveAddBlogPostRequest(session);
        }
        else if (GET.equals(session.getMethod()) && GET_ALL_BOOK_URL.equals(session.getUri())) {

            return blogPostController.serveGetAllBlogPostRequest(session);
        }
        else if (POST.equals(session.getMethod()) && ADD_BOOK_URL.equals(session.getUri())) {

            return blogPostController.serveAddBlogPostRequest(session);
        }

        return NanoHTTPD.newFixedLengthResponse(NOT_FOUND, "text/plain", "Not Found");
    }
}
