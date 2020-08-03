package com.netteans.example.multiple.ds.anno;

/**
 * @author netteans
 */
public @interface DataSource {
    boolean isPrimary() default true;

    String driverClassName() default "com.mysql.jdbc.Driver";

    String url() default "jdbc:mysql://localhost:3306/mybatis_example?useUnicode=true&characterEncoding=utf-8";

    String username() default "root";

    String password() default "1234";
}
