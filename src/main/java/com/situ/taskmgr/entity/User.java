package com.situ.taskmgr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author kwd
 * 1.类名和数据库名一致
 * 2.实体类的属性要与数据表的字段保持一致
 * 		1)名字要一致 各自遵循命名规范
 * 		2）数据类型要匹配 不要使用基本数据类型 而是使用java中的包装类integer
 * 3.封装实体类
   * 所有的属性私有化
   * 提供所有属性的get set方法@Data 打印@ToString
 *
 */

@Data
@ToString
@NoArgsConstructor
public class User {
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String realname;
	private Integer role;
	private Integer status;
}
