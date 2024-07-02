[TOC]
# 世界生成

## 评价:有点难度的生成编写..我感觉不如之前使用的一些插件生成房子，在mcbbs上的那些，还是把CSDN的教程搬运过来算了，主要是实用性感觉一般吧，让大神写就好了。

# 0.原理
Minecraft 地形生成是分为两部分：```Generation```和```Population```。```Generation```部分负责生成最基本的地形，```Population```部分是负责在这个地形上加装饰（花、草、树等等）。

区块是世界中长和宽为 16 16 16，高为 256 256 256 的部分。两个部分都是以区块作为最基本的单位，Generation再生成基础地形。

# 1.装饰
在世界开始加载时会触发一个WorldInit事件，我们可以监听这一事件，然后来完成一次“点缀”。
```
public class DiamondGenerator implements Listener {

    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        e.getWorld().getPopulators().add(new DiamondPopulator());
    }
}
```
前面说过，Population就是给原本的地形上加点装饰，当然加的装饰不止一种，我们可以自己添加一种，重点在于实现DiamondPopulator类。
```
public class DiamondPopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk source) {
		
	}
}
```
首先判断我们需要添加什么，比如随处可见的钻石块，假设我们一个区块需要5个钻石块，对于每一个钻石块而言，只需确定它的横纵坐标，遍历一遍所有y轴坐标判断是否符合我们要的条件即可。
```
int count = 5;
for(int i = 0; i <= 5; i++) {
	//随机确定x,z坐标
	int x = random.nextInt(16);
	int z = random.nextInt(16);
	//遍历我们所要的y坐标
	for(int y = 128; y >= 0; y--) {
		//我们的钻石块要在天空上，即空气方块上
		if(source.getBlock(x, y, z).getType() == Material.AIR) {
			world.getBlockAt(x, y, z).setType(Material.DIAMOND_BLOCK);
		}
	}
}
```
# 2.地形
## 2.1.简单区块地形生成

前面说过，Generation以区块作为基本单位生成，所以我们可以用ChunkGenerator来生成一个最最简单的地形，比如说钻石大陆。
在你的主类中重写：
```
@Override
public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	return new DiamondGenerator();
}
```
老规矩，重在实现DiamondGenerator：
```
public class DiamondGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        chunkData.setRegion(0, 1, 0, 16, 16, 16, Material.DIAMOND_BLOCK);
        return chunkData;
    }
}
```
setRegion方法是用于填充方块，传入两个三维坐标，然后对选中的部分进行填充。
如上，对于每个区块，我们只需将它填充为钻石块就行了。对于每个区块的最底层，我们将它填充为基岩就好了。
我们只是用一个小区块，然后用它拼合成一个大世界。这是最简单的地形生成。

## 2.2.其余地形生成
```
private SimplexOctaveGenerator simplex;
@Override
public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
    ChunkData chunkData = createChunkData(world);
    if(simplex == null) {
    	//传入种子
        simplex = new SimplexOctaveGenerator(world.getSeed(), 1);
        simplex.setScale(0.001D);
    }
    return chunkData;
}
```
这次我们不需要随机取一个方块的横纵坐标了，我们只需改变一个方块的高度既可，因为最终形成的地形是跌宕起伏的，不可能有个大窟窿吧。
```
for(int i = 0; i < 16; i++) {
    for(int j = 0; j < 16; j++) {
    	//设置x,z坐标
        int xx = x * 16 + i;
        int zz = z * 16 + j;

		
        double noise = simplex.noise(xx, zz, 0.3D, 0.4D);
       
       	int y = (int) (noise * 35D + 150D);

		//底层基岩
        chunkData.setBlock(x, 0, z, Material.BEDROCK);

		//1~y层全为钻石块
        for(int k = 1; k <= y; k++) {
            chunkData.setBlock(x, y, z, Material.DIAMOND_BLOCK);
        }
    }
}
```
以上就是Generation部分，至于Populartion部分，我们可以用getDefaultPopulators方法：
```
@Override
public List<BlockPopulator> getDefaultPopulators(World world) {
    return ImmutableList.of(....);//你的装饰生成器
}
```