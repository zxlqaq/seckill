package com.zxl.support.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxl.support.entity.ActivityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActivityMapper extends BaseMapper<ActivityInfo> {

    /**
     * 查询活动(最近的一场)
     * @param productId
     * @return
     */
    ActivityInfo selectByProductId(@Param("productId") String productId);

    /**
     * 查询活动(按条件)
     * @param productId
     * @return
     */
    ActivityInfo selectByCondition(@Param("productId") String productId,@Param("status") Integer status);

    /**
     * 更新活动状态
     * @param
     * @return
     */
    int updateStatus(@Param("id") Long id,@Param("status") Integer status);

    /**
     * 更新库存
     * @param
     * @return
     */
    int updateStockNum(@Param("id") Long id,@Param("buyNum") Integer buyNum);

}
