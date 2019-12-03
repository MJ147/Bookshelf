package com.mja.controller;

import com.mja.service.BlogPostService;
import com.mja.service.impl.BlogPostServiceImpl;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public class BlogPostController {

    private BlogPostService blogPostService= new BlogPostServiceImpl();

    public Response serveGetBlogPostRequest(IHTTPSession session) {

        return null;
    }
    public Response serveGetAllBlogPostRequest(IHTTPSession session) {

        return null;
    }
    public Response serveAddBlogPostRequest(IHTTPSession session) {

        return null;
    }
}
