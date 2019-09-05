# springcloud-eureka-mybatis-seata
### 1.版本信息
注册中心：eureka

持久层：mybatis

Springboot：2.1.7.RELEASE

Springcloud:Greenwich.SR2

jdk:1.8

### 2.demo概况
demo分为四个项目，单独启动。

- eureka:作为注册中心
- order:订单服务，用户下单后，会创建一个订单添加在order数据库，同时会扣减库存storage，扣减账户account;
- storage:库存服务，用户扣减库存；
- account:账户服务，用于扣减账户余额；

order服务关键代码如下：
```java
    @Override
    @GlobalTransactional
    public void create(Order order) {
        //本地方法 创建订单
        orderDao.create(order);
        //远程方法 扣减库存
        storageApi.decrease(order.getProductId(),order.getCount());

        //模拟远程调用出错，或者直接在account服务decrease方法中来模拟也可以
        System.out.println(5/0);
        //远程方法 扣减账户余额
        accountApi.decrease(order.getUserId(),order.getMoney());
    }
```
### 使用步骤
1.拉取代码 git clone xxxx;
2.


问题状态下，此时，会出现数据不一致；
1.添加本地事务：仅仅扣减库存；
2.不添加本地事务：创建订单，扣减库存；

### 使用
根据每个项目下的sql文件，创建数据库启动eureka,和各个项目，访问：http://localhost:8080/order/create?userId=1&productId=1&count=10&money=1000
