using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour
{
	[SerializeField]
	float mainSpeed; //regular speed
	[SerializeField]
	float shiftAdd; //multiplied by how long shift is held.  Basically running
	[SerializeField]
	float maxShift; //Maximum speed when holdin gshift
	[SerializeField]
	float camSens = 0.25f; //How sensitive it with mouse
	private Vector3 lastMouse = new Vector3(255, 255, 255); //kind of in the middle of the screen, rather than at the top (play)
	private float totalRun = 1.0f;
	[SerializeField]
	private Collider bounds;

	void Update()
	{
		if (Input.GetMouseButton(0))
		{
			lastMouse = Input.mousePosition - lastMouse;
			lastMouse = new Vector3(-lastMouse.y * camSens, lastMouse.x * camSens, 0);
			lastMouse = new Vector3(this.transform.eulerAngles.x + lastMouse.x, transform.eulerAngles.y + lastMouse.y, 0);
			transform.eulerAngles = lastMouse;
		}
		lastMouse = Input.mousePosition;
		//Mouse & camera angle done. 


		//Keyboard commands
		float f = 0.0f;
		var p = GetBaseInput();
		if (Input.GetKey(KeyCode.LeftShift) || Input.GetKey(KeyCode.RightShift))
		{
			totalRun += Time.deltaTime;
			p = p * totalRun * shiftAdd;
			p.x = Mathf.Clamp(p.x, -maxShift, maxShift);
			p.y = Mathf.Clamp(p.y, -maxShift, maxShift);
			p.z = Mathf.Clamp(p.z, -maxShift, maxShift);
		}
		else
		{
			totalRun = Mathf.Clamp(totalRun * 0.5f, 1, 1000);
			p = p * mainSpeed;
		}

		p = p * Time.deltaTime;
		if (Input.GetKey(KeyCode.Z))
		{
			//If player wants to move on X and Z axis only
			f = transform.position.y;
			transform.Translate(p);
			transform.position = new Vector3(transform.position.x, f, transform.position.z);
		}
		else
		{
			transform.Translate(p);
		}
		if (!bounds.bounds.Contains(transform.position))
			transform.position = bounds.ClosestPointOnBounds(transform.position);
	}

	private Vector3 GetBaseInput()
	{
		//returns the basic values, if it's 0 than it's not active.
		Vector3 p_Velocity = new Vector3(0,0,0);
		if (Input.GetKey(KeyCode.W))
		{
			p_Velocity += new Vector3(0, 0, 1);
		}
		if (Input.GetKey(KeyCode.S))
		{
			p_Velocity += new Vector3(0, 0, -1);
		}
		if (Input.GetKey(KeyCode.A))
		{
			p_Velocity += new Vector3(-1, 0, 0);
		}
		if (Input.GetKey(KeyCode.D))
		{
			p_Velocity += new Vector3(1, 0, 0);
		}
		if (Input.GetKey(KeyCode.E))
		{
			p_Velocity += new Vector3(0, 1, 0);
		}
		if (Input.GetKey(KeyCode.Q))
		{
			p_Velocity += new Vector3(0, -1, 0);
		}
		return p_Velocity;
	}
}
