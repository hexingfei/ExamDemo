package com.migu.schedule;

import java.util.List;

public class Queue
{
    int taskId;
    
    List<Integer> consumption;

    public int getTaskId()
    {
        return taskId;
    }

    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    public List<Integer> getConsumption()
    {
        return consumption;
    }

    public void setConsumption(List<Integer> consumption)
    {
        this.consumption = consumption;
    }
    
    
}
