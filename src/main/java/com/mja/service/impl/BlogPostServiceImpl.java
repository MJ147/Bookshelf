package com.mja.service.impl;

import com.mja.model.BlogPost;
import com.mja.service.BlogPostService;

import java.util.ArrayList;
import java.util.List;

public class BlogPostServiceImpl implements BlogPostService {

    private static List<BlogPost> blogPostStorage = new ArrayList<BlogPost>();

    public BlogPost getBlogPost(long id) {
        for (BlogPost blogPost : blogPostStorage) {
            if (blogPost.getId() == id) {
                return blogPost;
            }
        }
        return null;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostStorage;
    }

    public void addBlogPost(BlogPost blogPost) {
        blogPostStorage.add(blogPost);
    }
}
