package com.netteans.examples.mybatis.dao.model;

public class ExampleUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.salt
     *
     * @mbg.generated
     */
    private String salt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_user.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    public ExampleUser(Integer id, String name, String password, String salt, Integer version) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    public ExampleUser() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.id
     *
     * @return the value of example_user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_user.id
     *
     * @param id the value for example_user.id
     *
     * @mbg.generated
     */
    public ExampleUser setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.name
     *
     * @return the value of example_user.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_user.name
     *
     * @param name the value for example_user.name
     *
     * @mbg.generated
     */
    public ExampleUser setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.password
     *
     * @return the value of example_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_user.password
     *
     * @param password the value for example_user.password
     *
     * @mbg.generated
     */
    public ExampleUser setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.salt
     *
     * @return the value of example_user.salt
     *
     * @mbg.generated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_user.salt
     *
     * @param salt the value for example_user.salt
     *
     * @mbg.generated
     */
    public ExampleUser setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_user.version
     *
     * @return the value of example_user.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_user.version
     *
     * @param version the value for example_user.version
     *
     * @mbg.generated
     */
    public ExampleUser setVersion(Integer version) {
        this.version = version;
        return this;
    }
}