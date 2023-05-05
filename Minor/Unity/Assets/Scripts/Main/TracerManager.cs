using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TracerManager : MonoBehaviour
{
    [SerializeField]
    private Tracer tracerPrefab;
    private List<Tracer> tracers = new List<Tracer>();
    [SerializeField]
    public bool paused = true;
    [SerializeField]
    private bool started = false;
    [SerializeField]
    public int speed;
    [SerializeField]
    private double lastRenderedTime = 0;
    double min = 999999999999999999;
    double max = 0;

    private void Update()
    {
		if (Input.GetKeyDown(KeyCode.K))
		{
            if (!started)
            { StartTracers(); started = true; }
            paused = !paused;
        }
        if (Input.GetKeyDown(KeyCode.Backspace))
		{
            speed = -speed;
		}
        if (Input.GetKeyDown(KeyCode.Equals) || Input.GetKeyDown(KeyCode.KeypadPlus))
		{
            speed = (int)(Mathf.Sign((float)speed) * (Mathf.Abs((float)speed) + 1));
		}
        if (Input.GetKeyDown(KeyCode.Minus) || Input.GetKeyDown(KeyCode.KeypadMinus))
        {
            if(speed != 0)
                speed = (int)(Mathf.Sign((float)speed) * (Mathf.Abs((float)speed) - 1));
        }
        if (Input.GetKeyDown(KeyCode.Alpha1) || Input.GetKeyDown(KeyCode.Keypad1)) { speed = 1; } // keys 1-0 to do speeds from 10% to 100%
        if (Input.GetKeyDown(KeyCode.Alpha2) || Input.GetKeyDown(KeyCode.Keypad2)) { speed = 2; }
        if (Input.GetKeyDown(KeyCode.Alpha3) || Input.GetKeyDown(KeyCode.Keypad3)) { speed = 3; }
        if (Input.GetKeyDown(KeyCode.Alpha4) || Input.GetKeyDown(KeyCode.Keypad4)) { speed = 4; }
        if (Input.GetKeyDown(KeyCode.Alpha5) || Input.GetKeyDown(KeyCode.Keypad5)) { speed = 5; }
        if (Input.GetKeyDown(KeyCode.Alpha6) || Input.GetKeyDown(KeyCode.Keypad6)) { speed = 6; }
        if (Input.GetKeyDown(KeyCode.Alpha7) || Input.GetKeyDown(KeyCode.Keypad7)) { speed = 7; }
        if (Input.GetKeyDown(KeyCode.Alpha8) || Input.GetKeyDown(KeyCode.Keypad8)) { speed = 8; }
        if (Input.GetKeyDown(KeyCode.Alpha9) || Input.GetKeyDown(KeyCode.Keypad9)) { speed = 9; }
        if (Input.GetKeyDown(KeyCode.Alpha0) || Input.GetKeyDown(KeyCode.Keypad0)) { speed = 10; }


        if (!paused)
		{
            var newTime = lastRenderedTime + Time.deltaTime * 100 * speed; // * 1000 for milliseconds / 10 for speed int factor===
            if (newTime > max || newTime < min)
                return;

            tracers.ForEach(t => { var rend = t.RenderTime(newTime); });
            lastRenderedTime = newTime;
        }
    }

    public void StartTracers()
    {
        List<double> startTimes = new List<double>();
        List<double> endTimes = new List<double>();
        foreach (LinkedList<PathPoint> path in JsonReader.GetInstance().paths)
        {
            var tracer = Instantiate<Tracer>(tracerPrefab);
            tracer.SetPath(path);
            tracers.Add(tracer);
            startTimes.Add(path.First.Value.timestamp);
            endTimes.Add(path.Last.Value.timestamp);
        }
        
		for (int i = 0; i < startTimes.Count; i++)
		{
            min = startTimes[i] < min ? startTimes[i] : min;
        }
        for (int i = 0; i < endTimes.Count; i++)
        {
            max = endTimes[i] > max ? endTimes[i] : max;
        }
        lastRenderedTime = min;    
    }
}
