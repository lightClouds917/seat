## seat  branch 1.0
整合了SpringCloud，order通过feign调用account,storage两个服务
### 正常状态下：
```java
    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public void create(Order order) {
        //本地方法
        orderDao.create(order);
        //远程方法
        storageApi.decrease(order.getProductId(),order.getCount());
        accountApi.decrease(order.getUserId(),order.getMoney());
    }
```
此时事务是成功的；
### 问题状态下：
```java
    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public void create(Order order) {
        //本地方法
        orderDao.create(order);
        //远程方法 扣减库存
        storageApi.decrease(order.getProductId(),order.getCount());

        //模拟远程调用出错，或者直接在account服务decrease方法中来模拟也可以
        System.out.println(5/0);
        //远程方法 扣减账户余额
        accountApi.decrease(order.getUserId(),order.getMoney());
    }
```
此时，会出现数据不一致；
