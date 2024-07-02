[TOC]
## 事件
在前面已经很频繁的使用事件监听了，此处在略微概括一下.
## 事件注册
如果使用其余类的话需要实现接口
<br>
```public class EventListener implements Listener｛｝```
<br>
随后需要在plugin中进行注册
```Bukkit.getPluginManager().registerEvents(new EventListener)```

## 事件监听
需要使用注解
```
@EventHandler
public void xxx(Event e)
{

}
```

## 附魔

## 附魔方法
```boolean addEnchant(Enchantment var1,int var2,boolean var3)```<br>
var1表示:附魔内容，var2表示附魔等级，var3表示是否需要忽略附魔等级限制，例如锋利为V，耐久为III

## 附魔例子
```
ItemStack hello = new ItemStack(Material.DIAMOND_SWORD);
ItemMeta im = hello.getItemMeta();
im.addEnchant(Enchantment.DAMAGE_ALL, 32767, true);
hello.setItemMeta(im);
```

## Lore
其作用类似于meta(物品介绍)<br>
```
void setLore(List<String> var)
List<String> a = new ArrayList<>();
a.add("Hello World!");
a.add("dlroW olleH!");
im.setLore(a);
```

## 属性

## 属性创建
```boolean addAttributeModifier(@NotNull Attribute var1, @NotNull AttributeModifier var2);```
var1表示参数属性，var2表示修饰符

## 属性修改
```
public AttributeModifier(
@NotNull UUID uuid, 
@NotNull String name, 
double amount, 
@NotNull Operation operation, 
@Nullable EquipmentSlot slot);
```
uuid:表示修饰符的唯一标识<br>
name:修饰符名称<br>
amount:修饰值<br>
operation:运算模式<br>
slot:修饰槽位(例如主手有效)

运算模式|效果
---|---
add_number|增加X
add_scalar|乘以X
multiply_scalar_1|增加1之后在乘x

## 属性例子
```
ItemStack hello = new ItemStack(Material.DIAMOND_SWORD);
ItemMeta im = hello.getItemMeta();
im.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, 
new AttributeModifier(UUID.randomUUID(), 
"击退力度",  4.0, 
AttributeModifier.Operation.ADD_SCALAR));
hello.setItemMeta(im);
```
我们给钻石剑增加一个「攻击击退力度」，是原本的4倍。
