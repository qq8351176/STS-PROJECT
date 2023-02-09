package com.situ.taskmgr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Join {
	private Integer id;
	private Integer type;
	private Integer taskId;
	private Integer userId;
}
