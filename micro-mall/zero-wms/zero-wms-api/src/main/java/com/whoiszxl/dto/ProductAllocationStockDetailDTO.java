package com.whoiszxl.dto;

import com.whoiszxl.bean.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 货位库存详情表
 * </p>
 *
 * @author whoiszxl
 * @since 2021-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductAllocationStockDetail对象", description="货位库存详情表")
public class ProductAllocationStockDetailDTO extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "货位ID")
    private Long productAllocationId;

    @ApiModelProperty(value = "商品SKU ID")
    private Long productSkuId;

    @ApiModelProperty(value = "商品的上架时间")
    private Date putOnTime;

    @ApiModelProperty(value = "商品的上架数量")
    private Integer putOnQuantity;

    @ApiModelProperty(value = "上架的商品当前还剩余的库存数量")
    private Integer currentStockQuantity;

    @ApiModelProperty(value = "锁定库存数量")
    private Integer lockedStockQuantity;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "逻辑删除 1: 已删除， 0: 未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建者")
    private String createdBy;

    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}