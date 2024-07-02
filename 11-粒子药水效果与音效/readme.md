[TOC]
## 粒子效果

## 粒子生成
```void spawnParticle(particle var1,Location var2,int var3)```<br>
其中Particle表示的是枚举形状，Location是位置，int是播放次数

## 药水效果
```public PotionEffect(PotionEffectType type,int duration,int amplifier)```<br>
type表示的是药水效果,duration是时间,amplifier是倍率（也就是效果强弱）

## 效果使用
```entity.apply```

## 使用例子
```
public class GoodLuckEffect extends PotionEffect {
    public GoodLuckEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
        super(type, duration, amplifier);
    }

    @Override
    public boolean apply(LivingEntity entity) {
        entity.sendMessage("幸运之人免受伤害！");
        entity.setMaximumNoDamageTicks(getDuration() * 5);
        return true;
    }
}
```

## 音效

## 音效创建
```void playSound(Location var1,Sound var2,float var3,float var4)```
<br>var1表示播放位置，<br>var2表示播放音效
<br>var3表示播放的百分比,<br>var4表示播放倍速

## 音效例子
```player.getWorld().playSound(player.getLocation(), Sound.MUSIC_DISC_11, 1, 1);```


