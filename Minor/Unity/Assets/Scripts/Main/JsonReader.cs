using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Newtonsoft.Json;

public class JsonReader : MonoBehaviour
{
	private Vector3 lastPos;

    [SerializeField]
    TextAsset dummyData;

	[SerializeField]
    private PathPoint pathPointPrefab;

    [SerializeField]
    GameObject pathParent;

    public readonly List<LinkedList<PathPoint>> paths = new List<LinkedList<PathPoint>>() {};
    private static JsonReader instance;
    public static JsonReader GetInstance() { return instance; }
	private void Start()
	{
        JsonReader.instance = JsonReader.instance ?? this;
		lastPos = this.transform.position;
        StartCoroutine( InsertData(dummyData.text) );
    }


    private RunData run;
	public IEnumerator InsertData(string data)
	{
		// Debug.Log(data.ToString());
		run = JsonConvert.DeserializeObject<RunData>(data);
		GameObject[] parents = new GameObject[run.sensors.Count];
		for (int i = 0; i < run.sensors.Count; i++)
		{
			parents[i] = Instantiate(pathParent, this.gameObject.transform).transform.gameObject;
			parents[i].transform.localPosition = new Vector3();
			parents[i].name = $"Sensor {run.sensors[i].id}";
			var offset = run.sensors[i].ClbrOffset;

			var path = new LinkedList<PathPoint>();
			for (int g = 0; g < run.sensors[i].points.Count; g++)
			{
				PathPoint point = Instantiate<PathPoint>(pathPointPrefab, parents[i].transform);
				point.data = run.sensors[i].points[g];
				point.transform.localPosition = new Vector3(point.data.coordinates.X + offset.X, point.data.coordinates.Y + offset.Y, point.data.coordinates.Z + offset.Z); // new Vector3(i* g * 0.01f, 2 * Mathf.Cos(g * 0.02f), 2* Mathf.Sin(g * 0.02f) );
				point.transform.name = $"PathPoint{g}";
				point.timestamp = point.data.hardwareTime;
				if(path.Count != 0)
				{
					// calculating derivatives
					point.velocity = new Vector3((float)(1000*(point.transform.localPosition.x - path.Last.Value.transform.localPosition.x) / (point.timestamp - path.Last.Value.timestamp)), (float)(1000*(point.transform.localPosition.y - path.Last.Value.transform.localPosition.y) / (point.timestamp - path.Last.Value.timestamp)), (float)(1000*(point.transform.localPosition.z - path.Last.Value.transform.localPosition.z) / (point.timestamp - path.Last.Value.timestamp)));
					point.speed = point.velocity.magnitude;
					point.acceleration = new Vector3((float)(1000*(point.velocity.x - path.Last.Value.velocity.x) / (point.timestamp - path.Last.Value.timestamp)), (float)(1000*(point.velocity.y - path.Last.Value.velocity.y) / (point.timestamp - path.Last.Value.timestamp)), (float)(1000*(point.velocity.z - path.Last.Value.velocity.z) / (point.timestamp - path.Last.Value.timestamp)));
				}
				else
				{
					point.velocity = new Vector3();
					point.acceleration = new Vector3();
					point.speed = 0;
				}
				path.AddLast(point);
			}
			path.First.Value.speed = path.First.Next.Value.speed;
			path.First.Value.velocity = path.First.Next.Value.velocity;
			path.First.Value.acceleration = path.First.Next.Value.acceleration;
			paths.Add(path);
		}
		yield return null;

		RenderPaths();
	}

	public void RenderPaths()
	{
		paths.ForEach(p =>
		{
			LinkedListNode<PathPoint> cursor = p.First;

			for (int j = 0; j < p.Count - 1; j++)
			{
				cursor.Value.setLineRendererTarget(cursor.Next.Value);
				cursor = cursor.Next;
			}
			cursor.Value.GetComponent<LineRenderer>().enabled = false;
		});
	}

	private void Update()
	{
		if (this.transform.position != lastPos)
		{
			this.RenderPaths();
			lastPos = this.transform.position;
		}
	}
}
