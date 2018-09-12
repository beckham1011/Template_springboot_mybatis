package cn.bjjoy.bms.setting.dao;

import cn.bjjoy.bms.setting.entity.CamacAnswer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CamacAnswerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camac_answer
     *
     * @mbggenerated Wed Sep 12 15:34:16 CST 2018
     */
    int deleteByPrimaryKey(Long answerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camac_answer
     *
     * @mbggenerated Wed Sep 12 15:34:16 CST 2018
     */
    int insert(CamacAnswer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camac_answer
     *
     * @mbggenerated Wed Sep 12 15:34:16 CST 2018
     */
    CamacAnswer selectByPrimaryKey(Long answerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camac_answer
     *
     * @mbggenerated Wed Sep 12 15:34:16 CST 2018
     */
    List<CamacAnswer> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camac_answer
     *
     * @mbggenerated Wed Sep 12 15:34:16 CST 2018
     */
    int updateByPrimaryKey(CamacAnswer record);
}