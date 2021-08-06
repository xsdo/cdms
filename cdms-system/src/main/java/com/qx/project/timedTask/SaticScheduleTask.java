/*
package com.qx.project.timedTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    private AppProjectImplementMapper appProjectImplementMapper;
    //3.添加定时任务
    @Scheduled(cron = "0 0 0/12 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        AppProjectImplement appProjectImplement=new AppProjectImplement();
        List<AppProjectImplement> appProjectImplementList=appProjectImplementMapper.selectAppProjectImplementList(appProjectImplement);
        for (AppProjectImplement a:appProjectImplementList) {
            if("0".equals(a.getDelFlag())){
                if(!"3".equals(a.getStatus()) && !"2".equals(a.getStatus())){
                    AppProjectImplement appProjectImplement1=new AppProjectImplement();
                    if(new Date().compareTo(a.getPredictEndTime())>=0){
                        appProjectImplement1.setImplementId(a.getImplementId());
                        appProjectImplement1.setStatus("2");
                        appProjectImplementMapper.updateAppProjectImplement(appProjectImplement1);
                    }else if (new Date().compareTo(a.getPredictStartTime())>=0){
                        appProjectImplement1.setImplementId(a.getImplementId());
                        appProjectImplement1.setStatus("1");
                        appProjectImplementMapper.updateAppProjectImplement(appProjectImplement1);
                    }

                }else{
                    continue;
                }
            }else{
                continue;
            }
        }
        System.err.println("执行实施定时任务时间: " + LocalDateTime.now());
    }
}
*/
