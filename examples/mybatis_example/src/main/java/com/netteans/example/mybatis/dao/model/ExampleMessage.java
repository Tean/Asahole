package com.netteans.examples.mybatis.dao.model;

public class ExampleMessage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_message.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_message.type
     *
     * @mbg.generated
     */
    private Short type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_message.message_id
     *
     * @mbg.generated
     */
    private String messageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_message.message_body
     *
     * @mbg.generated
     */
    private String messageBody;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column example_message.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_message.id
     *
     * @return the value of example_message.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_message.id
     *
     * @param id the value for example_message.id
     *
     * @mbg.generated
     */
    public ExampleMessage setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_message.type
     *
     * @return the value of example_message.type
     *
     * @mbg.generated
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_message.type
     *
     * @param type the value for example_message.type
     *
     * @mbg.generated
     */
    public ExampleMessage setType(Short type) {
        this.type = type;
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_message.message_id
     *
     * @return the value of example_message.message_id
     *
     * @mbg.generated
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_message.message_id
     *
     * @param messageId the value for example_message.message_id
     *
     * @mbg.generated
     */
    public ExampleMessage setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_message.message_body
     *
     * @return the value of example_message.message_body
     *
     * @mbg.generated
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_message.message_body
     *
     * @param messageBody the value for example_message.message_body
     *
     * @mbg.generated
     */
    public ExampleMessage setMessageBody(String messageBody) {
        this.messageBody = messageBody == null ? null : messageBody.trim();
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column example_message.version
     *
     * @return the value of example_message.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column example_message.version
     *
     * @param version the value for example_message.version
     *
     * @mbg.generated
     */
    public ExampleMessage setVersion(Integer version) {
        this.version = version;
        return this;
    }
}