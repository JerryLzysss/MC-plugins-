[TOC]
## 实体（API使用）
```
p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
Zombie zombie = (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
//设置实体名字
zombie.setCustomName("守卫者");
//设置实体名字可见
zombie.setCustomNameVisible(true);
```
上述提到的只是将创建实体的功能基础实现

## 实体(NMS)

### 注:下列所提到的均是教程中所提及的，由于NMS涉及的原理以及其函数封装不利于直接运用，同时兼容性较差，因此此处仅稍微提及，而Secondplugins实现的方式是相似的道理，当然BUG是有的（双手给钻石啥的？反正修补过来了）

### 前言
自己创建一个实体是不太可能的，亲这边建议你转 Mod 开发，插件开发还是要看服务器的心情的呢。
所谓插件，无非是在原版的基础上加点特色，所以我们无法增加一个新物品，增加一个新实体，增加一个新方块。
那么，也只能在现有的实体上做做更改了。
### 1.1.自定义实体行为/重构AI（PathFinderGoal）
现有的实体已经能满足我们的大部分要求了，我们没有必要自己创建一个实体，可以重写一个实体啊。
现在我们需要一头牛，能吃钻石，并且能生产钻石块。
```
public class MyCustomCowEntity extends EntityCow {
	public MyCustomCowEntity(EntityTypes<? extends EntityCow> entitytypes, World world) {
        super(entitytypes, world);
    }
}
```
在上面的代码中，我们继承了EntityCow类（当然这是继承 NMS 下的类），现在就可以开始进行重写了。
我们写一个构造方法，传入牛生成的位置。
```
public MyCustomCowEntity(Location location) {
	this(EntityTypes.COW, ((CraftWorld) location.getWorld()).getHandle());
	//更改牛生成的位置
	this.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
}
```
然后重写一下initPathfinder方法：
initPathfinder方法是初始化AI处理器的，牛原本的处理器我们不要了，自己重写一下这个方法，加入自己想要的处理器。
```
@Override
public void initPathfinder() {
	this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
	this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
}
```
我们共添加了两个 AI处理器，NMS 中有很多这样的处理器。如果没有你想要的处理器，也可以自己写一个。
每个处理器都有自己的优先级，用 0~8 这几个数字表示。数字越小代表优先级越高，数字越高代表优先级越低。
### 1.2.交互
当我们拿着小麦右键牛，牛会吃这棵小麦。进而概括成「玩家右键实体」这一事件。
public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand);
上面的b方法就是处理用来「右键实体」的。来看这方法的两个参数。
entityhuman代表玩家，enumhand就代表玩家的手。显然我们可以得到玩家手上的物品。
//获取玩家手上的物品
```ItemStack itemStack = entityhuman.b(enumhand)```
我们还需判断这个物品是什么，按照甲方的需求，应该当玩家手中物品为钻石才行。

我们规定需要吃完一组钻石才能生产钻石块。那么就开个变量记录吃了多个钻石。
```
private int food = 0;

//是不是钻石且牛已经长大
if(itemStack.getItem() == Items.DIAMOND && !this.isBaby()) {
	//钻石吃的数量+1
	this.food += 1;
}
```
//由于 food 是私有的，自己写两个函数用于获取和更改 food
```
public int getFood() { return this.food; }
public void setFood(int food) { this.food = food; }
```
最后也就可以收尾了，让玩家手中的钻石数量减去 1就可以了。
```
this.a(entityhuman, itemStack);//物品数量减去1
return EnumInteractionResult.a(this.world.isClientSide);//判断是否是客户端
```
### 1.3.重写处理器
现有的处理器不能满足甲方的需求，那么我们需要自己写一个处理器。
一样的，新建一个继承PathfinderGoal的类。
```
public class PathfinderGoalDiamond extends PathfinderGoal {

}
```
开始之前，我们要想好自己这个处理器应该做什么。这里我就写牛的位置会出现一个钻石块。
照例，先写好构造函数：
```
private EntityCow cow;
public PathfinderGoalDiamond(EntityCow cow) {
	this.cow = cow;
}
```
我们传入一个Cow对象，表明这个对象将由我们这个AI处理器所控制。
于是，这个对象的一切我们就都知道了，下面的也就好写了。
```
@Override
public boolean a();
@Override
public boolean b();
@Override
public void c();
@Override
public void d();
@Override
public void e();
```
看到这五个函数，发现它们都已被混淆，它们原来的函数名分别是：
```
@Override
public boolean shouldExecute();
@Override
public boolean continueExecuting();
@Override
public void startExecuting();
@Override
public void resetTask();
@Override
public void updateTask();
```
顾名思义，首先 Bukkit 会去调用a方法，判断是否执行。如果可以则调用c方法开始执行，之后的每 1 tick(0.05s) 中，都会去调用一次b方法，判断是否继续执行。如果返回真就调用e方法，表示更新Task，如果返回假就调用d方法，清除Task恢复原样。

需要注意，我们的处理器要有一定概率的去执行，如果没有概率你可以完全写一个BukkitRunnable，这就多此一举了。所以，在a方法中，我们需要设置概率。嗯……就先来个五分之一的概率吧（毕竟喂了这么多钻石）。

也要注意，我们还需要判断玩家是否喂满一组钻石。
```
//是否是白天
if(!this.cow.getWorld().isDay()) {
	return false;
}
//是否吃了一组钻石
if(this.cow.getFood() < 64) {
	return false;
}
//五分之一的概率触发
if(this.cow.getRandom().nextInt(5) != 0) {
	return false;
}
//消耗吃完的一组钻石
this.cow.setFood(0);
//以上都满足，可以执行该处理器
return true;
```
我们设置了三个条件，第一个条件是需要当前时间处于白天，第三个这就是我们所说的概率触发。

假设三个条件都可以满足，那么吃完的那一组钻石需要消耗掉啊。

紧接着就是调用c方法，开始执行了，生成钻石块的时间设定为 600 ticks，也就是半分钟。
```
this.luckyTime = 600;
//设置牛的所处位置为钻石块
this.cow.getBukkitEntity().getLocation().getBlock().setType(Material.DIAMOND_BLOCK);
```
之后的每 1tick 中，都会去调用b方法，判断是否继续执行，那么每次只需判断luckyTime是否大于 0 即可。

```return this.luckyTime > 0;```

如果这个条件也满足了，就执行e方法更新我们的Task，每次luckyTime都需要减去1，同时也别忘了在脚底下生成钻石块！
```
this.luckyTime -= 1;
this.cow.getBukkitEntity().getLocation().getBlock().setType(Material.DIAMOND_BLOCK);
```
如果b方法返回假，表示条件不满足，此时应当清空我们的Task，使它变回原来的正常的牛牛，好像也不需要做些什么，就让luckyTime设为-1吧。

```this.luckyTime = -1;```
