1. /scan
```
header: 
  seq: 153061
  stamp: 
    secs: 1610720524
    nsecs: 243681907
  frame_id: "ego_racecar/laser"
angle_min: -2.34999990463
angle_max: 2.34999990463
angle_increment: 0.00435185199603
time_increment: 0.0
scan_time: 0.0
range_min: 0.0
range_max: 30.0
```
    2. /odem: 可以得到自身信息
 ```
    header: 
  seq: 14
  stamp: 
    secs: 1610720730
    nsecs: 773636102
  frame_id: "/map"
child_frame_id: "ego_racecar/base_link"
pose: 
  pose: 
    position: 
      x: 5.01493161621
      y: -16.3616221529
      z: 0.0
    orientation: 
      x: 0.0
      y: 0.0
      z: -0.694301233459
      w: 0.719684512281
  covariance: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
twist: 
  twist: 
    linear: 
      x: 1.0
      y: 0.0
      z: 0.0
    angular: 
      x: 0.0
      y: 0.0
      z: 0.308853867263
  covariance: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
```

3. /drive : 发布形式命令
jerk 是什么？
```
header: 
  seq: 72737
  stamp: 
    secs: 0
    nsecs:         0
  frame_id: ''
drive: 
  steering_angle: -0.117255136371
  steering_angle_velocity: 0.0
  speed: 1.0
  acceleration: 0.0
  jerk: 0.0
```

4. /map 我不知道这个信息怎么得到的？类似于 100 障碍物， 0 可以走(现在知道了，这是要自己画图的)



*****
总结一下，借助docker，本地没有用 rosrun，而是用docker

1. 运行 docker，这是切换到单人版
```
cd catkin_ws/src/f1tenth_gym_ros/
git checkout master
sudo ./build_docker.sh
sudo ./docker.sh
```
2. 运行 docker，切换到多人版
```
cd catkin_ws/src/f1tenth_gym_ros/
git checkout multi_node
sudo ./build_docker.sh
sudo ./docker.sh
```
3. 还可以换地图，然后重新
4. 可用的
5. 流程图
    > 1, mission: overrall planner
    > 2. behavioral: rules to follow
    > 3. local: trajectory
![8abc13103a9a5077db1717ed3c4fa55f.png](evernotecid://A8D08A9F-E482-48E3-9266-362848C7C03A/appyinxiangcom/18722204/ENResource/p268)

******
## Pure Pursuiting
1. 给 2D position：waypoints to follow + 给 自身定位 = follow
2. 路径信息在车的坐标系下：车头指向x，y轴指向左边
3. Pipeline of pure pursuit:
    1. Create a new map
    2. create a list of waypoints using (teleop?)
    3. Pick waypoints to track(最重要的 paramater L，最近点，插值等等，L 越大路径约光滑，但是更容易撞到障碍物)
    4. 计算 waypoint（x,y)对应的speed + steering angle = 计算旋转半径 r。假设圆心在 机器人y 轴上，然后 v/r = omega
    5. update waypoint

******
## Cartographer
1. 制图的目的：path planning + state estimate
2. 千万不要盲目制图！！！！
3. ![ee1830520cca5c8efc46c5dc03c939cb.png](evernotecid://A8D08A9F-E482-48E3-9266-362848C7C03A/appyinxiangcom/18722204/ENResource/p271)
4. SLAM 内容复杂
    1. 很多核心内容都是依赖于 scan matching（常用算法 PL_ICP,ICP）
    2. google 用了 ceres-sovler to formulate a nonlinear least-squares correlative scan matching problem
    3. Local Slam: 生成submap，大小合适，覆盖所有的地图， 这是 correlation-based scan matching
    4. Global Slam：再次接受 scan，然后和我的submap
******
## Particle Filters

1. 给定 
    1. Laser Scans
    2. Map
    3. Odometry
2. 输出
    1. robot pose in map = （x,y,theta）
3. Key Ideas
    1. `Bel(xt) = P(xt|0t,At-1,Ot-1,,,) ` t时刻在`xt`的概率取决于之前所有的 observation 和 odometry
    2. Recursion 版，这里架设了Markov性质 + 简单的贝叶斯 `Bel(xt) = miu * P(Ot | xt) * integrate(P(xt|xt-1,at-1)*Bel(xt-1)dxt-1)`
        1. `miu` : 归一化系数
        2. `P(Ot | xt)`: sensor model：用你的结果去反推传感器的数据，看一下可不可能
        3. `P(xt|xt-1,at-1)`: motion model 这个是动力学上的概率
        4. `Bel(xt-1)dxt-1)`：转移概率
        5. 在particle中用sample来代替parametrical distribution
4. 算法细节
    1. Initialization：根据高斯分布：mean = 你猜的初始位置：covariance 小，生成这些samples
    2. Motion Model: 将控制信息 speed，steering angle 作用在上一次的位置上（这也是猜的，有误差的），然后生成动力学下的下一个位置
    3. Sensor 这个很复杂：你只有一套scan，但是你有很多粒子，所以就要raycasting：Breshenham‘Line
    4. 反推概率（particale weight）：给米一个fake scan计算一个可能性，比较真实obs中的range差距，做差然后高斯化。
    5. ![9f0585cff306aeaca7e6ef76e975cb9b.png](evernotecid://A8D08A9F-E482-48E3-9266-362848C7C03A/appyinxiangcom/18722204/ENResource/p269)
    
*******
## Motion Planning
1. Input: C = configuration space, obstacles Cob, initial configuration qinit, goal configuration qgoal
2. Output: 一条无冲突path
3. Occupany grid with multiple layers: 
    1. static layer 
    2. Dynamic Layer
    3. Environment Layer

4. Continuous Motion Planning
    1. 离散化的问题，用最短路径搜索就好了
    2. RRT：rapidly-exploring random trees：[Loop invariant: G 这个树内的点都是无冲突的，机器人可以到达的点]
        1. 从自身点开始：作为树的根节点，重复N次扩展这棵树
        2. 现在空间内，随便sample一个点 Xrand
        3. 在现有的树中找到一个距离Xrand最近的树内的点 Xneighbor
        4. 根据树内点 Xneighbor 和随机点X rand ，找到yige Xnew,假如 （Xneighbor,Xnew）中间无冲突，那么Xnew加入树内
    
    3. Steering function：
        1. 就是利用steering function 来确定新的加入的点，这个必须要符合你的动力学模型
        2. 随机点的选择：uniform？ or 先验的Biased？


5. Path Optimization:（生成Path是真的有用啊）
    1. 怎么在 track 中用spline 定义一个path？
    2. 在track中生成连续的有覆盖的矩形，然后在每个矩形中sample点，把这些点用spline优化一下连成低degree的path