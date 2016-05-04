# samples
Small Example
>> 关于从sdcard热更新
   使用新添加函数upgradeBundleLocal
   push插件的.so文件到/sdcard目录，注意编译的时候修改插件版本
   点击更新后，注意重启宿主
>> 插件中使用broadcast receiver，需要在宿主manifest里注册