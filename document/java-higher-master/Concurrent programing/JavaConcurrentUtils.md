## 线程安全

### 什么是线程安全？

直观的，多个线程访问一个类时，这个类始终都能表现出正确的行为，那么这个类就是线程安全的。

### 锁，Java中的锁都是对象锁。

- 普通方法上的锁，锁的是对象
- 静态方法上的锁，锁的是类的字节码对象
- 代码块上的锁，锁的是括号中的对象

## 多个对象多个锁

Java的锁是对象锁，所以如果多个线程访问的是互不相同的对象，那么也是互不干扰的。

## 对象锁的同步和异步

同步：同步的概念关键是 ** 共享 ** ,如果不是共享的资源，就没必要进行同步。

异步：异步的概念就是 ** 独立 ** ，相互之间不受任何制约。

同步的目的是为了线程安全，对于线程安全，需要满足两个条件：

- 原子性(同步)
- 可见性

## 脏读

设计应用程序的时候，一定要考虑业务的整体性，业务整体枷锁。否则数据不一致会产生脏读。

ACID，指数据库事务正确执行的四个基本要素的缩写。包含：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）。一个支持事务（Transaction）的数据库，必需要具有这四种特性，否则在事务过程（Transaction processing）当中无法保证数据的正确性，交易过程极可能达不到交易方的要求。
目前主要有两种方式实现ACID：第一种是Write ahead logging，也就是日志式的方式(现代数据库均基于这种方式)。第二种是Shadow paging。Oracle一致性读，UNDO

## synchronized
synchronized拥有锁重入功能，也就是在使用synchronized时，当一个线程得到了一个对象的锁后，再次请求此对象时是可以再次得到该对象的锁。

## 线程间通信
使用wait/notify方法实现线程间的通信。(这两个方法都是object类的方法，所有java对象都提供这两个方法。)
1. wait和notify必须配合synchronized关键字使用
2. wait方法释放锁，notify方法不释放锁

## 单例
- double check
- static inner class

## Concurrent & CopyOnWrite
- ConcurrentHashmap  减小锁的粒度，降低锁竞争
- CopyOnWrite  适用于读写分离，读多写少

## Queue

- ConcurrentLinkedQueue  高性能非阻塞无界队列

- ArrayBlockingQueue  阻塞有界队列

- LinkedBlockingQueue 阻塞无界队列

- SynchronousQueue 缓冲队列，直接消费数据

- PriorityBlockingQueue 采用公平锁，无界队列

- DelayQueue 带有延迟的队列，其中的元素只有当其指定的延迟时间到了，才能从队列获取该元素，元素必须实现Delay接口。无界队列。应用：缓存超时的数据进行移除、任务超时处理、空闲连接的关闭。

### 队列重要方法

- put 调用此方法的线程如果发现队列没有空间，会阻塞直到有空间继续

- take 取走阻塞队列首位数据，若为空，阻塞进入等待直到有新数据加入。

- drainTo 一次性从阻塞队列获取所有可用个数的数据对象，可以提升获取效率，无需分批加锁释放锁。

- Deque 双端队列 允许在队列的头部或者尾部进行出队和入队操作 LinkedBlockingDeque  高并发场景下性能不高

## 多线程的设计模式
- Future模式
- Master-Worker模式
- 生产者消费者模式

## Executor框架
- newFixedThreadPool  固定线程数的线程池，任务提交时，如果没有空闲线程需要在任务队列中等待
- newSingleThreadExecutor 一个线程的线程池
- newCachedThreadPool 不限制线程数量，无任务不创建线程，空线程60秒后会自动回收
- newScheduledThreadPool 返回ScheduledExecutorService对象，可以指定线程数量，任务调度功能?

底层都是ThreadPoolExecutor

## Concurrent Utils
- CyclicBarrier
  多个线程在某个情况等待都达到，然后再一起执行
- CountDownLatch
  监听某些初始化操作，等待初始化执行完毕后，通知主线程继续工作。一个线程等待其他线程完成之后，得到通知然后执行
- Callable和Future使用。适合执行耗时很长的业务逻辑使用，可以有效减少系统的响应时间，提高系统的吞吐量。
- Semaphore 可以控制系统的流量，获取许可，释放许可。同时有几个线程可以访问资源

互联网应用常用性能指标：

  - PV(page view)网站的总访问量，用户每刷新一次会被记录为一次访问

  - UV(unique visitor)访问用户量，一天相同ip只记录一次

  - QPS(query per second)每秒查询数

  - RT(response time)请求响应时间

高并发的几个点

- 网络(限流)
- 服务

  按业务模块化

  每个模块做负载均衡分流。
- Java 限流

  Semaphore

  Redis

  静态资源和动态资源分离

容量评估：

```
  峰值qps=(总PV * 80%)/(60*60*24*20%)

  机器数量=总峰值qps/压力测试得出的单机极限qps
```
  >秒杀系统的实现：

  > 秒杀系统是一个单独的模块，独立于原有业务系统，使用redis缓存

## 锁
Lock对象

### 重入锁, ReentrantLock

 > 有嗅探锁定、多路分支等功能

 > java8的synchronized关键字也有重入功能，性能和 ReentrantLock并无较大差异

```
try{
  lock.lock();

  condition.await();//释放锁
}
finally {
	lock.unlock();
}

```

```
try{
  lock.lock();

  condition.signalAll();
}
finally {
	lock.unlock();
}

```
锁的嗅探 tryLock()

### 读写锁, ReentrantReadWriteLock
核心是实现了读写分离的锁，适合读多写少的场景，否则使用重入锁性能更高

读写锁，本质分成两个锁，读锁和写锁，读锁的情况下，多个线程可以并发的进行访问，写的时候，只能一个个的顺序访问

口诀：读读共享，写写互斥，读写互斥

## 锁优化
- 避免死锁
- 减小锁的持有时间
- 减小锁的粒度
- 锁的读写分离
- 尽量使用无锁的操作，如原子操作(Atomic系列类)，volatile关键字

## 分布式锁
  > 两个jvm上的操作如何保证原子性？

 > Zookeeper,线程访问资源之前，去Zookeeper上注册，由Zookeeper管理
