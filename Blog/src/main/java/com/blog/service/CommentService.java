package com.blog.service;

import com.blog.shared.dto.CommentDto;
import com.blog.shared.dto.ReplieDto;

public interface CommentService {
	public CommentDto createComment(CommentDto comment);
	public CommentDto updateComment(long id, CommentDto comment);
	public CommentDto getComment(long Id);
	public void deleteComment(long id);
	
	public ReplieDto createReplie(ReplieDto replie);
	public CommentDto updateReplie(long id, CommentDto replie);
	public CommentDto getReplie(long id);
	public void deleteReplie(long id);
}
