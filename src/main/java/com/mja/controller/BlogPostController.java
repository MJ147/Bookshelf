package com.mja.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mja.model.BlogPost;
import com.mja.service.BlogPostService;
import com.mja.service.impl.BlogPostServiceImpl;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static fi.iki.elonen.NanoHTTPD.Response.Status.*;
import static fi.iki.elonen.NanoHTTPD.newFixedLengthResponse;

public class BlogPostController {

    private final static String POST_ID_PARAM_NAME = "postId";

    private BlogPostService blogPostService= new BlogPostServiceImpl();

    public Response serveGetBlogPostRequest(IHTTPSession session) {

        Map<String, List<String>> requestParameters = session.getParameters();
        if (requestParameters.containsKey(POST_ID_PARAM_NAME)) {
            List<String> postIdParams = requestParameters.get(POST_ID_PARAM_NAME);
            String postIdParam = postIdParams.get(0);
            long postId = 0;
            try {
                postId = Long.parseLong(postIdParam);
            } catch (NumberFormatException e) {
                System.err.println("Error during convert request param: \n" + e);
                return newFixedLengthResponse(BAD_REQUEST, "text/plain", "Request param 'postId' have to be a number");
            }

            BlogPost blogPost = blogPostService.getBlogPost(postId);
            if (blogPost != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String response = objectMapper.writeValueAsString(blogPost);
                    return newFixedLengthResponse(OK, "application/json", response);
                } catch (JsonProcessingException e) {
                    System.err.println("Error during convert request param: \n" + e);
                    return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error can't read all book");
                }
            }
            return newFixedLengthResponse(NOT_FOUND, "application/json", "");
        }
        return newFixedLengthResponse(BAD_REQUEST, "text/plain", "Uncorrected request params");
    }
    public Response serveGetAllBlogPostsRequest(IHTTPSession session) {

        ObjectMapper objectMapper = new ObjectMapper();
        String response = "";


        try {
            response = objectMapper.writeValueAsString(blogPostService.getAllBlogPosts());
        } catch (JsonProcessingException e) {
            System.err.println("Error during process request: \n" + e);
            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error chan't read all book");
        }

        return newFixedLengthResponse(OK, "application/json", response);
    }
    public Response serveAddBlogPostRequest(IHTTPSession session) {

        ObjectMapper objectMapper = new ObjectMapper();
        long randomBlogPostId = System.currentTimeMillis();
        String randomBlogPostDate = LocalDate.now().toString();

        String lengthHeader = session.getHeaders().get("content-length");
        int contentLength = Integer.parseInt(lengthHeader);
        byte[] buffer = new byte[contentLength];

        try {
            session.getInputStream().read(buffer, 0, contentLength);
            String requestBody = new String(buffer).trim();
            BlogPost requestBlogPost = objectMapper.readValue(requestBody, BlogPost.class);
            requestBlogPost.setId(randomBlogPostId);
            requestBlogPost.setDateOfPosted(randomBlogPostDate);

            blogPostService.addBlogPost(requestBlogPost);
        } catch (Exception e) {
            System.err.println("Error during process request: \n" + e);
            return newFixedLengthResponse(INTERNAL_ERROR, "text/plain", "Internal error book hasn't been added");
        }

        return newFixedLengthResponse(OK, "text/plain", "Post has been successfully added, id = " + randomBlogPostId);
    }
}
