[TOC]
# 命令使用

## 命令注册
```
Bukkit.getPluginCommand("test").setExecutor(this); 该指令作用是将某个指令注册进入plugins
```

## 命令重载

```
 @Override
public boolean onCommand(CommandSender sender, Command command, String label, String[]args)

```
sender => 指令发起者(玩家，控制台)

command => 指令名称

label => 被执行命令的别名

args => 表示命令参数，可以用来判断参数数目

## 命令填入

## plugin.yml<br>
<注意空格><br>
commands:<br>
test:<br>
description: My First Command. 描述<br>
usage: /test 用法<br>
permission:xxx 执行所需权限<br>
permission-message: 你不能执行.
