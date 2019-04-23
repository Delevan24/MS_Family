package com.bjsxt.base.sync005;
/**
 * synchronized异常
 * 1 将异常记录日志，继续执行后续任务。这种操作是任务之间没有关联关系
 * 2 抛出异常，终止程序
 * @author alienware
 *
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 10){
					Integer.parseInt("a");
//					throw new RuntimeException();
				}
			}catch(Exception e){
			    e.printStackTrace();
			    //出现
			    System.out.println(" write log");
				throw new RuntimeException();
			}
		}
	}
	
	public static void main(String[] args) {
		
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
	
	
}
