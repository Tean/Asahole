package com.netteans.examples.mybatis.dao.mapper;

import com.netteans.examples.mybatis.dao.model.ExampleMessage;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ExampleMessageMapperExt extends ExampleMessageMapper{

    @Update({
            "update example_message",
            "set type = #{type,jdbcType=SMALLINT},",
            "message_id = #{messageId,jdbcType=VARCHAR},",
            "message_body = #{messageBody,jdbcType=VARCHAR},",
            "version = version+1",
            "where id = #{id,jdbcType=INTEGER}",
            "and version = #{version,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyIdem(ExampleMessage record);

    @Select({
            "select",
            "id, type, message_id, message_body",
            "from example_message",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.netteans.examples.mybatis.dao.mapper.ExampleUserMapper.BaseResultMap")
    ExampleMessage selectByPrimaryKey(Integer id);
}
