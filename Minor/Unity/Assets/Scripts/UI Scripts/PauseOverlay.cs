using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PauseOverlay : MonoBehaviour
{
    private float t;
    [SerializeField]
    private float decayConstant;
    [SerializeField]
	private Image img = null;
    [SerializeField]
	private Text txt = null;
	// Start is called before the first frame update
	private void OnEnable()
	{
        img.color = new Color(img.color.r, img.color.g, img.color.b, 1);
        txt.color = new Color(img.color.r, img.color.g, img.color.b, 1);
        t = 0;
    }

    // Update is called once per frame
    void Update()
    {
        t = t + Time.deltaTime;
        var tseconds = t;
        img.color = new Color(img.color.r, img.color.g, img.color.b, Mathf.Max( 1 - decayConstant * tseconds * tseconds, 0.15f));
        txt.color = new Color(img.color.r, img.color.g, img.color.b, Mathf.Max(1 - decayConstant * tseconds * tseconds, 0.5f));
    }
}
