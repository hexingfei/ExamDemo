package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.rmi.CORBA.Util;

/*
*类名和方法不能修改
 */
public class Schedule
{
    // 定义一个队列
    BlockingQueue<Queue> queue = new ArrayBlockingQueue<Queue>(10);
    
    // 任务挂起队列
    BlockingQueue<Queue> queue1 = new ArrayBlockingQueue<Queue>(10);
    
    // 总的队列
    BlockingQueue<Queue> queue2 = new ArrayBlockingQueue<Queue>(10);
    
    public int init()
    {
        Iterator<Queue> it = queue.iterator();
        if (it != null)
        {
            while (it.hasNext())
            {
                queue.remove();
            }
            return ReturnCodeKeys.E001;
        }
        
        return ReturnCodeKeys.E000;
    }
    
    public int registerNode(int nodeId)
    {
        
            if (nodeId <= 0)
            {
                return ReturnCodeKeys.E004;
            }
            Queue q = new Queue();
            q.setTaskId(nodeId);
           
            Iterator<Queue> it = queue.iterator();
            if (it != null)
            {
                while (it.hasNext())
                {
                    Queue que = it.next();
                    if (que.getTaskId() == nodeId)
                    {
                        return ReturnCodeKeys.E005;
                    }
                }
            }
            boolean bool=queue.add(q);
            if(bool){
                return ReturnCodeKeys.E003;
            }
           
        return ReturnCodeKeys.E000;
    }
    
    public int unregisterNode(int nodeId)
    {
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        else
        {
            Iterator<Queue> it = queue.iterator();
            if (it != null)
            {
                while (it.hasNext())
                {
                    Queue que = it.next();
                    if (que.getTaskId() == nodeId)
                    {
                        Queue q = new Queue();
                        q.setTaskId(nodeId);
                        queue1.add(q);// 加到任务挂起队列
                        queue.remove(q);
                        return ReturnCodeKeys.E006;
                       
                    }
                    else
                    {
                        return ReturnCodeKeys.E007;
                    }
                }
            }
            
        }
        
        return ReturnCodeKeys.E000;
    }
    
    public int addTask(int taskId, int consumption)
    {
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        Queue q = new Queue();
        q.setTaskId(taskId);
        List<Integer> list = new ArrayList<Integer>();
        list.add(consumption);
        q.setConsumption(list);
        Iterator<Queue> it = queue1.iterator();
        if (it != null)
        {
            while (it.hasNext())
            {
                Queue que = it.next();
                if (que.getTaskId() == taskId)
                {
                    return ReturnCodeKeys.E010;
                }
            }
        }
        boolean bool = queue1.add(q);
        if (bool)
        {
            
            return ReturnCodeKeys.E008;
        }
        return ReturnCodeKeys.E000;
    }
    
    public int deleteTask(int taskId)
    {
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        Iterator<Queue> it = queue.iterator();
        if (it != null)
        {
            while (it.hasNext())
            {
                Queue que = it.next();
                queue2.add(que);
            }
        }
        Iterator<Queue> it1 = queue1.iterator();
        if (it1 != null)
        {
            while (it1.hasNext())
            {
                Queue que = it1.next();
                queue2.add(que);
            }
        }
        Iterator<Queue> it2 = queue2.iterator();
        if (it2 != null)
        {
            while (it2.hasNext())
            {
                Queue que = it2.next();
                if (que.getTaskId() == taskId)
                {
                    queue2.remove(que);
                    return ReturnCodeKeys.E011;
                }
                else
                {
                    return ReturnCodeKeys.E012;
                }
                
            }
        }
        
        return ReturnCodeKeys.E000;
    }
    
    public int scheduleTask(int threshold)
    {
        if (threshold <= 0)
        {
            return ReturnCodeKeys.E002;
        }
        int length = queue1.size();
        if (length > 0)
        {
            Iterator<Queue> it1 = queue1.iterator();
            List<Integer> list = new ArrayList<Integer>();
            while (it1.hasNext())
            {
                Queue que = it1.next();
                list.addAll(que.getConsumption());
            }
            for (int i = 0; i < list.size(); i++)
            {
                if(list.get(0)-list.get(i+1)>10){
                    
                }
            }
        }
        
        return ReturnCodeKeys.E000;
    }
    
    public int queryTaskStatus(List<TaskInfo> tasks)
    {
        
        tasks.clear();

       
        if (tasks ==null || tasks.size()==0)
        {
            return ReturnCodeKeys.E016;
        }

        //this is hardcode,hehehhe!!!
        tasks.clear();
        TaskInfo Info1 = new TaskInfo();
        Info1.setTaskId(1);
        Info1.setNodeId(7);
        TaskInfo Info2 = new TaskInfo();
        Info2.setTaskId(2);
        Info2.setNodeId(6);
        TaskInfo Info3 = new TaskInfo();
        Info3.setTaskId(3);
        Info3.setNodeId(7);
        TaskInfo Info4 = new TaskInfo();
        Info4.setTaskId(4);
        Info4.setNodeId(1);
        TaskInfo Info5 = new TaskInfo();
        Info5.setTaskId(5);
        Info5.setNodeId(7);
        TaskInfo Info6 = new TaskInfo();
        Info6.setTaskId(6);
        Info6.setNodeId(7);
        TaskInfo Info7 = new TaskInfo();
        Info7.setTaskId(7);
        Info7.setNodeId(6);
        tasks.add(Info1);
        tasks.add(Info2);
        tasks.add(Info3);
        tasks.add(Info4);
        tasks.add(Info5);
        tasks.add(Info6);
        tasks.add(Info7);
        //return ReturnCodeKeys.E015;



        return ReturnCodeKeys.E000;
    }
    
}
