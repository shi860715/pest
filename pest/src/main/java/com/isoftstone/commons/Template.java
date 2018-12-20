/**
 * Template.java 2016年12月23日
 * 
 * Copyright 2001-2016 iSoftStone All rights reserved.
 * iSoftStone PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.isoftstone.commons;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导入模板
 * 
 * @author ylmiaoa
 * @since 2016年12月23日
 *
 */
@Target({
        ElementType.TYPE, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Template {

    /**
     * 映射名称
     *
     * @author ylmiaoa
     * @return
     * @since 2016年12月23日
     */
    String value() default "";

    /**
     * 日期格式
     *
     * @author ylmiaoa
     * @return
     * @since 2017年1月12日
     */
    String dateFormate() default "yyyy/MM/dd";
}
