package com.zpl.eshop.inventory.command;

/**
 * 商品库存更新命令接口
 *
 * @author ZhangPeiL1n
 * @date 2022/1/24 22:29
 **/
public interface GoodsStockUpdateCommand {
    /**
     * 更新商品库存
     *
     * @return 更新成功返回true
     */
    Boolean updateGoodsStock();
}
