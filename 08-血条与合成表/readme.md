[TOC]
# 进度条
```
public static KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor color, BarStyle style, BarFlag... flags) {
	return server.createBossBar(key, title, color, style, flags);
}
```
参数详解:<br>
1.key 表示这个进度条的唯一ID<br>
2.title 进度条的标题<br>
3.color 进度条的颜色(BLUE,GREEN,PINK,PURPLE,RED,WHITE,YELLOW)<br>
4.style 进度条的样式(SOLID,SEGMENTED_6/10/12/20)<br>
5.FLAG 进度条的附加属性<br>
### 参数内容解析
常量 |	说明
---|---
SOLID|	将血条设置为 1 个部分
SEGMENTED_6|	将血条设置为 6 个部分
SEGMENTED_10|	将血条设置为 10 个部分
SEGMENTED_12|	将血条设置为 12 个部分
SEGMENTED_20|	将血条设置为 20 个部分
CREATE_FOG	|末影龙的血条属性，告诉客户端播放打末影龙时候的BGM|
DARKEN_SKY|	凋零血条的属性（战斗时天空会变暗）
PLAY_BOSS_MUSIC|在血条所在的世界创建一个迷雾

## 进度条创建例子
```
BossBar bossBar=Bukkit.createBossBar(new NamespaceKey(PluginMain.instance,"MyFirstBossBAR"),BarColor.PINK,BarStyle.SOLID,BarFlag.DARKEN_SKY)
bossBar.addPlayer(Player)
bossBar.removePlayer(Player)
```
上述是创建BOSS进度条，然后添加到玩家身上，以及去掉BOSS进度条

## 合成表

## 合成表创建
Shaped Recipe(NamespacedKey key,ItemStack result)
## 菜单合成表建立
recipe =recipe.shape("bbb","bab","bbb")<br>
此处的a和b均代表某个物品
## 映射合成
```recipe=recipe.setIngredient('b',Material.xxx).setIngredient('a',Material.yyy)```
此处表示的则是a=>Material.yyy,b=>Material.xxx
## 添加映射
Bukkit.addRecipe(recipe)
## 映射方式的选择
```
public ShapedRecipe setIngredient(char key,  MaterialData ingredient) {
}
public ShapedRecipe setIngredient(char key,  RecipeChoice ingredient) {
}
public ShapelessRecipe setIngredient(char key,Recipe ingredient)
```
第一种方式表示的是这个物品就可以了，无论是名称还是介绍
第二种方式则是表示物品，名称，介绍都需要相同
第三种方式表示的是无序配方，只需要上述提到的材料即可以合成。

## 合成表删除
插件卸载时需要删除合成表，否则会服务器崩溃
```Bukkit.resetRecipes```
