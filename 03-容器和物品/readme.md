[TOC]
# 容器和物品

## 容器创建
```
Inventory inv=Bukkit.createInventory(null,9,"")
```
其中9是可容纳数量，后面的String表示容器的名字，数字必须是9的倍数.

## 玩家打开容器
```
player.openInventory(inv)
```

## 物品创建
```
ItemStack is =new ItemStack(Material.COMPASS)
ItemMeta im=is.getItemMeta()
im.setDisplayName("xxx")
im.setLore(Arrays.asList("一个功能"))
is.setItemMeta(im)
```
此处ItemStack表示物品堆，ItemMeta则是容器的属性
具体方法可以参考BukkitApi

## 容器添加物品
```
inv.addItem(is)
inv.setItem(9,is)
```
一个是直接添加（类似于数组添加）,一个是直接覆盖(类似于数组插入,其值为二维数组从左到右遍历，起始值为0)




