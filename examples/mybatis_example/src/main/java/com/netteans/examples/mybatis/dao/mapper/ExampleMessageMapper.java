package com.netteans.examples.mybatis.dao.mapper;

import com.netteans.examples.mybatis.dao.model.ExampleMessage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ExampleMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    @Delete({
        "delete from example_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    @Insert({
        "insert into example_message (type, message_id, ",
        "message_body, version)",
        "values (#{type,jdbcType=SMALLINT}, #{messageId,jdbcType=VARCHAR}, ",
        "#{messageBody,jdbcType=VARCHAR}, #{version,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ExampleMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    int insertSelective(ExampleMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, type, message_id, message_body, version",
        "from example_message",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.netteans.examples.mybatis.dao.mapper.ExampleMessageMapper.BaseResultMap")
    ExampleMessage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExampleMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_message
     *
     * @mbg.generated
     */
    @Update({
        "update example_message",
        "set type = #{type,jdbcType=SMALLINT},",
          "message_id = #{messageId,jdbcType=VARCHAR},",
          "message_body = #{messageBody,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ExampleMessage record);
}