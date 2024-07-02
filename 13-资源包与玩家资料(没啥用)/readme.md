[TOC]
## 玩家资料与资源包

### 注:此处采用的大部分是教程资料，涉及插件开发内容较少(自认为),除了资源包可以检查一下其他作用不大.

## 资源包安装
```
server.properties文件中
require-source-pack=true
resource-pack=<url>
resource-pack-sha1=
```
1.表示需要强制安装资源包
2.表示资源包URL路径
3.表示资源包的sha1值

## 资源包获取与下载判断

```
Bukkit.getResourcePack()获取资源包uri
Bukkit.getResourcePackHash()获取资源包sha1
public class xxx implements Listener()
{
    @Override
    public void c(PlayerResourcePackStatusEvent e)
    {
        获取资源包状态
    }
}
```
其中:
状态|解释
---|---
ACCEPTED|客户端接收资源包开始下载
DECLINED|客户端拒绝接收资源包
FAILED_DOWNLOAD|客户端接收资源包但是下载失败
SUCCESSFULLY_LOADED|资源包成功下载并应用

## 玩家资料（PlayerProfile）

### 前言
玩家资料在较新版本被引入，旧版本(已知是1.17及以前)没有PlayerProfile
一份玩家资料（PlayerProfile接口）至少包含玩家唯一ID或非空名字，完整的玩家资料除两者外还要有玩家的纹理。
PlayerProfile是一个接口，所以你可以放心地使用。

### 「打印文件」

我们可以创建一份新的资料，至于是哪个玩家的资料取决于玩家名：
```
@NotNull
PlayerProfile createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name);
```
createPlayerProfile方法在Server接口中，可以使用getServer方法来获取：
```
Bukkit.getServer().createPlayerProfile(UUID.randomUUID(), "<玩家名>");
```
返回类型是PlayerProfile接口，这就是一份玩家资料。

### 「审阅文件」&「核验文件」
我们拿到了一份玩家资料，首先要检查资料是否齐全，即是否是一份完整的玩家资料，我们可以使用isComplete方法来检查资料是否完整。如果不完整，我们还可以调用update方法来更新玩家资料：
```
@NotNull
CompletableFuture<PlayerProfile> update();
```
必须注意，返回的类型是CompletableFuture类。因为玩家的相关资料都在官方网站中，所以update方法需要在另外一个线程中发起一个对外连接, 以拉取官方最新资料属性。
可以使用如下代码来操作更新完成的玩家资料：
```
profile.update().thenAcceptAsync(updatedProfile -> {
	//这里操作更新完成的玩家资料
	//...
}, runnable -> Bukkit.getScheduler().runTask(this, runnable));
```
thenAcceptAsync是指异步对单个处理完的结果进行操作（消费），但它不和父线程使用同一个线程，而是使用自定义的线程池，而在最后一个参数为该方法提供了Executor框架。

关于CompletableFuture<T>，有兴趣可以自行了解，本章不做详细讲解。

玩家的唯一ID及玩家名对我们来说没有一丁点价值，有价值的是玩家的纹理，纹理包括玩家的皮肤(Skin)和披风(Cape)，我们可以用getSkin和getCape分别获取皮肤和披风，注意二者的返回类型均为URL类。

可以用setSkin和setCape方法分别设置玩家的皮肤和披风为指定的URL。但是必须指向 Minecraft 纹理服务器，比如下面的URL就是一个例子：

http://textures.minecraft.net/texture/b3fbd454b599df593f57101bfca34e67d292a8861213d2202bb575da7fd091ac
```
PlayerProfile profile = Bukkit.getServer().createPlayerProfile(UUID.randomUUID(), "<玩家名>");
PlayerTextures textures = profile.getTextures();
try {
	textures.setSkin(new URL("http://textures.minecraft.net/texture/b3fbd454b599df593f57101bfca34e67d292a8861213d2202bb575da7fd091ac"));
} catch(Exception e) {
	e.printStackTrace();
	sender.sendMessage("发生异常：" + e.getMessage());
}
```


