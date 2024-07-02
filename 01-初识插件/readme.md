[TOC]
# 初识插件
## 配置环境
### 第一步:安装并配置intellij idea
### 第二步:安装plugins
1.打开File/Settings/Plugins
2.搜索MineCraft Development
3.安装
### 第三步:新建项目
1.选择Minecraft
2.选择适配的plugin与project SDK
JAVA8 => 1.12?
Java17 =>1.18+?
3.写入GroupId与ArtifactedId以及version等信息
groupId：是公司项目组唯一的标识符，实际对应JAVA的包的结构，是main目录里java的目录结构。
artifactId：是项目组中的某模块的唯一的标识符，实际对应小项目的名称。
version：指定了myapp项目的当前版本，SNAPSHOT意为快照，其中1.0是版本号。说明该项目还处于开发中，是不稳定的版本。而衍生的有Release版本则代表稳定的版本。
### 第四步:添加lib与导出方式
1.打开file/project structure/Project Settings
2.在libraries里面导入服务器的js(使用接口)
引入方式:选择java(例如bukkit)
例如:使用的是bukkit 1.16.5,开服使用的jar是craftbukkit-1.16.5.jar
那么将这个jar拉入lib当中.
3.在Artifacts中添加jar(插件后缀)
3.1修改生成名字后勾选include in project build！！！
3.2将右侧的Available Elements拉到左边(也就是'xxx' compile output和服务器的jar).
4.此时构建build生成的jar位于out文件夹里的jar也就是使用的plugins
## API初步使用

### 主文件
onEnable与onDisable对应的是插件开启与插件关闭时候执行任务
原理:重写JavaPlugin里面的方法
Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);
作用:对Event监听器进行注册

### 其他类文件
@EventHandler 注解
表示:该方法是一个监听器，通过在主文件注册后进行使用
其余内容均为API引入
isOp():是否为OP的判断，返回boolean
setJoinMessage():PlayerJoinEvent实例上的一个方法,设置用户进入时的信息。

### plugins.yml
配置的重要文件
其对应的信息如下所示。
属性|必需|描述
|---|---|---|
main|是|主类
version|是|插件版本
name|是|插件名字
description|不是|介绍这个插件
author|不是|插件作者
authors|不是|多个插件作者
commands|不是|命令
permissions|不是|权限
depend|不是|依赖，比如这个插件依赖另外的插件
database|不是|数据库

### 颜色
(有些可能对应不上可以一个个尝试)
输入|颜色
|---|---|
|§0|黑色|
|§1|深蓝|
|§2|深绿|
|§3|天蓝|
|§4|红色|
|§5|深紫|
|§6|金黄|
|§7|浅灰|
|§8|深灰|
|§9|天蓝|
|§a|淡绿|
|§b|淡蓝|
|§c|淡红|
|§d|淡紫|
|§e|淡黄|
|§f|白色|
