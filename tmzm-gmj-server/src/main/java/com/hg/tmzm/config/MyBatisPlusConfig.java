package com.hg.tmzm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus configuration
 *
 * @author hg
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * MybatisPlus interceptor with Oracle pagination support
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.ORACLE);
        paginationInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }

    /**
     * MetaObjectHandler for auto-filling audit fields
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "recCreateTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "recCreator", String.class, getOperator());
                this.strictInsertFill(metaObject, "recReviseTime", LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, "recRevisor", String.class, getOperator());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "recReviseTime", LocalDateTime.class, LocalDateTime.now());
                this.strictUpdateFill(metaObject, "recRevisor", String.class, getOperator());
            }

            /**
             * Get current operator name.
             * TODO: Replace with SecurityUtils.getUsername() when security is integrated.
             */
            private String getOperator() {
                return "system";
            }
        };
    }
}
