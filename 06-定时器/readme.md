[TOC]
# 定时器
该内容相当重要，例如定时执行某个事件而不通过事件监听实现，而是通过时间(MC内的时间是20tick为1秒).

## 方法继承(第一种)
```
class xxx extends BukkitRunnable
{
    @Override
    public void run()
}
```
通过这个办法重写run方法来实现定时器

## 匿名函数(第二种)

new BukkitRunnable() {

    @Override
    public void run() {
		//TODO
	}
}.runTaskTimer(this,0,1*20L);