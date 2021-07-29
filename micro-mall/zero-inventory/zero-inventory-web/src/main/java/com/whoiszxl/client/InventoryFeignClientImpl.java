package com.whoiszxl.client;

import com.whoiszxl.bean.ResponseResult;
import com.whoiszxl.dto.InventorySkuDTO;
import com.whoiszxl.dto.PurchaseInboundOrderDTO;
import com.whoiszxl.entity.ProductStock;
import com.whoiszxl.feign.InventoryFeignClient;
import com.whoiszxl.service.ProductStockService;
import com.whoiszxl.stock.PurchaseInboundStockUpdaterFactory;
import com.whoiszxl.stock.StockUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author whoiszxl
 * @date 2021/7/21
 */
@RestController
public class InventoryFeignClientImpl implements InventoryFeignClient {

    /**
     * 采购入库库存更新命令
     */
    @Autowired
    private PurchaseInboundStockUpdaterFactory<PurchaseInboundOrderDTO> purchaseInboundStockUpdaterFactory;

    @Autowired
    private ProductStockService productStockService;

    @Override
    public ResponseResult<Boolean> notifyPurchaseInboundFinished(@RequestBody PurchaseInboundOrderDTO purchaseInboundOrderDTO) {
        StockUpdater stockUpdater = purchaseInboundStockUpdaterFactory.create(purchaseInboundOrderDTO);
        stockUpdater.updateProductStock();
        return ResponseResult.buildSuccess();
    }

    @Override
    public ResponseResult<List<InventorySkuDTO>> getSaleStockQuantity(List<Long> skuIds) {
        List<InventorySkuDTO> results = new ArrayList<>();
        for (Long skuId : skuIds) {
            ProductStock productStock = productStockService.getProductStockBySkuId(skuId);
            if(productStock == null) {
                results.add(new InventorySkuDTO(skuId, 0));
            }else {
                results.add(new InventorySkuDTO(skuId, productStock.getSaleStockQuantity()));
            }
        }
        return ResponseResult.buildSuccess(results);
    }
}