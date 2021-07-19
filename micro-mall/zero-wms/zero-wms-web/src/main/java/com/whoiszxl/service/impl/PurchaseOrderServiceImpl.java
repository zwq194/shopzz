package com.whoiszxl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.whoiszxl.dto.PurchaseOrderDTO;
import com.whoiszxl.dto.PurchaseOrderItemDTO;
import com.whoiszxl.entity.PurchaseOrder;
import com.whoiszxl.entity.PurchaseOrderItem;
import com.whoiszxl.enums.PurchaseOrderStatusEnum;
import com.whoiszxl.exception.ExceptionCatcher;
import com.whoiszxl.mapper.PurchaseOrderMapper;
import com.whoiszxl.service.PurchaseOrderItemService;
import com.whoiszxl.service.PurchaseOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whoiszxl.utils.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 采购订单表 服务实现类
 * </p>
 *
 * @author whoiszxl
 * @since 2021-07-19
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;


    @Transactional
    @Override
    public boolean savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        //1. 新增采购订单
        purchaseOrderDTO.setPurchaseOrderStatus(PurchaseOrderStatusEnum.EDITING.getCode());
        PurchaseOrder purchaseOrder = purchaseOrderDTO.clone(PurchaseOrder.class);
        boolean orderFlag = this.save(purchaseOrder);

        //2. 新增采购订单中的商品详情
        List<PurchaseOrderItemDTO> items = purchaseOrderDTO.getItems();
        items.forEach(item -> item.setPurchaseOrderId(purchaseOrder.getId()));
        List<PurchaseOrderItem> purchaseOrderItems = BeanCopierUtils.copyListProperties(items, PurchaseOrderItem::new);
        boolean itemFlag = purchaseOrderItemService.saveBatch(purchaseOrderItems);

        return orderFlag && itemFlag;
    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        //1. 查询采购订单
        PurchaseOrder purchaseOrder = this.getById(id);
        PurchaseOrderDTO purchaseOrderDTO = purchaseOrder.clone(PurchaseOrderDTO.class);

        //2. 查询采购订单中的商品详情
        List<PurchaseOrderItem> purchaseOrderItemList = purchaseOrderItemService.list(new QueryWrapper<PurchaseOrderItem>()
                .eq("purchase_order_id", id));
        List<PurchaseOrderItemDTO> purchaseOrderItemDTOList
                = BeanCopierUtils.copyListProperties(purchaseOrderItemList, PurchaseOrderItemDTO::new);

        //3. 将详情添加到采购订单对象中去
        purchaseOrderDTO.setItems(purchaseOrderItemDTOList);

        return purchaseOrderDTO;
    }

    @Transactional
    @Override
    public Boolean updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        //1. 更新采购订单
        purchaseOrderDTO.setPurchaseOrderStatus(PurchaseOrderStatusEnum.EDITING.getCode());
        PurchaseOrder purchaseOrder = purchaseOrderDTO.clone(PurchaseOrder.class);
        boolean orderFlag = this.updateById(purchaseOrder);

        //2. 删除原来的订单商品详情
        boolean removeFlag = purchaseOrderItemService.remove(new UpdateWrapper<PurchaseOrderItem>()
                .eq("purchase_order_id", purchaseOrderDTO.getId()));

        //2. 新增订单商品详情
        List<PurchaseOrderItemDTO> items = purchaseOrderDTO.getItems();
        items.forEach(item -> item.setPurchaseOrderId(purchaseOrder.getId()));
        List<PurchaseOrderItem> purchaseOrderItems = BeanCopierUtils.copyListProperties(items, PurchaseOrderItem::new);
        boolean itemFlag = purchaseOrderItemService.saveBatch(purchaseOrderItems);

        if(!orderFlag || !removeFlag || !itemFlag) {
            ExceptionCatcher.catchDatabaseFailEx();
        }
        return true;
    }

    @Transactional
    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<PurchaseOrder> updateWrapper = new UpdateWrapper<PurchaseOrder>();
        updateWrapper.eq("id", id);
        updateWrapper.set("purchase_order_status", status);
        return this.update(null, updateWrapper);
    }
}
