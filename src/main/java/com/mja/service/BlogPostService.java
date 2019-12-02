package com.mja.service;

import com.mja.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost getBlogPost(long id);

    List<BlogPost> getAllBlogPosts();

    void addBlogPost(BlogPost blogPost);

}
