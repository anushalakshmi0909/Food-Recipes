package com.example.foody.controller;

import com.example.foody.model.Recipe;
import com.example.foody.model.RecipeBlog;
import com.example.foody.service.RecipeBlogService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:3000")  // allow your React dev server
public class RecipeBlogController {

    private final RecipeBlogService svc;

    public RecipeBlogController(RecipeBlogService svc) {
        this.svc = svc;
    }

    /**
     * GET /api/blogs
     */
    @GetMapping
    public Page<RecipeBlog> getAllBlogs(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return svc.getAllBlogs(pageable);
    }

    /**
     * GET /api/blogs/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecipeBlog> getBlogById(@PathVariable Integer id) {
        RecipeBlog blog = svc.getBlogById(id);
        if (blog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blog);
    }

    /**
     * GET /api/blogs/recent-blog (Now fetches only 1 blog)
     */
    @GetMapping("/recent-blog")
    public ResponseEntity<RecipeBlog> getRecentBlog() {
        RecipeBlog blog = svc.getRecentBlog();
        if (blog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blog);
    }
    @GetMapping("/{id}/recipes")
    public Recipe getRecipeFromBlog(@PathVariable Integer id) {
        return svc.getRecipeFromBlog(id);
    }
}
