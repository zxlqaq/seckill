package com.zxl.support.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxl.support.entity.OrderRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRecordMapper extends BaseMapper<OrderRecord> {

    /**
     * 创建订单
     * @param orderRecord
     * @return
     */
    int insert(OrderRecord orderRecord);

    /**
     * 查询活动
     * @param
     * @return
     */
    OrderRecord selectByOrderId(String orderId);

    /**
     * 更新状态
     * @param
     * @return
     */
    int updateOrderStatus(@Param("orderId") String orderId,@Param("orderStatus") Integer orderStatus);

}
