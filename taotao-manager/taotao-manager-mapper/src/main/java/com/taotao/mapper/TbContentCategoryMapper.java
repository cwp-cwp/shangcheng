package com.taotao.mapper;

import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int countByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int deleteByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int insert(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int insertSelective(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    TbContentCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int updateByExample(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int updateByPrimaryKeySelective(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbggenerated Sun Jan 20 16:20:44 CST 2019
     */
    int updateByPrimaryKey(TbContentCategory record);
}