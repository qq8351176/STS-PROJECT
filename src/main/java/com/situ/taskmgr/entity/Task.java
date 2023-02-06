package com.situ.taskmgr.entity;

import java.util.Date;
import java.util.List;

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
	private Date createTime;
	private Date start;
	private Date end;
	private Integer status;
	private Integer createId;
	
	//添加一个任务与用户的关联
	private List<Join> joins;
	
}
