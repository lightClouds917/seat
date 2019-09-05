# springcloud-eureka-mybatis-seata
### 1.版本信息
注册中心：eureka

持久层：mybatis

Springboot：2.1.7.RELEASE

Springcloud:Greenwich.SR2

jdk:1.8 

seata:0.8

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
### 3.使用步骤
- 1.拉取demo代码 git clone xxxx;
- 2.下载seata server,https://github.com/seata/seata/releases;
- 3.执行每个项目下的建表语句，resource下xx.sql文件；
- 4.seata相关建表语句见下文说明；

### 4.配置信息修改
#### 1.seata-server中，/conf目录下，有两个配置文件：

1.file.conf 

里面有事务组配置，锁配置，事务日志存储等相关配置信息，由于此demo使用db存储事务信息，我们这里只修改store中的配置，其他的可以先保持默认值：
```java
## transaction log store
store {
  ## store mode: file、db
  mode = "db"   修改这里

  ## file store
  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    driver-class-name = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://116.62.62.26/seat-server"  修改这里
    user = "root"  修改这里
    password = "root"  修改这里
    min-conn = 1
    max-conn = 3
    global.table = "global_table"
    branch.table = "branch_table"
    lock-table = "lock_table"
    query-limit = 100
  }
}
```
由于我们使用db模式存储事务日志，所以，我们要创建三张表：global_table，branch_table，lock_table，建表sql在/conf/db_store.sql中；

由于存储undo_log是在业务库中，所以在每个业务库中，还要创建undo_log表，建表sql在/conf/db_undo_log.sql中。

2.registry.conf

registry{}中是注册中心相关配置，config{}中是配置中心相关配置。

我们这里用eureka作注册中心，所以，只用修改registry{}中的：
```java
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "eureka"  修改这里

  nacos {
    serverAddr = "localhost"
    namespace = "public"
    cluster = "default"
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"  修改这里
    application = "default"  
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = "0"
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}
```
其他的配置可以暂时使用默认值。

如果是在windows下启动seata-server，现在可以直接启动了：执行/bin/seata-server.bat


问题状态下，此时，会出现数据不一致；
1.添加本地事务：仅仅扣减库存；
2.不添加本地事务：创建订单，扣减库存；

### 使用
根据每个项目下的sql文件，创建数据库启动eureka,和各个项目，访问：http://localhost:8080/order/create?userId=1&productId=1&count=10&money=1000
