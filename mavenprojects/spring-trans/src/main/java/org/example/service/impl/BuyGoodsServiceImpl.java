package org.example.service.impl;

import org.example.dao.GoodsDao;
import org.example.dao.SaleDao;
import org.example.domain.Goods;
import org.example.domain.Sale;
import org.example.excep.NotEnoughException;
import org.example.service.BuyGoodsService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BuyGoodsServiceImpl implements BuyGoodsService {
    private SaleDao saleDao;
    private GoodsDao goodsDao;

    /**
     *rollbackFor:表示发生指定的异常一定回滚。
     *   处理逻辑：1）spring框架会首先检查方法抛出的异常是不是在rollbackFor的属性值中
     *                如果异常在rollbackFor列表中，不管是什么类型的异常，一定回滚
     *           2）如果你抛出的异常不在rollbackFor列表中，spring会判断异常是不是RuntimeException,
     *             如果是一定回滚
     */
    /*@Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = {
                    NullPointerException.class,NotEnoughException.class
            }
    )      上面注解括号中的都是默认的设置*/
    @Transactional
    @Override
    public void buy(Integer goodsId, Integer nums) {
        System.out.println("======buy方法开始");
        //记录销售信息，向sale表添加记录
        Sale sale=new Sale(goodsId,nums);
        saleDao.insertSale(sale);

        //更新库存
        Goods goods= goodsDao.selectGoods(goodsId);
        if (goods==null){
            //商品不存在
            throw new NullPointerException("编号是"+goodsId+"商品不参在");
        }else if (goods.getAmount()<nums){
            //商品库存不足
            throw new NotEnoughException("编号是"+goodsId+"商品库存不足");
        }
        //修改库存了
        Goods buyGoods=new Goods();
        buyGoods.setId(goodsId);
        buyGoods.setAmount(nums);
        goodsDao.updateGoods(buyGoods);
        System.out.println("=================buy方法结束");
    }

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }
}
