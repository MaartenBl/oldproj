using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PathPoint : MonoBehaviour
{
    public DataPoint data;
    LineRenderer _line = null;
    LineRenderer line { get { if (_line != null) return _line; var lr = this.GetComponent<LineRenderer>(); _line = lr; return lr; } }
    public double timestamp { get; set; }
    public Vector3 acceleration { get; set; }
	public Vector3 velocity { get; set; }
	public float speed { get; set; }
    [SerializeField]
    Color slowColor;
    [SerializeField]
    Color fastColor;
    [SerializeField]
    float slowBound; // numbers in m/s
    [SerializeField]
    float fastBound;
	[SerializeField]
	int pointsPerSegment;
    public void setLineRendererTarget (PathPoint target)
	{
		var grd = new Gradient();
		grd.mode = GradientMode.Blend;
		var tgclr = target.calculateColor();
		var colorKeys = new GradientColorKey[] { new GradientColorKey(calculateColor(), 0), new GradientColorKey(tgclr, 0.1f), new GradientColorKey(tgclr, 1) };
		var alphaKey = new GradientAlphaKey[2];
		alphaKey[0].alpha = 1.0f;
		alphaKey[0].time = 0.0f;
		alphaKey[1].alpha = 1.0f;
		alphaKey[1].time = 1.0f;
		grd.SetKeys(colorKeys, alphaKey);
		this.line.colorGradient = grd;
		this.line.useWorldSpace = true;
		this.line.positionCount = pointsPerSegment;
		this.line.numCapVertices = 4;
		this.line.numCornerVertices = 2;
		var positions = new Vector3[pointsPerSegment];
		for (int i = 0; i < pointsPerSegment; i++)
		{
			positions[i] = Vector3.Lerp(this.transform.position, target.transform.position, (float)i / (pointsPerSegment - 1) );
		}
		Debug.Log(positions);
		this.line.SetPositions(positions);
	}

	private Color calculateColor()
	{
		var hsvSlow = new Vector3();
		Color.RGBToHSV(slowColor, out hsvSlow.x, out hsvSlow.y, out hsvSlow.z);
		var hsvfast = new Vector3();
		Color.RGBToHSV(fastColor, out hsvfast.x, out hsvfast.y, out hsvfast.z);
		hsvfast.x = hsvfast.x + 1;
		var newHSV = Vector3.Lerp(hsvSlow, hsvfast, (speed - slowBound) / (fastBound - slowBound));
		var newColor = Color.HSVToRGB(newHSV.x % 1, newHSV.y, newHSV.z);
		return newColor;
	}
}
