[TOC]
# 计分板

## 获取计分板
```
1.Scoreboard scoreboard;
scorebaord=getServer().getScoreboardManager().getMainScoreboard()
2.Scoreboard scoreboard=manager.getNewScoreboard()
```
1.通过这种方式获取对服务器的主计分板<br>
2.通过这种方式创建不受指令控制的计分板
## 计分项设置
```
Objective obj;
obj=scoreboard.registerNewObjective("内部名","准则","标题")

```
准则|描述
--|--
dummy|	无法使用指令修改准则
deathCount|	累加死亡次数准则
playerKillCount|玩家击杀次数记录准则
killByTeam.XXX|	记录XXX队的击杀数准则
teamKill|	记录击杀队伍数准则

## 计分板位置
obj.setDisplaySlot(DisplaySlot.SIDEBAR)

显示位置|描述
----|----
BELOW_NAME|设置位置在玩家Tag里
PLAYER_LIST|设置位置在玩家列表中
SIDEBAR	|设置位置在侧边栏中

## 计分板分数&玩家推送
```
Score score=obj.getScore("xxx");
score.setScore(xxx)
player.setScoreboard(scoreboard)
```
## 销毁计分板
需要注意的是，如果不删除计分板，推送之后会一直存在（不会随着服务器关闭而销毁）
手动置空（还没尝试）<br>
```scoreboard = null```
## 队伍注册
```
Team team = scoreboard.registerNewTeam("红队");
```
## 队伍前缀与后缀设置
```
team.setPrefix("red") 前缀
team.setSuffix("blue") 后缀
```
## 加入队伍&删除队伍
team.addEntry() 添加实体（非玩家也可以）
team.removeEntry()删除实体
team.unregister() 移除队伍

## 展示队伍名字
team.setDisplayName("SB")

## 选项
选项	|描述
---|---
NAME_TAG_VISIBILITY|玩家名字是否可见
DEATH_MESSAGE_VISIBILITY|死亡信息是否可见
COLLISION_RULE|实体碰撞

例子:设置队伍玩家名字可见，而在其他队伍中队友名字不可见.
```
team.setOption(Team.Option.NAME_TAG_VISIBILITY,Team.OptionStatus.FOR_OTHER_TEAMS)
```
其中FOR_OTHER_TEAMS表示其他队伍，FOR_OWN_TEAM表示自身队伍
