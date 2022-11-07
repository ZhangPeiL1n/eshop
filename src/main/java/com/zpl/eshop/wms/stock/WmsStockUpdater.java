package com.zpl.eshop.wms.stock;

/**
 * wms中心库存坑新接口
 *
 * @author ZhangPeiL1n
 * @date 2022/10/7 19:39
 **/
public interface WmsStockUpdater {

    /**
     * 更新商品库存
     *
     * @return 处理结果
     * @throws Exception
     */
    Boolean update() throws Exception;

    /**
     * 设置这个更新组件需要的参数
     *
     * @param parameter 参数
     * @throws Exception
     */
    void setParameter(Object parameter) throws Exception;

}
