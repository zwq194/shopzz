import {createRouter, createWebHashHistory} from 'vue-router'
import Index from '@/views/Index.vue'
import AddGood from '@/views/AddGood.vue'
import Login from '@/views/Login.vue'
import SysUser from '@/views/SysUser.vue'
import SysUserAdd from '@/views/SysUserAdd.vue'
import PurchaseOrder from '@/views/PurchaseOrder.vue'
import Supplier from '@/views/Supplier.vue'
import SupplierAdd from '@/views/SupplierAdd.vue'
import Warehouse from '@/views/Warehouse.vue'
import WarehouseAdd from '@/views/WarehouseAdd.vue'
import WarehouseShelf from '@/views/WarehouseShelf.vue'
import WarehouseShelfAdd from '@/views/WarehouseShelfAdd.vue'
import InboundReturnOrder from '@/views/InboundReturnOrder.vue'
import OutboundSellOrder from '@/views/OutboundSellOrder.vue'
import WarehouseSku from '@/views/WarehouseSku.vue'

import AttributeList from '@/views/product/AttributeList.vue'
import SpuList from '@/views/product/SpuList.vue'
import SkuList from '@/views/product/SkuList.vue'
import SpuAdd from '@/views/product/SpuAdd.vue'
import AttributeDetail from '@/views/product/AttributeDetail.vue'

import CategoryList from '@/views/product/CategoryList.vue'

import FileList from '@/views/system/FileList.vue'


import BannerList from '@/views/promotion/BannerList.vue'
import BannerAdd from '@/views/promotion/BannerAdd.vue'

import SeckillList from '@/views/promotion/SeckillList.vue'
import SeckillAdd from '@/views/promotion/SeckillAdd.vue'

import SeckillItemList from '@/views/promotion/SeckillItemList.vue'


import ColumnList from '@/views/promotion/ColumnList.vue'
import ColumnAdd from '@/views/promotion/ColumnAdd.vue'
import ColumnSpuList from '@/views/promotion/ColumnSpuList.vue'

import CouponList from '@/views/promotion/CouponList.vue'
import CouponAdd from '@/views/promotion/CouponAdd.vue'

import ActivityList from '@/views/promotion/ActivityList.vue'
import ActivityAdd from '@/views/promotion/ActivityAdd.vue'


import Software from "@/views/server/Software.vue";
import SoftwareAdd from "@/views/server/SoftwareAdd.vue";
import Server from "@/views/server/Server.vue";
import ServerAdd from "@/views/server/ServerAdd.vue";
import SoftwareConfig from "@/views/server/SoftwareConfig.vue";
import SoftwareConfigDetail from "@/views/server/SoftwareConfigDetail.vue";
import Script from "@/views/server/Script.vue";
import ScriptAdd from "@/views/server/ScriptAdd.vue";
import Init from "@/views/server/Init.vue";
import SoftwareConfigAdd from "@/views/server/SoftwareConfigAdd.vue";

const router = createRouter({
  history: createWebHashHistory(), // hash 模式
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/add',
      name: 'add',
      component: AddGood
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },

    {
      path: '/sysUser',
      name: 'SysUser',
      component: SysUser
    },

    {
      path: '/sysuser/add',
      name: 'SysUserAdd',
      component: SysUserAdd
    },



    {path: '/supplier', name: 'Supplier', component: Supplier},
    {path: '/supplier/add', name: 'SupplierAdd', component: SupplierAdd},

    {path: '/purchaseOrder', name: 'PurchaseOrder', component: PurchaseOrder},

    {path: '/warehouse', name: 'Warehouse', component: Warehouse},
    {path: '/warehouse/add', name: 'WarehouseAdd', component: WarehouseAdd},

    {path: '/warehouseShelf', name: 'WarehouseShelf', component: WarehouseShelf},
    {path: '/warehouseShelf/add', name: 'WarehouseShelfAdd', component: WarehouseShelfAdd},

    {path: '/inboundReturnOrder', name: 'InboundReturnOrder', component: InboundReturnOrder},
    {path: '/outboundSellOrder', name: 'OutboundSellOrder', component: OutboundSellOrder},

    {path: '/warehouseSku', name: 'WarehouseSku', component: WarehouseSku},




    {path: '/attributeList', name: 'AttributeList', component: AttributeList},
    {path: '/attributeDetail', name: 'AttributeDetail', component: AttributeDetail},

    {path: '/categoryList', name: 'CategoryList', component: CategoryList, children: [
      {path: '/categoryList2', name: 'CategoryList2', component: CategoryList},
      {path: '/categoryList3', name: 'CategoryList3', component: CategoryList}
    ]},

    {path: '/spuList', name: 'SpuList', component: SpuList},
    {path: '/spu/add', name: 'SpuAdd', component: SpuAdd},
    {path: '/skuList', name: 'SkuList', component: SkuList},


    {path: '/fileList', name: 'FileList', component: FileList},

    {path: '/bannerList', name: 'BannerList', component: BannerList},
    {path: '/banner/add', name: 'BannerAdd', component: BannerAdd},

    {path: '/columnList', name: 'ColumnList', component: ColumnList},
    {path: '/column/add', name: 'ColumnAdd', component: ColumnAdd},

    {path: '/column/spu/list', name: 'ColumnSpuList', component: ColumnSpuList},

    {path: '/couponList', name: 'CouponList', component: CouponList},
    {path: '/coupon/add', name: 'CouponAdd', component: CouponAdd},

    
    {path: '/activityList', name: 'ActivityList', component: ActivityList},
    {path: '/activity/add', name: 'ActivityAdd', component: ActivityAdd},

    {path: '/seckillList', name: 'SeckillList', component: SeckillList},
    {path: '/seckill/add', name: 'SeckillAdd', component: SeckillAdd},

    {path: '/seckillItemList', name: 'SeckillItemList', component: SeckillItemList},


    {path: '/init', name: 'init', component: Init},
    {path: '/software/list', name: 'software', component: Software},
    {path: '/software/add', name: 'softwareAdd', component: SoftwareAdd},
    {path: '/software/config', name: 'softwareConfig', component: SoftwareConfig},

    {path: '/softwareConfig/detail', name: 'softwareConfigDetail', component: SoftwareConfigDetail},
    {path: '/softwareConfig/add', name: 'softwareConfigAdd', component: SoftwareConfigAdd},

    {path: '/server/list', name: 'server', component: Server},
    {path: '/server/add', name: 'serverAdd', component: ServerAdd},

    {path: '/server/script', name: 'script', component: Script},
    {path: '/script/add', name: 'scriptAdd', component: ScriptAdd},

  ]
})

export default router