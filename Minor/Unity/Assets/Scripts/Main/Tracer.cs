using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Tracer : MonoBehaviour
{
    LinkedListNode<PathPoint> lastPoint;
    private bool rewinding = false;
    private double lastTimeRendered = 0;

    public void SetPath (LinkedList<PathPoint> path)
	{
        lastPoint = path.First;
        this.transform.position = lastPoint.Value.transform.position;
	}

    public bool RenderTime (double renderTime)
	{
        double temp = lastTimeRendered;
        lastTimeRendered = renderTime;
        if (renderTime >= temp)
		{
			if (rewinding)
			{
                rewinding = false;
                lastPoint = lastPoint.Previous ?? lastPoint.List.First;
			}
            return moveAlong(renderTime);
		}
		else
		{
            if (!rewinding)
			{
                rewinding = true;
                lastPoint = lastPoint.Next ?? lastPoint.List.Last;
			}
            return moveBack(renderTime);
		}
        
	}

    private bool moveAlong (double renderTime)
	{
        while (lastPoint.Next != null && lastPoint.Next.Value.timestamp < renderTime)
		{
            lastPoint = lastPoint.Next;
		}
        if (lastPoint.Next == null)
        {
            return false;
        }
        this.transform.position = Vector3.Lerp(lastPoint.Value.transform.position, lastPoint.Next.Value.transform.position, (float)((renderTime - lastPoint.Value.timestamp) / (lastPoint.Next.Value.timestamp - lastPoint.Value.timestamp)));
        return true;
	}

    private bool moveBack (double renderTime)
	{
        while (lastPoint.Previous != null && lastPoint.Previous.Value.timestamp > renderTime)
        {
            lastPoint = lastPoint.Previous;
        }
        if (lastPoint.Previous == null)
        {
            return false;
        }
        this.transform.position = Vector3.Lerp(lastPoint.Value.transform.position, lastPoint.Previous.Value.transform.position, (float)((renderTime - lastPoint.Value.timestamp) / (lastPoint.Previous.Value.timestamp - lastPoint.Value.timestamp)));
        return true;
    }
}
