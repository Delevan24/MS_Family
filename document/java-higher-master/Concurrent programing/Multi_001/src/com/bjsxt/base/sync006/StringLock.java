package com.bjsxt.base.sync006;
/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 * 不要使用String常量加锁，下面的例子如果锁住的是字符串常量，其他线程永远不会获取到锁
 * @author alienware
 *
 */
public class StringLock {

	public void method() {
		//new String("字符串常量")
		synchronized (new String("字符串常量")) {
			try {
				while(true){
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
					Thread.sleep(1000);		
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final StringLock stringLock = new StringLock();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				stringLock.method();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				stringLock.method();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
