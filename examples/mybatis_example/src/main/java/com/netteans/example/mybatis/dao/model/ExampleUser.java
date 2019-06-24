package com.netteans.example.mybatis.dao.model;

public class ExampleUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbggenerated
     */
    public ExampleUser(Integer id, String name, String password, String salt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.id
     *
     * @return the value of example_user.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.name
     *
     * @return the value of example_user.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.password
     *
     * @return the value of example_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.salt
     *
     * @return the value of example_user.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }
}