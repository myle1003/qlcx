package com.qlcx.web.controller.test;

import com.qlcx.common.constant.UserConstants;
import com.qlcx.common.core.controller.BaseController;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysPost;
import com.qlcx.system.service.ISysPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/post/")
public class PostController extends BaseController {
    @Autowired
    private ISysPostService postService;

    /**
     * Add new post
     * @param post
     * @return post
     */
    @PostMapping("/add-post")
    @ResponseBody
    public AjaxResult addPost(@Validated SysPost post) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return error("Add post" + post.getPostName() + " failed, post name already exists");
        } else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return error("Add post" + post.getPostName() + " failed, post code already exists");
        }
        post.setCreateBy(ShiroUtils.getLoginName());
        postService.insertPost(post);
        return AjaxResult.success("Add post successfully", postService.selectPostById(post.getPostId()));
    }

    /**
     * Update post
     * @param postId
     * @param post
     * @return post
     */
    @PutMapping("/update-post")
    @ResponseBody
    public AjaxResult updatePost(Long postId, @Validated SysPost post) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return error("Update post" + post.getPostName() + " failed, post name already exists");
        } else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return error("Update post" + post.getPostName() + " failed, post code already exists");
        }
        post.setUpdateBy(ShiroUtils.getLoginName());
        postService.updatePost(post);
        return AjaxResult.success("Update post succesfully", postService.selectPostById(post.getPostId()));
    }

    /**
     * Delete post
     * @param postIds
     * @return null
     */
    @DeleteMapping("/delete-post/")
    @ResponseBody
    public AjaxResult deletePost(String postIds) {
        try {
            postService.deletePostByIds(postIds);
            return AjaxResult.success("Delete post successfully");
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * Get post by id
     * @param postId
     * @return post
     */
    @GetMapping("/get-post/{postId}")
    @ResponseBody
    public AjaxResult getPost(@PathVariable("postId") Long postId){
        return AjaxResult.success("Get post successfully", postService.selectPostById(postId));
    }

    /**
     * Get post list
     * @param post
     * @return post list
     */
    @GetMapping("/get-post-list/")
    @ResponseBody
    public AjaxResult getPostList(SysPost post){
        return AjaxResult.success("Get post list successfully", postService.selectPostList(post));
    }

}