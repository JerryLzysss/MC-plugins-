[TOC]
## 消息

## 常用信息
```player.sendMessage()```

## 信息（SPIGOT）
```player.spigot().sendMessage(new TextComponent("HelloWorld"))```
### 注:此处由于本人使用的是BUKKIT服务端因此不支持SPIGOT的写法不提供详细函数写法.

## 文本事件(漏掉了)
可以分为ClickEvent和HoverEvent<br>
ClickEvent触发必定会是以下五种动作之一：
动作|解释
---|---
Action.OPEN_URL	|玩家点击后打开链接
Action.OPEN_FILE|	玩家点击后打开文件
Action.RUN_COMMAND|	玩家点击后执行指令
Action.SUGGEST_COMMAND|	玩家点击文字后在获取后面设置的value并复制到玩家聊天框中
Action.CHANGE_PAGE	|玩家点击后更改书本的页数
HoverEvent触发必定会是以下四种动作之一：
动作	|解释
---|---
Action.SHOW_TEXT|	玩家鼠标悬浮后显示一段文字
Action.SHOW_ACHIEVEMENT|	玩家鼠标悬浮后显示关于某种成就的介绍（在新版本中被弃用）
Action.SHOW_ITEM|	玩家鼠标悬浮后显示关于某种物品的介绍
Action.SHOW_ENTITY|	玩家鼠标悬浮后显示关于某种实体的介绍

## 命令补全
此处结合命令定义方式直接讲解

## 命令例子
```
@Override
public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player) {//是否为玩家
		if(args.length > 3) return null;//三个参数输入完了，不提示
		if(args.length == 0 || args.length == 1) return Collections.singletonList("请输入玩家名");//没有参数，或者已有一个参数
		if(args.length == 2) return Collections.singletonList("请输入奔跑时间");//以此类推
		return Collections.singletonList("是否飞行，仅限输入true或false");
	}
	return null;
}
```
<br>
此处就是通过args的方式对输入参数进行String补充。
通过args.length进行判断.
## 命令补全注册
```
Objects.requireNonNull(Bukkit.getPluginCommand("fastspeed")).setExecutor(new FastSpeed());
Objects.requireNonNull(Bukkit.getPluginCommand("fastspeed")).setTabCompleter(new FastSpeed());
```
TabCompleter表示的就是对命令补全的注册