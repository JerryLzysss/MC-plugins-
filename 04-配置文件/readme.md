[TOC]
# 配置文件

## config.yml
如果存在这个文件，Bukkit就会为这个插件创建配置文件

## 引入config

通过yml格式，相当于建立一个映射关系，作用则是用来控制参数，例如部分插件中通过config对效果进行控制。
```
int a=getConfig().getint("age");
String b=getConfig().getString("name");
```



