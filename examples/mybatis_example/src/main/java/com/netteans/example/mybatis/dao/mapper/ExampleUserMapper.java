package com.netteans.example.mybatis.dao.mapper;

import com.netteans.example.mybatis.dao.model.ExampleUser;
import com.netteans.example.mybatis.dao.model.ExampleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ExampleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    long countByExample(ExampleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    int deleteByExample(ExampleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    @Delete({
        "delete from example_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    @Insert({
        "insert into example_user (id, name, ",
        "password, salt)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR})"
    })
    int insert(ExampleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    int insertSelective(ExampleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    List<ExampleUser> selectByExample(ExampleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, password, salt",
        "from example_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.netteans.example.mybatis.dao.mapper.ExampleUserMapper.BaseResultMap")
    ExampleUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExampleUser record, @Param("example") ExampleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExampleUser record, @Param("example") ExampleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExampleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table example_user
     *
     * @mbg.generated
     */
    @Update({
        "update example_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ExampleUser record);
}