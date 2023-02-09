package com.situ.taskmgr.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Task {
	private Integer id;
	private String title;
	private String content;
	private String target;
	private String result;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date end;
	
	private Integer status;
	private Integer createrId;
	
	//添加一个任务与用户的关联
	private List<Join> joins;
	
	private User creater;
	
}
